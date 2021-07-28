### :computer: How to execute

1. Start postgres db docker container
   
    a. Through mvn command -> mvn package -f pom.xml
    
    b. manually -> open terminal in src/main/resources/dependency/docker-compose/ and run docker-compose up -d
   
    c. db schema scripts dir -> src/main/resources/scripts

    d. db schema credentials ->  src/main/resources/application.properties


2. Start PartnerService
   
   a. open terminal in src/main/resources/dependency/partner-service
   
   b. execute command -> java -jar .\partner-service-1.0-all.jar --port=8080 --min=<min> --max=<max>


3. run Spring boot main class -> com.trade_republic.quotation.InstrumentQuotationApplication.java
   
    a. Please note The project is developed in Java 11 so make sure you have a compatible JDK.

4. use these to apis to retrieve instrument latest and historical data

   a. Latest -> http://localhost:8081/instruments/quote
   
   b. Historical -> http://localhost:8081/instruments/{isin}/quotes

### :memo: Notes

1. docker-compose.yml
   
   a. contains configs for postgres sql db container
  
 
2. pom.xml 
   
   a. added plugin to start containers by maven
   
   b. clean life cycle would run docker-compose down
   
   c. compile life cycle would run docker-compose up -d 
   

3. Frameworks used
    
    a. Spring boot
    
    b. Hibernate

    c. Spring websocket

    e. Lombok


4. Programming paradigms applied

    a. Interface-based architecture

    b. Solid architecture


5. Conventions
   
    a. Packages naming convention

        I.   Singular names

        II.  model refers to transfere objects 

        III. entity refers to db table objects

        IV.  contract refers to interfaces

        V.   service refers to bussiness services
   
    b. Classes naming convention
        
        I.  Singular Pascal case
   
    d. Git Commits -> conventional commits format 


6. Project Structure

   a. Layered structure

        I.   data -> package for enums/models/entities + associated contracts

        II.  repository -> package for database repositories

        III. service -> package for business services + associated contracts 

        IV.  infrastructre -> package for configClasses/exceptions/utils/websocketConfig&Handlers
   b. Configurations: in application.properties file managed by ConfigurationManager class

### :pushpin: Things to improve

1. Applying TDD and unit test and integration test coverage
2. Using spring flux for reactive websockets
3. Use nosql db


