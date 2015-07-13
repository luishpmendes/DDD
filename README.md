Writing a data file:

java -cp moa.jar -javaagent:sizeofag.jar moa.DoTask "WriteStreamToARFFFile -f data.arff -s (ConceptDriftStream -a 1)"

We can use the class moa.streams.ArffFileStream to read the data file