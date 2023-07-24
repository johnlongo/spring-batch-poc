# Project: spring-batch-poc
    from:   https://www.youtube.com/watch?v=alzvJt7ddc0
            https://www.youtube.com/watch?v=VMFYsuc1uEg
            https://www.youtube.com/watch?v=XzgiCiSgNc0

# Build Command
    ./gradlew clean build

# 1 - Docker Command Start docker-compose first
    cd docker-files
    docker-compose up
    monitor Kafka http://localhost:8080/ui/docker-kafka-server/topic

# 2 - Run Command start one or more workers
    Important delete sales-chunkRequests and sales-chunkReplies topics before you start the worker
    ./gradlew bootRun --args='--spring.profiles.active=worker'

# 3 - Run Command start the manager  
    ./gradlew bootRun --args='--spring.profiles.active=manager'
