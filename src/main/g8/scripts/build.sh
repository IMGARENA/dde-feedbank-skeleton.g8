#!/bin/bash

# A "full" build, including coverage and assembly

sbt \
  clean \
  coverage \
  test \
  coverageReport \
  coverageOff \
  service/assembly
