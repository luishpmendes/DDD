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
driftDetectionMethodOption warningOption = 0.75 (gamma)
driftDetectionMethodOption outControlOption = 0.75 (gamma)
reset = 16666.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.75 -d 0.75) -s (ArffFileStream -f data/PAKDD/Modeling_Data.arff) -e (SimpleClassificationPerformanceEvaluator -r 16666.67) -f 30 -i -1 -d data/PAKDD/Modeling_DataNB/Modeling_DataNB.dump -o data/PAKDD/Modeling_DataNB/Modeling_DataNB.out"

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  MLP
driftDetectionMethodOption warningOption = 0.75 (gamma)
driftDetectionMethodOption outControlOption = 0.75 (gamma)
reset = 16666.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l weka.classifiers.functions.MultilayerPerceptron) -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.75 -d 0.75) -s (ArffFileStream -f data/PAKDD/Modeling_Data.arff) -e (SimpleClassificationPerformanceEvaluator -r 16666.67) -f 30 -i -1 -d data/PAKDD/Modeling_DataMLP/Modeling_DataMLP.dump -o data/PAKDD/Modeling_DataMLP/Modeling_DataMLP.out"


Running KDD:

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  bayes.NaiveBayes
driftDetectionMethodOption warningOption = 1.15 (gamma)
driftDetectionMethodOption outControlOption = 1.15 (gamma)
reset = 164673.67

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l bayes.NaiveBayes -d 0.005)) -d (EarlyDriftDetectionMethod -w 1.15 -d 1.15) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e (SimpleClassificationPerformanceEvaluator -r 164673.67) -f 30 -i -1 -d data/kdd/kddcup.data_10_percent_processedNB/kddcup.data_10_percent_processedNB.dump -o data/kdd/kddcup.data_10_percent_processedNB/kddcup.data_10_percent_processedNB.out"

weight = 1
lowDiversity = 1
highDiversity = 0.005
baseEnsembleLearner baseLearnerOption =  MLP
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = 164674

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -l (meta.WEKAClassifier -l weka.classifiers.functions.MultilayerPerceptron) -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/kdd/kddcup.data_10_percent_processed.arff) -e (SimpleClassificationPerformanceEvaluator -r 164673.67) -f 30 -i -1 -d data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP.dump -o data/kdd/kddcup.data_10_percent_processedMLP/kddcup.data_10_percent_processedMLP.out"


Running circle

weight = 1
lowDiversity = 1
highDiversity = 0.05
baseEnsembleLearner baseLearnerOption =  trees.HoeffdingTree
driftDetectionMethodOption warningOption = 0.95 (gamma)
driftDetectionMethodOption outControlOption = 0.95 (gamma)
reset = half

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 10 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test/dataCircleGSev1Sp1Test.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Test/dataCircleGSev1Sp1Test.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 10 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train/dataCircleGSev1Sp1Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp1Train/dataCircleGSev1Sp1Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 10 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train/dataCircleGSev1Sp2Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp2Train/dataCircleGSev1Sp2Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train.arff) -e (SimpleClassificationPerformanceEvaluator -r 1000) -f 10 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train/dataCircleGSev1Sp3Train.dump -o data/ArtificialDataSets/circleG/dataCircleGSev1Sp3Train/dataCircleGSev1Sp3Train.out"

java -cp target/DDD-0.0.1-SNAPSHOT.jar moa.DoTask "EvaluatePrequential -l (meta.DDD -w 1 -l 1 -h 0.005 -e (meta.OnlineBagging -d 0.005)) -d (EarlyDriftDetectionMethod -w 0.95 -d 0.95) -s (ArffFileStream -f data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test.arff) -e (SimpleClassificationPerformanceEvaluator -r 250) -f 10 -i -1 -d data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test/dataCircleGSev2Sp1Test.dump -o data/ArtificialDataSets/circleG/dataCircleGSev2Sp1Test/dataCircleGSev2Sp1Test.out"


run again with the BasicClassificationPerformanceEvaluator, I fixed the dataset

run 30 runs
run eddm with naive bayes and see if the result obtained are similar
run on artificial datasets (try to figure out the paramters of the datasets)
double check code, drift handling after drift detection
search for mlp on moa


remover SimpleClassificationPerformanceEvaluator
fazer um evaluator pra testar os dataset articiais, resetando a accuracy na metade (quando acontece o drift)
rodar pelo menos os dataset não-artificiais 30 vezes, tirar a média e desvio padrão e salvar em csv e fazer um gŕafico