#!/bin/bash

rm ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/cons-01/deploy/jms-poc-cluster-jms-*
rm ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/cons-02/deploy/jms-poc-cluster-jms-*
cp model/target/jms-poc-cluster-jms-model-1.0-SNAPSHOT.jar consumer/target/jms-poc-cluster-jms-consumer-1.0-SNAPSHOT.jar ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/cons-01/deploy
cp model/target/jms-poc-cluster-jms-model-1.0-SNAPSHOT.jar consumer/target/jms-poc-cluster-jms-consumer-1.0-SNAPSHOT.jar ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/cons-02/deploy


