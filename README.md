# GeoServices
Test Postgis via SOAP calls

This is a sample Spring Boot application demonstrating the use of Spring Web services and Postgis.

## Installation instructions

First initialize the database:
1. Download and install Postgres 9.6 (https://www.postgresql.org/download/)
2. Start the database
4. Create a user as super user geodbuser (as superuser) with password geodbuser.
5. Create a database named geodb assigned to geodbuse.
6. Download and install PostGIS https://postgis.net/install/
7. Enable Postgis extensions for the specific database

Then compile the application:
1. git clone https://github.com/geomark/GeoService.git
2. cd <installation floder>
3. mvn clean install (you must have maven 3.5 installed)
5. cd target
6. java -jar <generated-jarname>.jar

## Usage Instructions
When the project has successfully compiled:

1. cd target
2. java -jar <generated-jarname>.jar

The Spring Boot application will start automatically and if the database is up and running and properly configured 
it will generate the appropriate tables (you still need to import some data manually though).

The WSDL is available at http://localhost:8080/ws/points.wsdl

You can use a tool like SOAPUI to play around...
