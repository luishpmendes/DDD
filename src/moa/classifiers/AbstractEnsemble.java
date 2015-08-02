package moa.classifiers;

import java.util.Random;

import moa.options.ClassOption;
import moa.options.FloatOption;
import moa.options.IntOption;
import weka.core.Instance;

/**
 * Abstract Classifier.
 * 
 * <p>Parameters:</p>
 * <ul>
 * <li>-l : Classiﬁer to train</li>
 * <li>-s : The number of models in the ensemble</li>
 * <li>-d : Parameter used to encourage more or less diversity</li>
 * </ul>
 * @author Luis H. P. Mendes (luishpmendes@gmail.com)
 */
public abstract class AbstractEnsemble extends AbstractClassifier implements
		Ensemble {

	/** Classiﬁer to train */
    public ClassOption baseLearnerOption = new ClassOption("baseLearner", 'l',
            "Classifier to train.", Classifier.class, "trees.HoeffdingTree");

    /** The number of models in the ensemble */
    public IntOption ensembleSizeOption = new IntOption("ensembleSize", 'n',
            "The number of models in the ensemble.", 10, 1, Integer.MAX_VALUE);

    /** Parameter used to encourage more or less diversity */
	public FloatOption lambdaOption = new FloatOption("lambda", 'd',
			"Parameter used to encourage more or less diversity.", 1.0);

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

    /**
     * Gets the purpose of this object
     *
     * @return the string with the purpose of this object
     */
    @Override
    public String getPurposeString() {
        return "MOA Ensemble: " + getClass().getCanonicalName();
    }

    /**
     * Trains this ensemble incrementally using the given instance and lambda.
     *
     * @param inst the instance to be used for training
     * @param lambda parameter used to encourage more or less diversity
     */
	public void trainOnInstance(Instance inst, double lambda) {
		if (inst.weight() > 0.0) {
            this.trainingWeightSeenByModel += inst.weight();
            trainOnInstanceImpl(inst, lambda);
        }
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
    	this.trainOnInstanceImpl(inst, this.lambdaOption.getValue());
    }
    
    @Override
    public void setRandomSeed(int s) {
    	super.setRandomSeed(s);
        this.classifierRandom.setSeed(s);
    }
    
    @Override
    public void resetLearning() {
        this.trainingWeightSeenByModel = 0.0;
        if (isRandomizable()) {
			if (this.randomSeedOption != null && this.randomSeedOption.getValue() != 1 && this.randomSeed != 1) {
				this.classifierRandom = new Random();
			} else {
				this.classifierRandom = new Random(this.randomSeed);
			}
        }
        resetLearningImpl();
    }

    public void setClassifierRandom(Random r) {
    	this.classifierRandom = r;
    }
}
