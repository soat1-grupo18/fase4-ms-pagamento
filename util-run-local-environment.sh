#!/bin/bash

set -ev

docker-compose -f docker-compose-local.yml up --force-recreate --renew-anon-volumes

