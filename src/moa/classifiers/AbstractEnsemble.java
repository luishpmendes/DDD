package moa.classifiers;

import moa.options.ClassOption;
import moa.options.FloatOption;
import moa.options.IntOption;
import weka.core.Instance;

public abstract class AbstractEnsemble extends AbstractClassifier implements
		Ensemble {

    public ClassOption baseLearnerOption = new ClassOption("baseLearner", 'l',
            "Classifier to train.", Classifier.class, "trees.HoeffdingTree");

    public IntOption ensembleSizeOption = new IntOption("ensembleSize", 's',
            "The number of models in the bag.", 10, 1, Integer.MAX_VALUE);
    
	public FloatOption lambdaOption = new FloatOption("lambda", 'l',
			"Parameter used by Poisson to encourage more or less diversity.", 1.0);

    protected Classifier[] ensemble;

    /**
     * Trains this ensemble incrementally using the given instance.<br><br>
     * 
     * The reason for ...Impl methods: ease programmer burden by not requiring 
     * them to remember calls to super in overridden methods. 
     * Note that this will produce compiler errors if not overridden.
     *
     * @param inst the instance to be used for training
     * @param lambda parameter used to encourage more or less diversity
     */
    public abstract void trainOnInstanceImpl(Instance inst, double lambda);

    @Override
    public String getPurposeString() {
        return "MOA Ensemble: " + getClass().getCanonicalName();
    }

	public void trainOnInstance(Instance inst, double lambda) {
		if (inst.weight() > 0.0) {
            this.trainingWeightSeenByModel += inst.weight();
            trainOnInstanceImpl(inst, lambda);
        }
	}

    @Override
    public void trainOnInstanceImpl(Instance inst) {
    	this.trainOnInstance(inst, this.lambdaOption.getValue());
    }
}
