#!/bin/sh

CONF_DIR=/Users/ohmycloud/demo/Spark/socket-spark-streaming/socket-streaming/src/main/resources
APP_CONF=application.conf
EXECUTOR_JMX_PORT=23333
DRIVER_JMX_PORT=2334

spark-submit \
  --class com.gac.xs6.bigdata.BigdataApplication \
  --master local[2] \
  --deploy-mode client \
  --driver-memory 2g \
  --driver-cores 2 \
  --executor-memory 1g \
  --executor-cores 2 \
  --num-executors 2 \
  --conf "spark.executor.extraJavaOptions=-Dconfig.resource=$APP_CONF -Dcom.sun.management.jmxremote.port=$EXECUTOR_JMX_PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=`hostname`" \
  --conf "spark.driver.extraJavaOptions=-Dconfig.resource=$APP_CONF -Dcom.sun.management.jmxremote.port=$DRIVER_JMX_PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=`hostname`" \
  --conf spark.yarn.executor.memoryOverhead=1024 \
  --conf spark.yarn.driver.memoryOverhead=1024 \
  --conf spark.yarn.maxAppAttempts=2 \
  --conf spark.yarn.submit.waitAppCompletion=false \
  --files $CONF_DIR/$APP_CONF,$CONF_DIR/log4j.properties,$CONF_DIR/metrics.properties \
  /Users/ohmycloud/demo/Spark/socket-spark-streaming/socket-streaming/target/bigdata-online-1.0-SNAPSHOT-shaded.jar
