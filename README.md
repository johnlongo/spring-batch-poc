# Project: spring-batch-poc
    from:   https://www.youtube.com/watch?v=alzvJt7ddc0
            https://www.youtube.com/watch?v=VMFYsuc1uEg
            https://www.youtube.com/watch?v=XzgiCiSgNc0

# Build Command
    ./gradlew clean build

# 1 - Docker Command Start docker-compose first
    docker run --hostname=localhost  -p 3306:3306 --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=MYSQL_ROOT_PASSWORD=password --env=MYSQL_ROOT_HOST="%" -env=GOSU_VERSION=1.16 --env=MYSQL_MAJOR=8.0 --env=MYSQL_VERSION=8.0.33-1.el8 --env=MYSQL_SHELL_VERSION=8.0.33-1.el8 --volume=/var/lib/mysql --runtime=runc -d mysql:latest
    docker-compose up
    monitor Kafka http://localhost:8080/ui/docker-kafka-server/topic

# 2 - Run Command start one or more workers
    Important delete sales-chunkRequests and sales-chunkReplies topics before you start the worker
    ./gradlew bootRun --args='--spring.profiles.active=worker'

# 3 - Run Command start the manager  
    ./gradlew bootRun --args='--spring.profiles.active=manager'
