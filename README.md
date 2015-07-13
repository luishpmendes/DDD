We can use the class moa.tasks.WriteStreamToARFFFile to write a data file:

java -cp moa.jar -javaagent:sizeofag.jar moa.DoTask "WriteStreamToARFFFile -f data.arff -s (ConceptDriftStream -a 1)"

We can use the class moa.streams.ArffFileStream to read a data file:

java -cp moa.jar -javaagent:sizeofag.jar moa.DoTask "EvaluatePrequential -s (ArffFileStream -f data.arff) -l trees.HoeffdingTree -i 1000000 -e (WindowClassificationPerformanceEvaluator -w 10000)"
