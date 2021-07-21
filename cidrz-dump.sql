CREATE DATABASE [cidrz-assessment] 
GO

USE [cidrz-assessment]
GO

/* Create user login with name 'cidrz' and password cidrz_54321 */
/* then */
CREATE USER [cidrz] FOR LOGIN [cidrz] WITH DEFAULT_SCHEMA=[dbo]
GO

create table DicDoctors (docId int identity not null, docCode varchar(255), docName varchar(255), primary key (docId));
create table DicFacilities (facId int identity not null, facCode varchar(255), facImage varchar(64), facName varchar(255), primary key (facId));
create table DicTests (testId int identity not null, testCode varchar(255), testName varchar(255), testPrice int not null, primary key (testId));
create table Patients (patId int identity not null, dob datetime2 not null, firstName varchar(255), lastName varchar(255), nrc varchar(255), sex int not null, primary key (patId));
create table Requests (requestId int identity not null, accessNo int not null, receivedDate datetime2, docId int, facId int, patId int, primary key (requestId));
create table Tests (reqTestId int identity not null, resultValue int not null, testId int, requestId int, primary key (reqTestId));
alter table Requests add constraint FKdhg7p1hgg929rdn8jh4qnp398 foreign key (docId) references DicDoctors;
alter table Requests add constraint FKkbnr1ei05rlupk30yj4ry43n7 foreign key (facId) references DicFacilities;
alter table Requests add constraint FKpw0wn2eay41haqy27ksc6bcis foreign key (patId) references Patients;
alter table Tests add constraint FKkivk6f8yistrsoav7fhadiicu foreign key (testId) references DicTests;
alter table Tests add constraint FKkwm85dxfc9eokagsqkiur3llg foreign key (requestId) references Requests;

/* facilities */
INSERT INTO DicFacilities (facCode, facName) VALUES ('CHAI', 'CHAISA');
INSERT INTO DicFacilities (facCode, facName) VALUES ('TBCJ', 'TB-CHILENJE');
select * from DicFacilities;

/* doctors */
INSERT INTO dbo.DicDoctors (docCode, docName) VALUES ('ARV', 'Antiretroviral');
INSERT INTO dbo.DicDoctors (docCode, docName) VALUES ('EMRG', 'EMRG STUDY');
select * from DicDoctors;

/* PATIENT*/
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('JOHN', 'DOE', '123456/11/1', '01-01-1970', 1);
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('JANE', 'DOE', '123457/11/1', '01-01-1990', 1);
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('BWALYA', 'MUSONDA', '123458/11/1', '01-01-2000', 1);
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('MULENGA', 'CHIBWE', '123459/11/1', '01-01-2010', 2);
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('WELUZANI', 'BANDA', '123410/11/1', '01-01-2020', 2);
INSERT INTO Patients (firstName, lastName, nrc, dob, sex) VALUES ('WELLINGTON', 'ZIMBA', '123411/11/1', '01-01-2021', 2);
select * from Patients;

/* requests */
INSERT INTO Requests (receivedDate, accessNo, docId, facId, patId) VALUES ('05-05-2020', 123456, 1, 1, 1);
INSERT INTO Requests (receivedDate, accessNo, docId, facId, patId) VALUES ('12-12-2019', 123457, 2, 1, 2);
INSERT INTO Requests (receivedDate, accessNo, docId, facId, patId) VALUES ('12-31-2020', 123458, 2, 1, 2);
INSERT INTO Requests (receivedDate, accessNo, docId, facId, patId) VALUES ('08-15-2018', 123459, 1, 2, 4);
INSERT INTO Requests (receivedDate, accessNo, docId, facId, patId) VALUES ('07-28-2018', 123460, 2, 1, 3);
select * from [cidrz-assessment].dbo.Requests;

/* DIC TESTS*/
INSERT INTO DicTests (testCode, testName, testPrice) VALUES ('ALP', 'Alkaline phosphatase', 20);
INSERT INTO DicTests (testCode, testName, testPrice) VALUES ('TRIC', 'Trichomonas', 30);
INSERT INTO DicTests (testCode, testName, testPrice) VALUES ('HIVAS', 'HIV Aliqote', 15);
INSERT INTO DicTests (testCode, testName, testPrice) VALUES ('HIVRE', 'HIV Resistance', 80);
INSERT INTO DicTests (testCode, testName, testPrice) VALUES ('POLY', 'Polychromasia', 25);
select * from DicTests;

