#!/bin/bash
shm_size=1g
JAVA_OPTS="-Xmx${shm_size} -XX:+UseConcMarkSweepGC -XX:NativeMemoryTracking=summary"
java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /usr/src/gs-spring-boot/target/gs-spring-boot-0.1.0.jar

