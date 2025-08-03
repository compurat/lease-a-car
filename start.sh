#!/bin/sh

# Load environment variables from .env file
if [ -f /app/.env ]; then
  export $(grep -v '^#' /app/.env | xargs)
  echo "Environment variables loaded from .env file"
else
  echo "Warning: .env file not found"
fi

#
java -jar /app/Lease-a-leaseCar-0.0.1-SNAPSHOT.jar

# Keep the container running
wait
