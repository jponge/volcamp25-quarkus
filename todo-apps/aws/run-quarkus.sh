#!/usr/bin/env bash
export QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://todos-instance-1.cniw42ess98v.eu-north-1.rds.amazonaws.com:5432/postgres
export QUARKUS_DATASOURCE_USERNAME=todos
export QUARKUS_DATASOURCE_PASSWORD=<your_password_here>
export QUARKUS_HIBERNATE_ORM_DATABASE_GENERATION=drop-and-create
java -Xmx128m -Xms128m -jar quarkus-run.jar