# sample-springboot-swagger

## About
Application to demonstrate Swagger 2 with spring boot application, using the Springfox implementation of the Swagger 2 specification and h2 in-memory database.

## Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 3.3.9+

## Swagger
Swagger is most popular framework for describing REST APIs using a common language that everyone can understand.

## In Memory DB (H2)
Sample data for the application will be created during application starup and gets destroyed after the application stops
<br/>
Creation of database data is done using sql script [data.sql](./src/main/resources/data.sql) which can be found at '/src/main/resources'
H2 console can be accessed from http://localhost:8080/h2-console/

## Verify Swagger2 JSON Format Docs
Do maven build and Start the server. Open the link http://localhost:8080/v2/api-docs and it should give the entire documentation in JSON format.

## Verify Swagger2 UI Docs
Open http://localhost:8080/swagger-ui.html# to see the Swagger UI documentation in the browser.
