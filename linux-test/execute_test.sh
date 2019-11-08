#!/bin/bash
dos2unix /usr/local/bin/build/src/main/resources/libs/linux/*
cp -a /usr/local/bin/build/src/main/resources/libs/linux/. /usr/lib/
cd /usr/local/bin/build
./gradlew test