/* TESTS */
INSERT INTO Tests (resultValue, testId, requestId) VALUES (34, 1, 1);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (44, 1, 3);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (35, 2, 3);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (66, 3, 1);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (145, 2, 3);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (123, 3, 3);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (89, 2, 1);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (12, 3, 3);
INSERT INTO Tests (resultValue, testId, requestId) VALUES (11, 2, 1);
select * from Tests;

-- Question 1A
SELECT
	dt.testName AS "Test Name",
	t.resultValue AS "Result Value",
	r.receivedDate AS "Received Date",
	r.accessNo AS "Access No",
	df.facName AS "Facility Name",
	dd.docName AS "Doctor Name",
	CONCAT(p.firstName, ' ', p.lastName) AS "Patient Name",
	CAST(ROUND(DATEDIFF(DAY, p.dob, GETDATE()) / 365.2425, 2) AS DECIMAL(10, 2)) AS "Patient Age",
	-- 365.2425 = minimum number of days in 1 calendar year for the last 400 years
 	CASE
		WHEN p.sex = 1 THEN 'Male'
		WHEN p.sex = 2 THEN 'Female'
		ELSE '-'
	END AS "Sex"
FROM
	DicTests dt INNER JOIN Tests t ON t.testId = dt.testId
	INNER JOIN Requests r ON r.requestId = t.requestId
	INNER JOIN DicFacilities df ON df.facId = r.facId
	INNER JOIN DicDoctors dd ON dd.docId = r.docId
	INNER JOIN Patients p ON p.patId = r.patId
WHERE
	YEAR(r.receivedDate) = 2018;


-- Question 2A
SELECT
	TestAmountTble.TestCode AS "Test Code",
	SUM(TestAmountTble.TotalAmount) AS "Total Amount"
FROM
	(SELECT
		CASE
			WHEN dt.testCode = 'TRIC' THEN ROUND(dt.testPrice*0.50, 2)
			-- 50% translates to 0.5
			WHEN df.facCode = 'CHA'
			AND p.sex = '2'
			-- Female
			AND CAST(ROUND(DATEDIFF(DAY, p.dob, GETDATE()) / 365.2425, 2) AS DECIMAL(10, 2)) < 18
			-- 365.2425 is the mean num of days in a calendar year for the last 400 years
				THEN dt.testPrice / 2
			ELSE dt.testPrice
		END AS TotalAmount,
		dt.testCode AS TestCode
	FROM
		DicTests dt,
		Tests t,
		Requests r,
		DicFacilities df,
		Patients p
	WHERE
		t.testId = dt.testId
		AND r.requestId = t.requestId
		AND df.facId = r.facId
		AND r.patId = p.patId) AS TestAmountTble
GROUP BY
	TestAmountTble.TestCode ;

/*
Question 3A
Write a query which displays the 3rd most popular test at the lab. 
*/
SELECT
	*
FROM
	-- this gets us some more information and not just the count, also used incase there is more than one third place testcode.
(	SELECT
		COUNT(*) AS TestCount4,
		DT.TESTCODE AS TestCode
	FROM
		DICTESTS DT,
		TESTS T,
		REQUESTS R,
		DICFACILITIES DF,
		PATIENTS P
	WHERE
		T.TESTID = DT.TESTID
		AND R.REQUESTID = T.REQUESTID
		AND DF.FACID = R.FACILITYID
		AND R.PATIENTID = P.PATID
	GROUP BY
		DT.TESTCODE) AS MainTable4
