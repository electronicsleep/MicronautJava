# MicronautJava

Setup
```
brew install java11
brew install gradle
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
```
http://localhost:8080/swagger-ui


Tests
```
bash src/test/curl-tests.sh
bash src/test/pytest.sh
```

# Micronaut

https://micronaut.io/

https://github.com/micronaut-projects

https://docs.micronaut.io/latest/guide/index.html
