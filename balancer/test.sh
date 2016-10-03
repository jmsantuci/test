#!/bin/bash

mvn exec:java -f client/pom.xml -Dexec.mainClass='jms.cluster.balancer.client.WebBalancerTest' -Dexec.args='http://sagreosp.telefonica.br'

