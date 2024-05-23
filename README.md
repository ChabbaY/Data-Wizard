# Big Data Datenaufbereitung

## Running it
Docker services:
- needs only docker engine: `docker-compose up`
- database on port <b>3306</b>
  - mysql image
- phpmyadmin on port <b>8080</b>
  - phpmyadmin image
- api on port <b>5555</b>
  - spring application (needs to be packaged first)
- frontend on port <b>4200</b>
  - angular frontend

Spring application:
- needs java 17: Spring run configuration
- on port 5555
- swagger UI at /swagger-ui/index.html

Angular frontend:
- needs angular cli: `ng serve` or angular run configuration
- on port 4200

## Development
Database root password: pfopSDuTXh6Am5yLX7dyFcPWt

Use `docker-compose up --build`

## Usage
- Example projects from 'Big Data in der Praxis'
  - https://github.com/StefanPapp/bigdata_buch

### Spring Boot
- Spring Framework (version 3.2.5)
- Springdoc Openapi for Swagger-UI generation
  - https://github.com/springdoc/springdoc-openapi
- Configure CORS for Spring Boot
  - https://spring.io/guides/gs/rest-service-cors
- Return String response as JSON
  - https://stackoverflow.com/a/42837009

### Angular
- Angular Framework (version 17.3.4)
- How to adapt Dockerfile to Angular 17
  - https://stackoverflow.com/questions/77850159/how-to-deploy-angular-17-with-ssr-with-docker

### NLP
- Train a Classifier with Apache OpenNLP
  - https://opennlp.apache.org/docs/2.3.3/manual/opennlp.html#tools.doccat.classifying
