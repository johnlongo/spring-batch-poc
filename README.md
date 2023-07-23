# Project: spring-batch-poc

    from:   https://www.youtube.com/watch?v=alzvJt7ddc0
            https://www.youtube.com/watch?v=VMFYsuc1uEg
            https://www.youtube.com/watch?v=XzgiCiSgNc0

# Build Command
    ./gradlew clean build

# 1 - Docker Command
    cd docker-files
    docker-compose up

# 2 - Run Command
    ./gradlew bootRun --args='--spring.profiles.active=worker'
    ./gradlew bootRun --args='--spring.profiles.active=manager'
