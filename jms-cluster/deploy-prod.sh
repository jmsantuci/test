#!/bin/bash

rm ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/prod-01/deploy/jms-poc-cluster-jms-*
rm ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/prod-02/deploy/jms-poc-cluster-jms-*
cp model/target/jms-poc-cluster-jms-model-1.0-SNAPSHOT.jar producer/target/jms-poc-cluster-jms-producer-1.0-SNAPSHOT.jar ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/prod-01/deploy
cp model/target/jms-poc-cluster-jms-model-1.0-SNAPSHOT.jar producer/target/jms-poc-cluster-jms-producer-1.0-SNAPSHOT.jar ~/dev/test/jboss-6.0.0-poc-jms-cluster/server/prod-02/deploy


