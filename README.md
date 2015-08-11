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
baseEnsembleLearner baseLearnerOption = bayes.NaiveBayes
driftDetectionMethodOption warningOption = 0.75 (gamma)
driftDetectionMethodOption outControlOption = 0.75 (gamma)
reset = 16666.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005) -d (EarlyDriftDetectionMethod -w 0.75 -d 0.75)) -s (ArffFileStream -f data/PAKDD/Modeling_Data.arff) -e (SimpleClassificationPerformanceEvaluator -r 16666.67) -f 225 -i -1 -d data/PAKDD/Modeling_DataNB/Modeling_DataNB.dump -o data/PAKDD/Modeling_DataNB/Modeling_DataNB.out"

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption = MLP
learning rate = 0.01
momentum = 0.01
epochs = 1
nodes = 10
driftDetectionMethodOption warningOption = 0.75 (gamma)
driftDetectionMethodOption outControlOption = 0.75 (gamma)
reset = 16666.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l (weka.classifiers.functions.MultilayerPerceptron -L 0.01 -M 0.01 -N 1 -H 10)) -d 0.005) -d (EarlyDriftDetectionMethod -w 0.75 -d 0.75)) -s (ArffFileStream -f data/PAKDD/Modeling_Data.arff) -e (SimpleClassificationPerformanceEvaluator -r 16666.67) -f 225 -i -1 -d data/PAKDD/Modeling_DataMLP/Modeling_DataMLP.dump -o data/PAKDD/Modeling_DataMLP/Modeling_DataMLP.out"


Running KDD:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption = bayes.NaiveBayes
driftDetectionMethodOption warningOption = 1.15 (gamma)
driftDetectionMethodOption outControlOption = 1.15 (gamma)
reset = 164673.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005) -d (EarlyDriftDetectionMethod -w 1.15 -d 1.15)) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e (SimpleClassificationPerformanceEvaluator -r 164673.67) -f 225 -i -1 -d data/kdd/kddcup.data_10_percent_processedNB/kddcup.data_10_percent_processedNB.dump -o data/kdd/kddcup.data_10_percent_processedNB/kddcup.data_10_percent_processedNB.out"

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption = MLP
learning rate = 0.01
momentum = 0.01
epochs = 1
nodes = 10
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = 164673.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l (weka.classifiers.functions.MultilayerPerceptron -L 0.01 -M 0.01 -N 1 -H 10)) -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e (SimpleClassificationPerformanceEvaluator -r 164673.67) -f 225 -i -1 -d data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP.dump -o data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP.out"


Running elecdata:

weight = 1
lowDiversity = 1
highDiversity = 0.1
baseEnsembleLearner baseLearnerOption = bayes.NaiveBayes
FA = 45
reset = 15104

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.1) -f 45) -s (ArffFileStream -f data/elecdata/elec2.arff) -e (SimpleClassificationPerformanceEvaluator -r 15104) -f 225 -i -1 -d data/elecdata/elec2NB/elec2NB.dump -o data/elecdata/elec2NB/elec2NB.out"

weight = 1
lowDiversity = 1
highDiversity = 0.05
baseEnsembleLearner baseLearnerOption = MLP
learning rate = 0.01
momentum = 0.01
epochs = 1
nodes = 10
FA = 5
reset = 15104

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l (weka.classifiers.functions.MultilayerPerceptron -L 0.01 -M 0.01 -N 1 -H 10)) -d 0.05) -f 5) -s (ArffFileStream -f data/elecdata/elec2.arff) -e (SimpleClassificationPerformanceEvaluator -r 15104) -f 225 -i -1 -d data/elecdata/elec2MLP/elec2MLP.dump -o data/elecdata/elec2MLP/elec2MLP.out"


Running bool:

weight = 1
lowDiversity = 1
highDiversity = 0.1
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Test/dataBool1000Sev1Sp1Test.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Test/dataBool1000Sev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Train/dataBool1000Sev1Sp1Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp1Train/dataBool1000Sev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp2Train/dataBool1000Sev1Sp2Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp2Train/dataBool1000Sev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp3Train/dataBool1000Sev1Sp3Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev1Sp3Train/dataBool1000Sev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Test/dataBool1000Sev2Sp1Test.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Test/dataBool1000Sev2Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Train/dataBool1000Sev2Sp1Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp1Train/dataBool1000Sev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp2Train/dataBool1000Sev2Sp2Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp2Train/dataBool1000Sev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp3Train/dataBool1000Sev2Sp3Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev2Sp3Train/dataBool1000Sev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Test/dataBool1000Sev3Sp1Test.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Test/dataBool1000Sev3Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Train/dataBool1000Sev3Sp1Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp1Train/dataBool1000Sev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp2Train/dataBool1000Sev3Sp2Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp2Train/dataBool1000Sev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.1 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.1) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp3Train/dataBool1000Sev3Sp3Train.dump -o data/ArtificialDataSets/bool1000/dataBool1000Sev3Sp3Train/dataBool1000Sev3Sp3Train.out"


