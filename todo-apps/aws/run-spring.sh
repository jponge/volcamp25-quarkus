#!/usr/bin/env bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://todos-instance-1.cniw42ess98v.eu-north-1.rds.amazonaws.com:5432/postgres
export SPRING_DATASOURCE_USERNAME=todos
export SPRING_DATASOURCE_PASSWORD=<your_password_here>
export SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop
java -Xmx128m -Xms128m -Dspring.profiles.active=production -jar todo-app-spring-1.0-SNAPSHOT.jar