#!/bin/bash

rm -f /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/cpqd-*
rm -f /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/cpqd-*
cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/cpqd-balancer-test.war
cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /cygdrive/c/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/cpqd-balancer-test.war


# rm -f /home/jsantuci/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/cpqd-*
# rm -f /home/jsantuci/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/cpqd-*
# cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /home/jsantuci/dev/test/jboss-6.0.0-balancer/server/node-01/deploy/balancer-test/cpqd-balancer-test.war
# cp web/target/jms-cluster-balancer-web-1.0-SNAPSHOT.war /home/jsantuci/dev/test/jboss-6.0.0-balancer/server/node-02/deploy/balancer-test/cpqd-balancer-test.war

