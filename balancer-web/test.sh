#!/bin/bash

mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.cluster.balancer.client.BalancerClient' -Dexec.args='http://s1:8808'

