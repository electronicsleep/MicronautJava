#!/bin/bash
set -ex
./gradlew clean build
ls -lh ./build/libs/*
