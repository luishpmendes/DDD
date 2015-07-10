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
import moa.streams.InstanceStream;

public class DDD extends AbstractClassifier {
	private static final long serialVersionUID = 1L;
	
	public static final int BEFORE_DRIFT = 0;
	public static final int AFTER_DRIFT = 1;
	
	/* multiplier constant W for weight of the old low diversity ensemble */
	private double W;
	public FloatOption weightOption = new FloatOption("weight", 'w', "multiplier constant for the weight of the old low diversity ensemble", 1.0);
	
	public ClassOption baseEnsembleLearnerOption = new ClassOption("baseEnsembleLearner", 'e', "Online ensemble learning algorithm.", Classifier.class, "meta.OnlineBagging");
	
	/* parameter for ensemble learning with low diversity */
	private double pl;
	public FloatOption lowDiversityOption = new FloatOption("lowDiversity", 'l', "Parameter for ensemble learning with low diversity.", 2.0);
	/* parameter for ensemble learning with high diversity */
	private double ph;
	public FloatOption highDiversityOption = new FloatOption("lowDiversity", 'l', "Parameter for ensemble learning with low diversity.", 0.005);
	
	/* drift detection method */
	private DriftDetectionMethod driftDetectionMethod;
	public ClassOption driftDetectionMethodOption = new ClassOption("driftDetectionMethodOption", 'd', "Drift detection method.", DriftDetectionMethod.class, "EarlyDriftDetectionMethod");
	
	/* data stream */
	private InstanceStream stream; //??

	/* online ensembles */
	private Ensemble hnl; /* new low diversity */
	private Ensemble hnh; /* new high diversity */
	private Ensemble hol; /* old low diversity */
	private Ensemble hoh; /* old high diversity */
	
	/* mode of operations */
	private int mode;
	
	/* accuracies */
	private double accol;
	private double accoh;
	private double accnl;
	private double accnh;

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
		double accnlex = 0.0;
		if (this.hnl.correctlyClassifies(inst)) {
			accnlex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accnl = accnlex;
		} else {
			this.accnl = this.accnl + (accnlex - this.accnl)/(this.timeStep + 1.0);
		}
		// update stdnl
		// Update(accol, stdol, hol, d)
		double accolex = 0.0;
		if (this.hol.correctlyClassifies(inst)) {
			accolex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accol = accolex;
		} else {
			this.accol = this.accol + (accolex - this.accol)/(this.timeStep + 1.0);
		}
		// update stdol
		// Update(accoh, stdoh, hoh, d)
		double accohex = 0.0;
		if (this.hoh.correctlyClassifies(inst)) {
			accohex = 1.0;
		}
		if (this.timeStep == 0) {
			this.accoh = accohex;
		} else {
			this.accoh = this.accoh + (accohex - this.accoh)/(this.timeStep + 1.0);
		}
		// update stdoh
	}
	
	public double[] getVotesForInstance(Instance inst) {
		double[] result = null;
		if (this.mode == DDD.BEFORE_DRIFT) {
			result = this.hnl.getVotesForInstance(inst); /* prediction ← hnl(d) */
		} else {
			double sumacc = this.accnl + this.accol + this.W * this.accoh;
			double wnl = this.accnl / sumacc;
			double wol = this.accol * this.W / sumacc;
			double woh = this.accoh / sumacc;
			result = this.weightedMajority(this.hnl.getVotesForInstance(inst), this.hol.getVotesForInstance(inst), this.hoh.getVotesForInstance(inst), wnl, wol, woh);
		}
		return result;
	}

	public boolean isRandomizable() {
		return true;
	}

	@Override
	public void getModelDescription(StringBuilder out, int indent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetLearningImpl() {
		this.W = this.weightOption.getValue();
		
		this.pl = this.lowDiversityOption.getValue();
		this.ph = this.highDiversityOption.getValue();
		
		this.driftDetectionMethod = (DriftDetectionMethod) this.getPreparedClassOption(this.driftDetectionMethodOption);
		
		this.mode = DDD.BEFORE_DRIFT;

		this.hnl = (Ensemble) this.getPreparedClassOption(this.baseEnsembleLearnerOption); /* new low diversity */
		this.hnh = (Ensemble) this.getPreparedClassOption(this.baseEnsembleLearnerOption); /* new high diversity */
		this.hol = null; /* old low diversity */
		this.hoh = null; /* old high diversity */
		
		/* accuracies */
		this.accol = this.accoh = this.accnl = this.accnh = 0;
		
		/* standard deviations */
		this.stdol = this.stdoh = this.stdnl = this.stdnh = 0;
		
		this.timeStep = 0;
	}
	
	private void detectDrift(Instance inst) {
		int trueClass = (int) inst.classValue();
		boolean prediction = false;
		if (Utils.maxIndex(this.hnl.getVotesForInstance(inst)) == trueClass) {
			prediction = true;
		}
		this.ddmLevel = this.driftDetectionMethod.computeNextVal(prediction);
	}

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
			this.hnl = (Ensemble) this.getPreparedClassOption(this.baseEnsembleLearnerOption);
			this.hnh = (Ensemble) this.getPreparedClassOption(this.baseEnsembleLearnerOption);
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
		this.hnl.trainOnInstance(inst, this.pl); // EnsembleLearning(hnl, d, pl)
		this.hnh.trainOnInstance(inst, this.ph); // EnsembleLearning(hnh, d, ph)
		if (this.mode == DDD.AFTER_DRIFT) {
			this.hol.trainOnInstance(inst, this.pl); // EnsembleLearning(hol, d, pl)
			this.hoh.trainOnInstance(inst, this.pl); // EnsembleLearning(hoh, d, pl)
		}
		
		this.timeStep++;
	}

    @Override
    public String getPurposeString() {
        return "Diversity for Dealing with Drifts of Leandro L. Minku and Xin Yao.";
    }
}
