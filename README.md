# Account Service

### Brainstorming

- accountStatus: during User registration, accountStatus is set to `PENDING` until the Account event is processed.
- accountStatus can be `ACTIVE`, `DISABLED`, `PENDING`.
- statusReason: INACTIVITY, FRAUD, USER_REQUEST, NULL (is accountStatus is ACTIVE), CREATE_ACCOUNT_FAILED.

### Kafka commands
```
$ docker exec -it kafka1 bash
$ kafka-topics --create --topic <TOPIC_NAME> --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
$ kafka-topics --list --bootstrap-server localhost:9092
$ kafka-console-producer --topic my-topic --bootstrap-server localhost:9092

> {"sean": "borland", "age": 41} PRESS ENTER to send the message
> {"john": "doe", "age": 30} PRESS ENTER to send the message
Ctrl+C to exit producer

$ kafka-console-consumer --topic my-topic --bootstrap-server localhost:9092 --from-beginning
$ kafka-topics --delete --topic my-topic --bootstrap-server localhost:9092
$ docker-compose down -v # Stop and remove containers, networks, volumes, and images created by `up`
$ docker system prune -f # Remove unused Docker data
```
### Requirements
- TODO

### URLs
[Swagger UI](http://localhost:8080/swagger-ui/index.html#/account-controller)
