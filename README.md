# DHIS2 Docker

## About

DHIS2 is the world's largest public health information system.
This is a script for deploying DHIS2 in docker with RabbimMQ messaging.
An example RabbitMQ java client is included.

## Changes from the parent repository
* dhis.war download link changed.
* dhis.conf copied to dhis2app folder and is copied at the time of container creation.
* RabbitMQ java client code added.

## How to use
* make changes to dhis2/dhis2app/dhis.conf
* change the port numbers as desired in the docker-compose.yml

```
docker-compose up 

```
