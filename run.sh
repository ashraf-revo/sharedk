#!/bin/bash
export JAVA_HOME=~/java8
mvn clean install -DskipTests=true -P embeded
cf push "${CF_APP}"