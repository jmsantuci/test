#!/bin/bash

mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.poc.cluster.jms.client.CountProducerClient'
# mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.poc.cluster.jms.client.MessageCounterProducerClient' -Dexec.args="$1 $2"