WHERE
	MainTable4.TestCount4 = (
	SELECT
		MAX(MainTable3.TestCount3) AS ThirdHighestTest
		-- gets the third highest test in the main table defined. Basically looping three times.
	FROM 
		(SELECT
			COUNT(*) AS TestCount3,
			DT.TESTCODE AS TestCode
		FROM
			DICTESTS DT,
			TESTS T,
			REQUESTS R,
			DICFACILITIES DF,
			PATIENTS P
		WHERE
			T.TESTID = DT.TESTID
			AND R.REQUESTID = T.REQUESTID
			AND DF.FACID = R.FACILITYID
			AND R.PATIENTID = P.PATID
		GROUP BY
			DT.TESTCODE) AS MainTable3
	WHERE
		MainTable3.TestCount3 < (
		SELECT
			MAX(MainTable2.TestCount2) AS SecondHighestTest
			-- gets the second highest test from MainTable defined again here where the max is not the highest
		FROM 
			(SELECT
				COUNT(*) AS TestCount2,
				DT.TESTCODE
			FROM
				DICTESTS DT,
				TESTS T,
				REQUESTS R,
				DICFACILITIES DF,
				PATIENTS P
			WHERE
				T.TESTID = DT.TESTID
				AND R.REQUESTID = T.REQUESTID
				AND DF.FACID = R.FACILITYID
				AND R.PATIENTID = P.PATID
			GROUP BY
				DT.TESTCODE) AS MainTable2
		WHERE
			MainTable2.TestCount2 < 
			(SELECT
				MAX(MainTable.TestCount) AS HighestTest
			FROM
				-- gets the highest test count from the MainTable we have defined in
				-- the sub query below
				(SELECT
					COUNT(*) AS TestCount,
					DT.TESTCODE
				FROM
					DICTESTS DT,
					TESTS T,
					REQUESTS R,
					DICFACILITIES DF,
					PATIENTS P
				WHERE
					T.TESTID = DT.TESTID
					AND R.REQUESTID = T.REQUESTID
					AND DF.FACID = R.FACILITYID
					AND R.PATIENTID = P.PATID
				GROUP BY
					DT.TESTCODE) AS MainTable
				-- The main table that contains the total test done for each test code
			)
			-- end of highest count test count table
		)
		-- end of second highest test count in main table
	)
	-- end of third highest test count in main table
;

-- Method two, recommended for code integration, also utilises subquery to find the nth highest testcount
SELECT * -- main outer query
FROM
	(SELECT
		COUNT(*) AS TestCount,
		dt.testCode
	FROM
		DicTests dt,
		Tests t,
		Requests r,
		DicFacilities df,
		Patients p
	WHERE
		t.testId = dt.testId
		AND r.requestId = t.requestId
		AND df.facId = r.facId 
		AND r.patId = p.patId
	GROUP BY
		dt.testCode) MainTble -- main table with the all the testcodes and their test counts
WHERE (2) = ( 
-- 2 = third highest testcount from formula (N-1) = being the nth highest test code
-- any record with a count = 2 has 2 records higher than it so it is the third highest.
		SELECT COUNT(DISTINCT(MainTble2.TestCount2)) -- count all 
		FROM (SELECT
				COUNT(*) AS TestCount2,
				dt.testCode
			FROM
				DicTests dt,
				Tests t,
				Requests r,
				DicFacilities df,
				Patients p
			WHERE
				t.testId = dt.testId
				AND r.requestId = t.requestId
				AND df.facId = r.facId 
				AND r.patId = p.patId
			GROUP BY
				dt.testCode) MainTble2
		WHERE MainTble2.TestCount2 > MainTble.TestCount  
);

/*
Question 4A
Write a query which displays all lab requests. 
You should include the number of tests in that request and the test which had the highest result. 
*/

SELECT
	r.REQUESTID AS "Request ID",
	r.RECEIVEDDATE AS "Received Date",
	r.ACCESSNO AS "Access No",
	p.PATID AS "Patient ID",
	count(*) AS "Tests",
	HighestResultTble.TESTNAME AS "HighTest"
FROM
	REQUESTS r,
	TESTS t,
	PATIENTS p,
	-- temp table query to fetch tests with the highest results
	(SELECT
		dt.TESTID,
		dt.TESTNAME,
		TestResTble.HighestRes
	FROM
		DICTESTS dt,
		(SELECT
			t.TESTID AS TestID,
			max(t.RESULTVALUE) AS HighestRes
		FROM
			TESTS t
		GROUP BY
			t.TESTID) AS TestResTble
			-- main test result table with the test and 
			-- their corresponding highest test result values
	WHERE
		dt.TESTID IN (TestResTble.TestID) 
		) AS HighestResultTble
		-- this gets the details (testname, id and result) of each highest result value tests
WHERE
	t.REQUESTID = r.REQUESTID
	AND p.PATID = r.PATIENTID
	AND HighestResultTble.TESTID = t.TESTID
	-- match temp table's testid with the main query testid
	GROUP BY r.REQUESTID,
	r.RECEIVEDDATE,
	r.ACCESSNO,
	p.PATID,
	HighestResultTble.TESTNAME ;

