# Family Tree API

* Run `mvn clean install` to create the artifacts.
* A file named `family-tree-api-1.0.0.jar` should appear in the `target` directory.
* Run `java -jar family-tree-api-1.0.0.jar` to start the application.
* The application can be accessed on [http://localhost:8080](http://localhost:8000)
* The UI is seperately developed at https://github.com/kensplanet/family-tree

## Technologies
* **Java 11** 
* **Spring Boot** - contains packages to accelerate project development
* **Spring MVC** - to design REST calls
* **Spring Security** - authentication and security

## Database
* The application uses in-memory H2 database for fast development.
* It can be accessed through [http://localhost:8080/h2-console](http://localhost:8000/h2-console) with the below credentials,
  * JDBC URL: jdbc:h2:mem:test-db;MODE=DB2;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
  * User Name: sa
  * Password: (blank)
