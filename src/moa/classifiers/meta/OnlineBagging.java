package moa.classifiers.meta;

import moa.classifiers.AbstractEnsemble;
import moa.classifiers.Classifier;
import moa.core.DoubleVector;
import moa.core.Measurement;
import moa.core.MiscUtils;
import weka.core.Instance;

/**
 * Incremental on-line bagging of Oza and Russell.
 *
 * <p>Parameters:</p> <ul>
 * <li>-r : Seed for random behaviour of the classifier.</li>
 * <li>-l : Classifier to train</li>
 * <li>-s : The number of models in the bag</li> </ul>
 * <li>-d : Parameter used to encourage more or less diversity</li>
 *
 * @author Luis H. P. Mendes (luishpmendes@gmail.com)
 */
public class OnlineBagging extends AbstractEnsemble {
	private static final long serialVersionUID = 1L;

    /**
     * Gets the purpose of this object
     *
     * @return the string with the purpose of this object
     */
    @Override
    public String getPurposeString() {
        return "Modified version of incremental on-line bagging of Oza and Russell.";
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
        this.ensemble = new Classifier[this.ensembleSizeOption.getValue()];
        Classifier baseLearner = (Classifier) getPreparedClassOption(this.baseLearnerOption);
        baseLearner.resetLearning();
        baseLearner.setRandomSeed(this.randomSeed);
        for (int i = 0; i < this.ensemble.length; i++) {
            this.ensemble[i] = baseLearner.copy();
        }
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
    //@Override
    public double[] getVotesForInstance(Instance inst) {
        DoubleVector combinedVote = new DoubleVector();
        for (int i = 0; i < this.ensemble.length; i++) {
            DoubleVector vote = new DoubleVector(this.ensemble[i].getVotesForInstance(inst));
            if (vote.sumOfValues() > 0.0) {
                vote.normalize();
                combinedVote.addValues(vote);
            }
        }
        return combinedVote.getArrayRef();
    }

    /**
     * Gets whether this classifier needs a random seed.
     * Examples of methods that needs a random seed are bagging and boosting.
     *
     * @return true if the classifier needs a random seed.
     */
    //@Override
    public boolean isRandomizable() {
        return true;
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
        return new Measurement[]{new Measurement("ensemble size", this.ensemble != null ? this.ensemble.length : 0)};
    }

    /**
     * Gets the classifiers of this ensemble.
     * Returns null if this classifier is a single classifier.
     *
     * @return an array of the classifiers of the ensemble
     */
    @Override
    public Classifier[] getSubClassifiers() {
        return this.ensemble.clone();
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
	public void trainOnInstanceImpl(Instance inst, double lambda) {
		for (int i = 0; i < this.ensemble.length; i++) {
            int k = MiscUtils.poisson(lambda, this.classifierRandom);
            if (k > 0) {
                Instance weightedInst = (Instance) inst.copy();
                weightedInst.setWeight(inst.weight() * k);
                this.ensemble[i].trainOnInstance(weightedInst);
            }
        }
	}
}
