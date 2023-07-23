# Project: spring-batch-poc

# Build Command
    ./gradlew clean build

# 1 - Docker Command
    cd docker-files
    docker-compose up

# 2 - Run Command
    ./gradlew bootRun --args='--spring.profiles.active=worker'
    ./gradlew bootRun --args='--spring.profiles.active=manager'
