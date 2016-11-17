#!/bin/bash

rm -f /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/jms-*
rm -f /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/jms-*
cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/jms-balancer-test.war
cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/jms-balancer-test.war


