# cidrz-assessment
The answers to Questions 1A, 2A, 3A, and 4A are marked respectively and can be opened in notepad or any sql reader of your choice.

The assessment utilises Spring Boot, Spring Data Jpa and Spring Web Services. 

Question 2
The xml document can be accessed on the path /request/{requestId} for example, if you want to see details for requestId=1 then go to 
http://localhost:8080/request/1
this will show you the results in a valid XML format.

Question 3
I opted for web application. To add a facility go to /webapp/facility i.e.
http://localhost:8080/webapp/facility
1. Input facility code, name, and upload picture.
2. Picture is uploaded to the root folder /cidrz/facility/{facId}/ where facId = Facility Id in the DB
3. You can view facility details on http://localhost:8080/webapp/facility-detail?facId={facId} where facId = Facility Id in the DB

Note: The web app does not allow users to save the form while offline and upload when they are back online. 


 



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