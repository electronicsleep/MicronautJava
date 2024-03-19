# MicronautJava

MicronautJava is a simple top score REST API/APP will write name/score to a SQL database

Example project to show Micronaut with pytest.

Why Micronaut: Fast startup, builds, cloudnative

Use SDKman: https://sdkman.io/
```
sdk install java 17.0.8-tem
sdk use java 17.0.8-tem
```

Setup MacOS
```
brew install openjdk@17 gradle
```

Setup Linux
```
apt-get install openjdk-17-jdk gradle
```

Database
```
git clone https://github.com/electronicsleep/mysql-docker-test.git && \
cd mysql-docker-test && ./run.sh
```

Set Overrides Properties: src/main/resources/overrides.properties

```
datasource_connection=jdbc:mysql://127.0.0.1:3306/infradb?useSSL=true&serverTimezone=UTC
datasource_password=password
datasource_user=infradb
```

Run
```
./gradlew run
curl localhost:8080
curl localhost:8080/health
curl localhost:8080/top-score | jq .
```

Tests
```
bash src/test/curl-tests.sh
bash src/test/pytest.sh
```

# Swagger

http://localhost:8080/swagger-ui

# Micronaut

https://micronaut.io/

https://github.com/micronaut-projects

https://docs.micronaut.io/latest/guide/index.html
