
#!/bin/bash
spark-submit \
--class wordcount.WordCount \
--num-executors 3 \
--driver-memory 100m \
--executor-memory 100m \
--executor-cores 3 \
/data/spark-1.0-SNAPSHOT.jar \
