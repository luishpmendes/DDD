#!/bin/bash
prime=(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113)
rm data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP*.dump
rm data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP*.out
for i in `seq 1 30`;
do
    java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -r ${prime[i-1]} -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l weka.classifiers.functions.MultilayerPerceptron) -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e (SimpleClassificationPerformanceEvaluator -r 164673.67) -f 225 -i -1 -d data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP$i.dump -o data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP$i.out"
done
