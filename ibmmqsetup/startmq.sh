
#!/bin/bash

echo "Let's clean up the environment by taking the container down..."
docker-compose --log-level WARNING down --remove-orphans
# spins up the docker container for the mq
echo "Let's spin up the container..."
docker-compose build
docker-compose -f docker-compose.yml up --detach
# The queues that are created here are also available within application.yaml.
# If you add one here, make sure it aligns with application.yaml
echo "Waiting for Active MQ to start"
sleep 10
echo "INFO Creating the MQ queues..."
docker exec ibmmq runmqsc -f qlocalconfig