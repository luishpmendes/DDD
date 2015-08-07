#!/bin/bash
prime=(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113)
rm data/elecdata/elec2NB/elec2NB*.dump
rm data/elecdata/elec2NB/elec2NB*.out
for i in `seq 1 30`;
do
    java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -r ${prime[i-1]} -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.1) -f 45) -s (ArffFileStream -f data/elecdata/elec2.arff) -e (SimpleClassificationPerformanceEvaluator -r 15104) -f 225 -i -1 -d data/elecdata/elec2NB/elec2NB$i.dump -o data/elecdata/elec2NB/elec2NB$i.out"
done
