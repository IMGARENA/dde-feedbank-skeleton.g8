# Feedbank $sport;format="Camel"$

Feedbank service for $sport$. **FIXME**: Fill in additional details about this service.

## Build

To build the project, use `scripts/build.sh`, which will run a full SBT build with coverage.

You can also build a ready-to-run docker image with `scripts/docker-build.sh`, which will also
tag your image with the branch, short commit, and "latest", and push it to ECR. You'll need to log
into ECR first for the latter to be successful.
