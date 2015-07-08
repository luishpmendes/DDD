package moa.classifiers;

import weka.core.Instance;

public interface Ensemble extends Classifier {
    public void trainOnInstance(Instance inst, double lambda);
}
