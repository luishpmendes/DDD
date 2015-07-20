package moa.classifiers;

import weka.core.Instance;

/**
 * Ensemble interface for incremental classification models. 
 *
 * @author Luis H. P. Mendes (luishpmendes@gmail.com)
 */
public interface Ensemble extends Classifier {
    /**
     * Trains this classifier incrementally using the given instance.
     *
     * @param inst the instance to be used for training
     * @param lambda parameter used to encourage more or less diversity
     */
    public void trainOnInstance(Instance inst, double lambda);
}
