#!/usr/bin/env bash
set -eo pipefail
set -o errexit

echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

docker tag haystack-opentelemetry-example expediadotcom/haystack-opentelemetry-example:${SHA}
docker push expediadotcom/haystack-opentelemetry-example:${SHA}

docker tag haystack-opentelemetry-example expediadotcom/haystack-opentelemetry-example:latest
docker push expediadotcom/haystack-opentelemetry-example:latest