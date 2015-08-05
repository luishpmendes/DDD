package moa.evaluation;

import weka.core.Instance;
import weka.core.Utils;
import moa.core.Measurement;
import moa.core.ObjectRepository;
import moa.options.AbstractOptionHandler;
import moa.options.FloatOption;
import moa.tasks.TaskMonitor;

public class SimpleClassificationPerformanceEvaluator extends AbstractOptionHandler implements ClassificationPerformanceEvaluator {
	/** The weight of instances before reseting the accuracy */
    public FloatOption resetOption = new FloatOption("reset", 'r', "The weight of instances before reseting the accuracy.", 30, 0, Integer.MAX_VALUE);
	
    protected double weightObserved;

    protected double weightCorrect;

    protected double[] columnKappa;

    protected double[] rowKappa;

    protected int numClasses;

    private double weightCorrectNoChangeClassifier;
    
    private int lastSeenClass;
    
    public double getTotalWeightObserved() {
        return this.weightObserved;
    }

    public double getFractionCorrectlyClassified() {
        return this.weightObserved > 0.0 ? this.weightCorrect / this.weightObserved : 0.0;
    }

    public double getFractionIncorrectlyClassified() {
        return 1.0 - getFractionCorrectlyClassified();
    }

    public double getKappaStatistic() {
        if (this.weightObserved > 0.0) {
            double p0 = getFractionCorrectlyClassified();
            double pc = 0.0;
            for (int i = 0; i < this.numClasses; i++) {
                pc += (this.rowKappa[i] / this.weightObserved) * (this.columnKappa[i] / this.weightObserved);
            }
            return (p0 - pc) / (1.0 - pc);
        } else {
            return 0;
        }
    }

    public double getKappaTemporalStatistic() {
        if (this.weightObserved > 0.0) {
            double p0 = this.weightCorrect / this.weightObserved;
            double pc = this.weightCorrectNoChangeClassifier / this.weightObserved;
            return (p0 - pc) / (1.0 - pc);
        } else {
            return 0;
        }
    }
    
    /**
     * Resets this evaluator. It must be similar to
     * starting a new evaluator from scratch.
     */
    public void reset() {
        reset(this.numClasses);
    }
    
    public void reset(int numClasses) {
        this.numClasses = numClasses;
        this.rowKappa = new double[numClasses];
        this.columnKappa = new double[numClasses];
        for (int i = 0; i < this.numClasses; i++) {
            this.rowKappa[i] = 0.0;
            this.columnKappa[i] = 0.0;
        }
        this.weightObserved = 0.0;
        this.weightCorrect = 0.0;
        this.weightCorrectNoChangeClassifier = 0.0;
        this.lastSeenClass = 0;
    }

    /**
     * Adds a learning result to this evaluator.
     *
     * @param inst the instance to be classified
     * @param classVotes an array containing the estimated membership
     * probabilities of the test instance in each class
     */
    public void addResult(Instance inst, double[] classVotes) {
        double weight = inst.weight();
        int trueClass = (int) inst.classValue();
        if (weight > 0.0) {
            if (this.weightObserved == 0 || (this.resetOption.getValue() > 0 && this.weightObserved >= this.resetOption.getValue())) {
                reset(inst.dataset().numClasses());
            }
            this.weightObserved += weight;
            int predictedClass = Utils.maxIndex(classVotes);
            if (predictedClass == trueClass) {
                this.weightCorrect += weight;
            }
            this.rowKappa[predictedClass] += weight;
            this.columnKappa[trueClass] += weight;
        }
        if (this.lastSeenClass == trueClass) {
            this.weightCorrectNoChangeClassifier += weight;
        }
        this.lastSeenClass = trueClass;
    }

    /**
     * Gets the current measurements monitored by this evaluator.
     *
     * @return an array of measurements monitored by this evaluator
     */
    public Measurement[] getPerformanceMeasurements() {
        return new Measurement[]{
            new Measurement("classified instances", this.getTotalWeightObserved()),
            new Measurement("classifications correct", this.getFractionCorrectlyClassified()),
            new Measurement("Kappa Statistic", this.getKappaStatistic()),
            new Measurement("Kappa Temporal Statistic", this.getKappaTemporalStatistic())
        };
    }

    /**
     * Returns a string representation of this object.
     * Used in <code>AbstractMOAObject.toString</code>
     * to give a string representation of the object.
     *
     * @param sb	the stringbuilder to add the description
     * @param indent	the number of characters to indent
     */
    public void getDescription(StringBuilder sb, int indent) {
        Measurement.getMeasurementsDescription(getPerformanceMeasurements(), sb, indent);
    }

    /**
     * This method describes the implementation of how to prepare this object for use.
     * All classes that extends this class have to implement <code>prepareForUseImpl</code>
     * and not <code>prepareForUse</code> since
     * <code>prepareForUse</code> calls <code>prepareForUseImpl</code>.
     *
     * @param monitor the TaskMonitor to use
     * @param repository  the ObjectRepository to use
     */
	@Override
	protected void prepareForUseImpl(TaskMonitor monitor, ObjectRepository repository) {
		this.reset();
	}
}
