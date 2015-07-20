package moa.classifiers.meta;

import weka.core.Instance;
import weka.core.Utils;
import moa.classifiers.Classifier;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.Ensemble;
import moa.core.Measurement;
import moa.classifiers.core.driftdetection.DriftDetectionMethod;
import moa.options.ClassOption;
import moa.options.FloatOption;

/**
 * Diversity for Dealing with Drifts of Leandro L. Minku and Xin Yao. 
 * 
 * <p>Parameters:</p>
 * <ul>
 * <li>-w : Multiplier constant for the weight of the old low diversity ensemble</li>
 * <li>-e : Online ensemble learning algorithm</li>
 * <li>-l : Parameter for ensemble learning with low diversity</li>
 * <li>-h : Parameter for ensemble learning with low diversity</li>
 * <li>-d : Drift detection method</li>
 * </ul>
 *
 * @author Luis H. P. Mendes (luishpmendes@gmail.com)
 */
public class DDD extends AbstractClassifier {
	private static final long serialVersionUID = 1L;

	public static final int BEFORE_DRIFT = 0;
	public static final int AFTER_DRIFT = 1;

	/** Multiplier constant for weight of the old low diversity ensemble */
	public FloatOption weightOption = new FloatOption("weight", 'w', "Multiplier constant for the weight of the old low diversity ensemble", 1.0);

	/** Online ensemble learning algorithm */
	public ClassOption ensembleLearningOption = new ClassOption("baseEnsembleLearner", 'e', "Online ensemble learning algorithm.", Classifier.class, "meta.OnlineBagging");

	/** Parameter for ensemble learning with low diversity */
	public FloatOption lowDiversityOption = new FloatOption("lowDiversity", 'l', "Parameter for ensemble learning with low diversity.", 2.0);

	/** Parameter for ensemble learning with high diversity */
	public FloatOption highDiversityOption = new FloatOption("lowDiversity", 'h', "Parameter for ensemble learning with high diversity.", 0.005);

	/** Drift detection method */
	public ClassOption driftDetectionMethodOption = new ClassOption("driftDetectionMethodOption", 'd', "Drift detection method.", DriftDetectionMethod.class, "EarlyDriftDetectionMethod");

	/* online ensembles */
	private Ensemble hnl; /* new low diversity */
	private Ensemble hnh; /* new high diversity */
	private Ensemble hol; /* old low diversity */
	private Ensemble hoh; /* old high diversity */

	/* Mode of operation */
	private int mode;

	/* accuracies */
	private double accol;
	private double accoh;
	private double accnl;
	private double accnh;

	/* variances */
	private double varol;
	private double varoh;
	private double varnl;
	private double varnh;

	/* standard deviations */
	private double stdol;
	private double stdoh;
	private double stdnl;
	private double stdnh;

	private int ddmLevel;

	private int timeStep;

	private double[] weightedMajority(double[] pnl, double[] pol, double[] poh, double wnl, double wol, double woh) {
		double[] result = null;

		int length = pnl.length;
		if (length < pol.length) {
			length = pol.length;
		}
		if (length < poh.length) {
			length = poh.length;
		}

		result = new double[length];

		for (int i = 0; i < length; i++) {
			double totalw = 0;
			result[i] = 0;
			if (i < pnl.length) {
				result[i] += wnl * pnl[i];
				totalw += wnl;
			}
			if (i < pol.length) {
				result[i] += wol * pol[i];
				totalw += wol;
			}
			if (i < poh.length) {
				result[i] += woh * poh[i];
				totalw += woh;
			}
			if (totalw != 0) {
				result[i] /= totalw;
			}
		}

		return result;
	}

	private void update(Instance inst) {
		// Update(accnl, stdnl, hnl, d)
		double tempnl = this.accnl;
		double accnlex = 0.0;
		if (this.hnl.correctlyClassifies(inst)) {
			accnlex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accnl = accnlex;
		} else {
			this.accnl = this.accnl + ((accnlex - this.accnl) / this.timeStep);
		}
		if (this.timeStep == 0) {
			this.varnl = 0.0;
		} else {
			this.varnl = this.varnl + (((accnlex - tempnl) * (accnlex - this.accnl)) / this.timeStep);
		}
		this.stdnl = Math.sqrt(this.varnl);
		// Update(accol, stdol, hol, d)
		double tempol = this.accol;
		double accolex = 0.0;
		if (this.hol.correctlyClassifies(inst)) {
			accolex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accol = accolex;
		} else {
			this.accol = this.accol + ((accolex - this.accol) / this.timeStep);
		}
		if (this.timeStep == 0) {
			this.varol = 0.0;
		} else {
			this.varol = this.varol + (((accolex - tempol) * (accolex - this.accol)) / this.timeStep);
		}
		this.stdol = Math.sqrt(this.varol);
		// Update(accoh, stdoh, hoh, d)
		double tempoh = this.accoh;
		double accohex = 0.0;
		if (this.hoh.correctlyClassifies(inst)) {
			accohex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accoh = accohex;
		} else {
			this.accoh = this.accoh + ((accohex - this.accoh) / this.timeStep);
		}
		if (this.timeStep == 0) {
			this.varoh = 0.0;
		} else {
			this.varoh = this.varoh + (((accohex - tempoh) * (accohex - this.accoh)) / this.timeStep);
		}
	}
	
	private void detectDrift(Instance inst) {
		int trueClass = (int) inst.classValue();
		boolean prediction = false;
		if (Utils.maxIndex(this.hnl.getVotesForInstance(inst)) == trueClass) {
			prediction = true;
		}
		this.ddmLevel = ((DriftDetectionMethod) this.getPreparedClassOption(this.driftDetectionMethodOption)).computeNextVal(prediction);
	}

