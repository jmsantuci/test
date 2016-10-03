#!/bin/bash

mvn exec:java -Dexec.mainClass='jms.test.jndi.Lookup' -Dexec.args="$1"

