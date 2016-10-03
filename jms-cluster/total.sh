#!/bin/bash

mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.poc.cluster.jms.client.TotalClient' -Dexec.args=$1