    /**
     * Gets the current measurements of this classifier.<br><br>
     * 
     * The reason for ...Impl methods: ease programmer burden by not requiring 
     * them to remember calls to super in overridden methods. 
     * Note that this will produce compiler errors if not overridden.
     *
     * @return an array of measurements to be used in evaluation tasks
     */
	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns a string representation of the model.
     *
     * @param out	the stringbuilder to add the description
     * @param indent	the number of characters to indent
     */
	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		// TODO Auto-generated method stub
	}

    /**
     * Gets the purpose of this object
     *
     * @return the string with the purpose of this object
     */
    @Override
    public String getPurposeString() {
        return "Diversity for Dealing with Drifts of Leandro L. Minku and Xin Yao.";
    }

    /**
     * Predicts the class memberships for a given instance. If
     * an instance is unclassified, the returned array elements
     * must be all zero.
     *
     * @param inst the instance to be classified
     * @return an array containing the estimated membership
     * probabilities of the test instance in each class
     */
	public double[] getVotesForInstance(Instance inst) {
		double[] result = null;
		if (this.mode == DDD.BEFORE_DRIFT) {
			result = this.hnl.getVotesForInstance(inst); /* prediction ← hnl(d) */
		} else {
			double sumacc = this.accnl + this.accol + this.weightOption.getValue() * this.accoh;
			double wnl = this.accnl / sumacc;
			double wol = this.accol * this.weightOption.getValue() / sumacc;
			double woh = this.accoh / sumacc;
			result = this.weightedMajority(this.hnl.getVotesForInstance(inst), this.hol.getVotesForInstance(inst), this.hoh.getVotesForInstance(inst), wnl, wol, woh);
		}
		return result;
	}

    /**
     * Gets whether this classifier needs a random seed.
     * Examples of methods that needs a random seed are bagging and boosting.
     *
     * @return true if the classifier needs a random seed.
     */
	public boolean isRandomizable() {
		return true;
	}

    /**
     * Resets this classifier. It must be similar to
     * starting a new classifier from scratch. <br><br>
     * 
     * The reason for ...Impl methods: ease programmer burden by not requiring 
     * them to remember calls to super in overridden methods. 
     * Note that this will produce compiler errors if not overridden.
     */
	@Override
	public void resetLearningImpl() {
		this.mode = DDD.BEFORE_DRIFT;

		this.hnl = (Ensemble) this.getPreparedClassOption(this.ensembleLearningOption); /* new low diversity */
		this.hnh = (Ensemble) this.getPreparedClassOption(this.ensembleLearningOption); /* new high diversity */
		this.hol = null; /* old low diversity */
		this.hoh = null; /* old high diversity */

		/* accuracies */
		this.accol = this.accoh = this.accnl = this.accnh = 0;

		/* standard deviations */
		this.stdol = this.stdoh = this.stdnl = this.stdnh = 0;

		this.timeStep = 0;
	}

    /**
     * Trains this classifier incrementally using the given instance.<br><br>
     * 
     * The reason for ...Impl methods: ease programmer burden by not requiring 
     * them to remember calls to super in overridden methods. 
     * Note that this will produce compiler errors if not overridden.
     *
     * @param inst the instance to be used for training
     */
	@Override
	public void trainOnInstanceImpl(Instance inst) {
		if (this.mode == DDD.AFTER_DRIFT) {
			this.update(inst); /* ??? */
		}
		/* drift ← DetectDrift(hnl, d, pd) */
		this.detectDrift(inst);
		/* if drift == true then */
		if (this.ddmLevel == DriftDetectionMethod.DDM_OUTCONTROL_LEVEL) {
			if (this.mode == DDD.BEFORE_DRIFT || (this.mode == DDD.AFTER_DRIFT && this.accnl > this.accoh)) {
				this.hol = this.hnl;
			} else {
				this.hol = this.hoh;
			}
			this.hoh = this.hnh;
			this.hnl = (Ensemble) this.getPreparedClassOption(this.ensembleLearningOption);
			this.hnh = (Ensemble) this.getPreparedClassOption(this.ensembleLearningOption);
			this.accol = this.accoh = this.accnl = this.accnh = 0;
			this.stdol = this.stdoh = this.stdnl = this.stdnh = 0;
			this.mode = DDD.AFTER_DRIFT;
		}
		if (this.mode == DDD.AFTER_DRIFT) {
			if (this.accnl > this.accoh && this.accnl > this.accol) {
				this.mode = DDD.BEFORE_DRIFT;
			} else {
				if (this.accoh - this.stdoh > this.accnl + this.stdnl && this.accoh - this.stdoh > this.accol + this.stdol) {
					this.hnl = this.hoh;
					this.accnl = this.accoh;
					this.mode = DDD.BEFORE_DRIFT;
				}
			}
		}
		this.hnl.trainOnInstance(inst, this.lowDiversityOption.getValue()); // EnsembleLearning(hnl, d, pl)
		this.hnh.trainOnInstance(inst, this.highDiversityOption.getValue()); // EnsembleLearning(hnh, d, ph)
		if (this.mode == DDD.AFTER_DRIFT) {
			this.hol.trainOnInstance(inst, this.lowDiversityOption.getValue()); // EnsembleLearning(hol, d, pl)
			this.hoh.trainOnInstance(inst, this.lowDiversityOption.getValue()); // EnsembleLearning(hoh, d, pl)
		}

		this.timeStep++;
	}
}
