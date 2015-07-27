package moa.evaluation;

import weka.core.Instance;
import weka.core.Utils;
import moa.AbstractMOAObject;
import moa.core.Measurement;

public class SimpleClassificationPerformanceEvaluator extends AbstractMOAObject implements ClassificationPerformanceEvaluator {
    private static final long serialVersionUID = 1L;

    protected double weightObserved;
    protected double weightCorrect;

    public void reset() {
    	this.weightObserved = 0.0;
        this.weightCorrect = 0.0;
    }

    public void addResult(Instance inst, double[] classVotes) {
        double weight = inst.weight();
        int trueClass = (int) inst.classValue();
        if (weight > 0.0) {
            if (this.weightObserved == 0) {
                reset();
            }
            this.weightObserved += weight;
            int predictedClass = Utils.maxIndex(classVotes);
            if (predictedClass == trueClass) {
                this.weightCorrect += weight;
            }
        }
    }

    public Measurement[] getPerformanceMeasurements() {
        return new Measurement[] {
            new Measurement("classified instances", this.getTotalWeightObserved()),
            new Measurement("classifications correct", this.getFractionCorrectlyClassified())
        };

    }

    public double getTotalWeightObserved() {
        return this.weightObserved;
    }

    public double getFractionCorrectlyClassified() {
        return this.weightObserved > 0.0 ? this.weightCorrect / this.weightObserved : 0.0;
    }

    public double getFractionIncorrectlyClassified() {
        return 1.0 - this.getFractionCorrectlyClassified();
    }

    public void getDescription(StringBuilder sb, int indent) {
        Measurement.getMeasurementsDescription(this.getPerformanceMeasurements(), sb, indent);
    }
}