Running circle:

weight = 1
lowDiversity = 1
highDiversity = 0.05
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test/dataCircleGSev1Sp1Test.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test/dataCircleGSev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train/dataCircleGSev1Sp1Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train/dataCircleGSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train/dataCircleGSev1Sp2Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train/dataCircleGSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train/dataCircleGSev1Sp3Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train/dataCircleGSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test/dataCircleGSev2Sp1Test.dump -o data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test/dataCircleGSev2Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Train/dataCircleGSev2Sp1Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Train/dataCircleGSev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev2Sp2Train/dataCircleGSev2Sp2Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev2Sp2Train/dataCircleGSev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev2Sp3Train/dataCircleGSev2Sp3Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev2Sp3Train/dataCircleGSev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Test/dataCircleGSev3Sp1Test.dump -o data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Test/dataCircleGSev3Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Train/dataCircleGSev3Sp1Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev3Sp1Train/dataCircleGSev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev3Sp2Train/dataCircleGSev3Sp2Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev3Sp2Train/dataCircleGSev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev3Sp3Train/dataCircleGSev3Sp3Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev3Sp3Train/dataCircleGSev3Sp3Train.out"


Running line:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev1Sp1Test/dataLineSev1Sp1Test.dump -o data/ArtificialDataSets/line/dataLineSev1Sp1Test/dataLineSev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev1Sp1Train/dataLineSev1Sp1Train.dump -o data/ArtificialDataSets/line/dataLineSev1Sp1Train/dataLineSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev1Sp2Train/dataLineSev1Sp2Train.dump -o data/ArtificialDataSets/line/dataLineSev1Sp2Train/dataLineSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev1Sp3Train/dataLineSev1Sp3Train.dump -o data/ArtificialDataSets/line/dataLineSev1Sp3Train/dataLineSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev2Sp1Test/dataLineSev2Sp1Test.dump -o data/ArtificialDataSets/line/dataLineSev2Sp1Test/dataLineSev2Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev2Sp1Train/dataLineSev2Sp1Train.dump -o data/ArtificialDataSets/line/dataLineSev2Sp1Train/dataLineSev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev2Sp2Train/dataLineSev2Sp2Train.dump -o data/ArtificialDataSets/line/dataLineSev2Sp2Train/dataLineSev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev2Sp3Train/dataLineSev2Sp3Train.dump -o data/ArtificialDataSets/line/dataLineSev2Sp3Train/dataLineSev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev3Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev3Sp1Test/dataLineSev3Sp1Test.dump -o data/ArtificialDataSets/line/dataLineSev3Sp1Test/dataLineSev3Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev3Sp1Train/dataLineSev3Sp1Train.dump -o data/ArtificialDataSets/line/dataLineSev3Sp1Train/dataLineSev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev3Sp2Train/dataLineSev3Sp2Train.dump -o data/ArtificialDataSets/line/dataLineSev3Sp2Train/dataLineSev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/line/dataLineSev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/line/dataLineSev3Sp3Train/dataLineSev3Sp3Train.dump -o data/ArtificialDataSets/line/dataLineSev3Sp3Train/dataLineSev3Sp3Train.out"


Running plane:

weight = 1
lowDiversity = 1
highDiversity = 0.05
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp1Train/dataPlaneAddExpStyle1driftSev1Sp1Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp1Train/dataPlaneAddExpStyle1driftSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp2Train/dataPlaneAddExpStyle1driftSev1Sp2Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp2Train/dataPlaneAddExpStyle1driftSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp3Train/dataPlaneAddExpStyle1driftSev1Sp3Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Sp3Train/dataPlaneAddExpStyle1driftSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Test/dataPlaneAddExpStyle1driftSev1Test.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev1Test/dataPlaneAddExpStyle1driftSev1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp1Train/dataPlaneAddExpStyle1driftSev2Sp1Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp1Train/dataPlaneAddExpStyle1driftSev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp2Train/dataPlaneAddExpStyle1driftSev2Sp2Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp2Train/dataPlaneAddExpStyle1driftSev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp3Train/dataPlaneAddExpStyle1driftSev2Sp3Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Sp3Train/dataPlaneAddExpStyle1driftSev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Test/dataPlaneAddExpStyle1driftSev2Test.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev2Test/dataPlaneAddExpStyle1driftSev2Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp1Train/dataPlaneAddExpStyle1driftSev3Sp1Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp1Train/dataPlaneAddExpStyle1driftSev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp2Train/dataPlaneAddExpStyle1driftSev3Sp2Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp2Train/dataPlaneAddExpStyle1driftSev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 500) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp3Train/dataPlaneAddExpStyle1driftSev3Sp3Train.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Sp3Train/dataPlaneAddExpStyle1driftSev3Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 100) -f 25 -i -1 -d data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Test/dataPlaneAddExpStyle1driftSev3Test.dump -o data/ArtificialDataSets/plane10d/dataPlaneAddExpStyle1driftSev3Test/dataPlaneAddExpStyle1driftSev3Test.out"


