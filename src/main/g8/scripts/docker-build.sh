#!/bin/bash

# Build the docker file and tag it with some relevant tags
# Make sure you're authenticated with the docker repository first

REGISTRY_HOST='778379310836.dkr.ecr.eu-west-1.amazonaws.com'
IMAGE="\$REGISTRY_HOST/dde-feedbank-$sport;format="hyphen,lower"$"
COMMIT_TAG="\$(git rev-parse --short HEAD)"
BRANCH_TAG="\$(git rev-parse --abbrev-ref HEAD)"

docker build \
  --build-arg AWS_ACCESS_KEY_ID="\$AWS_ACCESS_KEY_ID" \
  --build-arg AWS_SECRET_ACCESS_KEY="\$AWS_SECRET_ACCESS_KEY" \
  --build-arg AWS_DEFAULT_REGION="\$AWS_DEFAULT_REGION" \
  -t "\$IMAGE:latest" \
  . || exit "\$?"

docker tag "\$IMAGE:latest" "\$IMAGE:\$COMMIT_TAG"
docker tag "\$IMAGE:latest" "\$IMAGE:\$BRANCH_TAG"

docker push "\$IMAGE:latest" || exit "\$?"
docker push "\$IMAGE:\$COMMIT_TAG"
docker push "\$IMAGE:\$BRANCH_TAG"
