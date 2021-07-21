# cidrz-assessment
This is an assessment submission for the CIDRZ Interview Assessment questions.
All answers to the questions are in this repo except for Question 4 which is in the 'cidrz-reactapp' repository.

## Tech used
1. Java
2. Spring Boot, Spring Data Jpa and Spring Web Services. 
3. MSSQL 
4. Maven

## MSSQL Requirement
1. Create a database called `cidrz-assessment` 
2. Add a login user with name `cidrz` and password `cidrz_54321`
3. Give the user `dbowner`, `db_ddladmin` rights on the database `cidrz-assessment`.

## Application
Runs on java 8
Navigate to the root folder
Build the application using `mvn clean install`
Run using `java -jar target/assessment-0.0.1-SNAPSHOT.jar`

### Question 1

The answers to Questions 1A, 2A, 3A, and 4A are marked respectively and can be opened in notepad or any SQL reader of your choice.
[e.g. The answer to Question 1A is in the file named "Question 1A.sql", Question 2A in "Question 2A.sql"] etc. 


### Question 2
The xml document can be accessed on the path /request/{requestId} for example, if you want to see details for requestId=1 then go to 
http://localhost:8080/request/1
this will show you the results in a valid XML format.

### Question 3
I opted for web application. To add a facility go to /webapp/facility i.e.
[http://localhost:8080/webapp/facility](http://localhost:8080/webapp/facility)
1. Input facility code, name, and upload picture.
2. Picture is uploaded to the root folder /cidrz/facility/{facId}/ where facId = Facility Id in the DB
3. You can view facility details on [http://localhost:8080/webapp/facility-detail?facId={facId}](http://localhost:8080/webapp/facility-detail?facId={facId}) where facId = FacilityId in the DB

The web app interact with a rest endpoint at http://localhost:8080/json/dic-facilities which accepts json form data.

Note: The web app does not allow users to save the form while offline and upload when they are back online. 

### Question 4
The react app is in the repository called cidrz-reactapp
It interacts with the rest api at [http://localhost:8080/dic-tests](http://localhost:8080/dic-tests)

To use ths app follow the simple steps below;\
Create a folder where you would like the application to sit, e.g. `mkdir cidrz-reactapp`\
Navigate to that folder\
Clone the app using `git clone https://github.com/lusungu/cidrz-reactapp.git`\
Start the react app using the command `npm run start`
the application will run on http://localhost:3000/ showing all the dic-tests and their respective prices.

### Question 5
The answer to question 5 is found in the file called "Question 5.txt" 