#!/bin/bash

mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.cluster.balancer.client.BalancerClient' -Dexec.args='http://cpqd036516:8808'