Running sine:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev1Sp1Test/dataSineSev1Sp1Test.dump -o data/ArtificialDataSets/sine/dataSineSev1Sp1Test/dataSineSev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev1Sp1Train/dataSineSev1Sp1Train.dump -o data/ArtificialDataSets/sine/dataSineSev1Sp1Train/dataSineSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev1Sp2Train/dataSineSev1Sp2Train.dump -o data/ArtificialDataSets/sine/dataSineSev1Sp2Train/dataSineSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev1Sp3Train/dataSineSev1Sp3Train.dump -o data/ArtificialDataSets/sine/dataSineSev1Sp3Train/dataSineSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev2Sp1Test/dataSineSev2Sp1Test.dump -o data/ArtificialDataSets/sine/dataSineSev2Sp1Test/dataSineSev2Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev2Sp1Train/dataSineSev2Sp1Train.dump -o data/ArtificialDataSets/sine/dataSineSev2Sp1Train/dataSineSev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev2Sp2Train/dataSineSev2Sp2Train.dump -o data/ArtificialDataSets/sine/dataSineSev2Sp2Train/dataSineSev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev2Sp3Train/dataSineSev2Sp3Train.dump -o data/ArtificialDataSets/sine/dataSineSev2Sp3Train/dataSineSev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev3Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev3Sp1Test/dataSineSev3Sp1Test.dump -o data/ArtificialDataSets/sine/dataSineSev3Sp1Test/dataSineSev3Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev3Sp1Train/dataSineSev3Sp1Train.dump -o data/ArtificialDataSets/sine/dataSineSev3Sp1Train/dataSineSev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev3Sp2Train/dataSineSev3Sp2Train.dump -o data/ArtificialDataSets/sine/dataSineSev3Sp2Train/dataSineSev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.005) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sine/dataSineSev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sine/dataSineSev3Sp3Train/dataSineSev3Sp3Train.dump -o data/ArtificialDataSets/sine/dataSineSev3Sp3Train/dataSineSev3Sp3Train.out"


Running sineH:

weight = 1
lowDiversity = 1
highDiversity = 0.05
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev1Sp1Test/dataSineHSev1Sp1Test.dump -o data/ArtificialDataSets/sineH/dataSineHSev1Sp1Test/dataSineHSev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev1Sp1Train/dataSineHSev1Sp1Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev1Sp1Train/dataSineHSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev1Sp2Train/dataSineHSev1Sp2Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev1Sp2Train/dataSineHSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev1Sp3Train/dataSineHSev1Sp3Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev1Sp3Train/dataSineHSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev2Sp1Test/dataSineHSev2Sp1Test.dump -o data/ArtificialDataSets/sineH/dataSineHSev2Sp1Test/dataSineHSev2Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev2Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev2Sp1Train/dataSineHSev2Sp1Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev2Sp1Train/dataSineHSev2Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev2Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev2Sp2Train/dataSineHSev2Sp2Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev2Sp2Train/dataSineHSev2Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev2Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev2Sp3Train/dataSineHSev2Sp3Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev2Sp3Train/dataSineHSev2Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev3Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev3Sp1Test/dataSineHSev3Sp1Test.dump -o data/ArtificialDataSets/sineH/dataSineHSev3Sp1Test/dataSineHSev3Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev3Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev3Sp1Train/dataSineHSev3Sp1Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev3Sp1Train/dataSineHSev3Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev3Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev3Sp2Train/dataSineHSev3Sp2Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev3Sp2Train/dataSineHSev3Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.05 -e (meta.OnlineBagging -l trees.HoeffdingTree -d 0.05) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95)) -s (ArffFileStream -f data/ArtificialDataSets/sineH/dataSineHSev3Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 25 -i -1 -d data/ArtificialDataSets/sineH/dataSineHSev3Sp3Train/dataSineHSev3Sp3Train.dump -o data/ArtificialDataSets/sineH/dataSineHSev3Sp3Train/dataSineHSev3Sp3Train.out"
