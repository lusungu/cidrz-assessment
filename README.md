# cidrz-assessment

## Tech used
Java
Spring Boot, Spring Data Jpa and Spring Web Services. 
MSSQL 

###Question 1
The answers to Questions 1A, 2A, 3A, and 4A are marked respectively and can be opened in notepad or any sql reader of your choice.
The answer to Question 1A is in the file named "Question 1A.sql" etc. 


### Question 2
The xml document can be accessed on the path /request/{requestId} for example, if you want to see details for requestId=1 then go to 
http://localhost:8080/request/1
this will show you the results in a valid XML format.

### Question 3
I opted for web application. To add a facility go to /webapp/facility i.e.
http://localhost:8080/webapp/facility
1. Input facility code, name, and upload picture.
2. Picture is uploaded to the root folder /cidrz/facility/{facId}/ where facId = Facility Id in the DB
3. You can view facility details on http://localhost:8080/webapp/facility-detail?facId={facId} where facId = Facility Id in the DB

The web app interact with a rest endpoint at http://localhost:8080/json/dic-facilities which accepts json form data.

Note: The web app does not allow users to save the form while offline and upload when they are back online. 

### Question 4
The react app is in the repository called cidrz-reactapp
It interacts with the rest api at http://localhost:8080/dic-tests

First start the react app using the command
npm run start
the application will run on http://localhost:3000/ showing all the dic-tests and their respective prices.

### Question 5
The answer to question 5 is found in the file called "Question 5.txt" 





 



## MSSQL User details
First create the DB 'cidrz-assessment-test'
Then create a user called cidrz with password 'cidrz_54321' without the quotes, give the user dbowner and admin rights.
Simple, not tested query you can use;

CREATE DATABASE cidrz-assessment-test;
Use cidrz-assessment-test;
GO
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'cidrz')
BEGIN
    CREATE USER cidrz 
	    WITH PASSWORD    = N'cidrz_54321',
	    CHECK_POLICY     = OFF,
	    CHECK_EXPIRATION = OFF;
    EXEC sp_addrolemember N'db_owner', N'cidrz'
END;
GO