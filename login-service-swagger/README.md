# springboot-swagger-login-application

## About
Application to demonstrate Login service using Swagger 2 with spring boot application, using the Springfox implementation of the Swagger 2 specification and h2 in-memory database.

## Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 3.3.9+

## Swagger
Swagger is most popular framework for describing REST APIs using a common language that everyone can understand.

## H2
H2 console can be accessed from http://localhost:8080/h2-console/

## Verify Swagger2 JSON Format Docs
Do maven build and Start the server. Open the link http://localhost:8080/v2/api-docs and it should give the entire documentation in JSON format.

## Verify Swagger2 UI Docs
Open http://localhost:8080/swagger-ui.html# to see the Swagger UI documentation in the browser.

##POST /createAdmin
{
  "email": "admin@abc.com",
  "mobileNumber": "1234567890",
  "username": "admin1",
  "password": "admin"
}
{
  "email": "admin1@abc.com",
  "mobileNumber": "1234567890",
  "username": "admin2",
  "password": "admin"
}

##POST /admin/login
{
  "password": "admin",
  "userId": "admin@abc.com"
}
{
  "password": "admin",
  "userId": "admin2@abc.com"
}

##POST /createUser
{
  "email": "user1@gmail.com",
  "mobileNumber": "1111111111",
  "primarySkill": "UI_DEVELOPER",
  "skillDescription": "",
  "username": "user1"
}

{
  "email": "user2@gmail.com",
  "mobileNumber": "2222222222",
  "primarySkill": "BACKEND_DEVELOPER",
  "skillDescription": "",
  "username": "user1"
}
{
  "email": "user3@gmail.com",
  "mobileNumber": "3333333333",
  "primarySkill": "BACKEND_DEVELOPER",
  "skillDescription": "",
  "username": "user1"
}

##POST /user/login
{
  "password": "1111111111",
  "userId": "user1@gmail.com"
}
{
  "password": "2222222222",
  "userId": "user2@gmail.com"
}
{
  "password": "3333333333",
  "userId": "user3@gmail.com"
}
##PUT /user/profileUpdate/{description}
