1启动sparksql:spark-shell --master local[2] --jars 
mysql-connector-java-5.1.27.bin.jar
2thriftwerver&beeline使用:连接beenline -u jdbc:hive2://localhost:10000 -u root
,不管启动多少个客户端,永远都是一个spark application,解决数据共享的问题.
