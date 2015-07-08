package moa.classifiers.meta;

import moa.classifiers.Ensemble;
import moa.core.MiscUtils;
import moa.options.FloatOption;
import weka.core.Instance;

public class OnlineBagging extends OzaBag implements Ensemble {
	private static final long serialVersionUID = 1L;

	private double lambda;
	public FloatOption lambdaOption = new FloatOption("lambda", 'l', "Parameter used by Poisson to encourage more or less diversity.", 1.0);

	@Override
    public void resetLearningImpl() {
        super.resetLearningImpl();
        this.lambda = lambdaOption.getValue();
    }

	public void trainOnInstance(Instance inst, double lambda) {
		for (int i = 0; i < this.ensemble.length; i++) {
            int k = MiscUtils.poisson(lambda, this.classifierRandom);
            if (k > 0) {
                Instance weightedInst = (Instance) inst.copy();
                weightedInst.setWeight(inst.weight() * k);
                this.ensemble[i].trainOnInstance(weightedInst);
            }
        }
	}

    @Override
    public void trainOnInstanceImpl(Instance inst) {
        this.trainOnInstance(inst, this.lambda);
    }

    @Override
    public String getPurposeString() {
        return "Modified version of incremental on-line bagging of Oza and Russell.";
    }
}
