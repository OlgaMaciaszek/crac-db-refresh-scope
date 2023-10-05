# Checkpoint-Restore sample with Spring Cloud Context Refresh and Hikari DataSource


## Introduction

In this sample, we've added `org.springframework.cloud:spring-cloud-starter` to the pom to provide the Context Refresh functionality. In the properties we've pointed to an external configuration source for defined using `spring.config.import` property. Frequently, users point this to a  [Spring Cloud Config Server](https://docs.spring.io/spring-cloud-config/reference/4.1/server.html), but in this example we're just using a file (`dev.yml`). We have placed all the configuration for `DataSource` and `HikariDataSource` beans there, and we have listed both beans under `spring.cloud.refresh.extra-refreshables` in the property file. Thanks to this, both beans (that come from `spring-boot-starter-data-jpa`) will have the `refresh` scope, even though they have not been annotated with `@RefreshScope`. Consequently, if the data in the external configuration source changes between creating checkpoint and restoring the image, `RefreshScopeLifecycle` will be triggered under the hood and the `DataSource` bean will be recreated with new values (in the case of this sample, new `password` value). You can read more about Refresh Scope on restart [here](https://docs.spring.io/spring-cloud-commons/reference/4.1/spring-cloud-commons/application-context-services.html#_refresh_scope_on_restart).

On the running application, you can also update the values of the config source as required and trigger [refreshing the context again with Actuator](https://docs.spring.io/spring-cloud-commons/reference/4.1/spring-cloud-commons/application-context-services.html#refresh-scope).

TIP: If you would like to see a setup with a user-crated datasource bean, check out the `user-created-hikari-datasource` branch.

## Prerequisites

- CRaC enabled JDK such as [the one provided by Azul](https://www.azul.com/downloads/?package=jdk-crac#zulu).
- Operating system compatible with CRac-enabled JDK (at this point Linux or Windows via WSL2)
- Docker to run external services such as database servers.

## Running the sample

1. `docker compose up` to run Postgres.
2. `./mvnw clean install` to build a jar.
3. `java -XX:CRaCCheckpointTo=/tmp/cr -jar target/crac-db-refresh-scope-0.0.1-SNAPSHOT.jar` to run the jar in training mode.
4. Run some HTTP requests that retrieve data from Postgres:  `http GET :8080/users`.
5. Create checkpoint and shut down: `jcmd target/crac-db-refresh-scope-0.0.1-SNAPSHOT.jar JDK.checkpoint`.
6. In the meantime, change database password:
   - run bash in db container `docker exec -it db bash`
   - connect to database with psql `psql -U user -d database`
   - change password using `\password` command
7. Set the `password` field in `dev.yml` to the new value.
8. Restore the application from checkpoint: `java -XX:CRaCRestoreFrom=/tmp/cr`.
9. Run some HTTP requests that retrieve data from Postgres:  `http GET :8080/users`.

Since after restoring the application, the context has been refreshed and the `DataSource`bean has been recreated with the new database password, the application can connect to the database even after the password has changed. If you try following these steps without updating the `dev.yml` file, you will get 500s while trying to retrieve users over HTTP from the restored application.


