#!/bin/bash

curl --request PUT --data 'microservice-patterns-bucket' --url 'http://localhost:8500/v1/kv/config/application/cloud.uploadBucket'
curl --request PUT --data 'file:gcpServiceAccount.json' --url 'http://localhost:8500/v1/kv/config/application/spring.cloud.gcp.credentials.location'
curl --request PUT --data '.localUploadDir' --url 'http://localhost:8500/v1/kv/config/application/local.uploadDir'