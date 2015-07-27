We can use the class moa.tasks.WriteStreamToARFFFile to write a data file:

java -cp moa.jar -javaagent:sizeofag.jar moa.DoTask "WriteStreamToARFFFile -f data.arff -s (ConceptDriftStream -a 1)"

We can use the class moa.streams.ArffFileStream to read a data file:

java -cp moa.jar -javaagent:sizeofag.jar moa.DoTask "EvaluatePrequential -s (ArffFileStream -f data.arff) -l trees.HoeffdingTree -i 1000000 -e (WindowClassificationPerformanceEvaluator -w 10000)"

Converting CSV to ARFF:

java -cp target/DDD-0.0.1-SNAPSHOT.jar weka.core.converters.CSVLoader -B 1000 data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Test.csv > data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Test.arff

Running PAKDD:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  bayes.NaiveBayes
driftDetectionMethodOption warningOption = 0.8
driftDetectionMethodOption outControlOption = 0.75

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.8 -b 0.75) -s (ArffFileStream -f data/PAKDD/Modeling_Data.arff) -e SimpleClassificationPerformanceEvaluator -f 30 -i -1 -d data/PAKDD/Modeling_Data.dump -o data/PAKDD/Modeling_Data.out"


Running KDD:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  bayes.NaiveBayes
driftDetectionMethodOption warningOption = 0.99
driftDetectionMethodOption outControlOption = 0.95

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.99 -b 0.95) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e SimpleClassificationPerformanceEvaluator -f 30 -i -1 -d data/kdd/kddcup.data_10_percent_processed.dump -o data/kdd/kddcup.data_10_percent_processed.out"
