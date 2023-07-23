# Project: spring-batch-poc

# Build Command
    ./gradlew clean build

# Docker Command

    cd docker-files
    docker-compose up

# Run Command
    ./gradlew bootRun --args='--spring.profiles.active=manager'
    ./gradlew bootRun --args='--spring.profiles.active=worker'