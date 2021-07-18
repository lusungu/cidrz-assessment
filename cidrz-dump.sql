/* facilities */
DROP TABLE IF EXISTS DICFACILITIES;
CREATE TABLE DICFACILITIES(
   FACID   INT NOT NULL,
   FACCODE VARCHAR (20)     NOT NULL,
   FACNAME  VARCHAR (255)              NOT NULL,      
   PRIMARY KEY (FACID)
);

INSERT INTO DICFACILITIES VALUES (1, 'CHAI', 'CHAISA');
INSERT INTO DICFACILITIES VALUES (2, 'TBCJ', 'TB-CHILENJE');
INSERT INTO DICFACILITIES VALUES (3, 'ARV-L', 'ARV-LINDA');
INSERT INTO DICFACILITIES VALUES (4, 'HIV-CH', 'HIV-CHAWAMA');
INSERT INTO DICFACILITIES VALUES (5, 'CV-EM', 'CV-EMMASDALE');

SELECT * FROM DICFACILITIES;

/* doctors */
CREATE TABLE DICDOCTORS(
   DOCID   INT              NOT NULL,
   DOCCODE VARCHAR (20)     NOT NULL,
   DOCNAME  VARCHAR (255)              NOT NULL,      
   PRIMARY KEY (DOCID)
);
INSERT INTO DBO.DICDOCTORS VALUES (1, 'ARV', 'Antiretroviral');
INSERT INTO DBO.DICDOCTORS VALUES (2, 'EMRG', 'EMRG STUDY');
INSERT INTO DBO.DICDOCTORS VALUES (3, 'TB', 'TUBERCULOSIS');
INSERT INTO DBO.DICDOCTORS VALUES (4, 'HIV', 'HIV AND AIDS');
INSERT INTO DBO.DICDOCTORS VALUES (5, 'OBGY', 'OBS AND GYN');

/* PATIENT*/
DROP TABLE IF EXISTS PATIENTS;
CREATE TABLE PATIENTS(
   PATID INT NOT NULL,
   FNAME VARCHAR (255) NOT NULL,
   LANAME VARCHAR (255) NOT NULL,
   NRC VARCHAR (255) NOT NULL, 
   DOB DATE NOT NULL, 
   SEX INT NOT NULL, 
   PRIMARY KEY (PATID)
);
INSERT INTO PATIENTS VALUES (1, 'JOHN', 'DOE', '123456/11/1', '01-01-1970', 1);
INSERT INTO PATIENTS VALUES (2, 'JANE', 'DOE', '123457/11/1', '01-01-1990', 1);
INSERT INTO PATIENTS VALUES (3, 'BWALYA', 'MUSONDA', '123458/11/1', '01-01-2000', 1);
INSERT INTO PATIENTS VALUES (4, 'MULENGA', 'CHIBWE', '123459/11/1', '01-01-2010', 2);
INSERT INTO PATIENTS VALUES (5, 'WELUZANI', 'BANDA', '123410/11/1', '01-01-2020', 2);
INSERT INTO PATIENTS VALUES (6, 'WELLINGTON', 'ZIMBA', '123411/11/1', '01-01-2021', 2);

SELECT * FROM PATIENTS;

/* requests */
CREATE TABLE REQUESTS(
   REQUESTID INT NOT NULL,
   RECEIVEDDATE DATE NOT NULL,
   ACCESSNO INT NOT NULL
   PRIMARY KEY (REQUESTID),
   FACILITYID INT FOREIGN KEY REFERENCES DICFACILITIES(FACID),
   DOCTORID INT FOREIGN KEY REFERENCES DICDOCTORS(DOCID),
   PATIENTID INT FOREIGN KEY REFERENCES PATIENTS(PATID)
);
INSERT INTO REQUESTS VALUES (1, '05-05-2020', 123456, 1, 1, 1);
INSERT INTO REQUESTS VALUES (2, '08-30-2018', 123457, 2, 5, 4);
INSERT INTO REQUESTS VALUES (3, '09-11-2018', 123458, 3, 4, 2);
INSERT INTO REQUESTS VALUES (4, '10-25-2019', 123459, 1, 3, 3);
INSERT INTO REQUESTS VALUES (5, '11-06-2021', 1234510, 4, 2, 5);

INSERT INTO REQUESTS VALUES (6, '11-08-2021', 234510, 1, 2, 3);
INSERT INTO REQUESTS VALUES (7, '05-09-2021', 123410, 1, 2, 4);
INSERT INTO REQUESTS VALUES (8, '02-06-2021', 12345102, 1, 2, 5);
INSERT INTO REQUESTS VALUES (9, '01-06-2021', 12345103, 1, 2, 5);
INSERT INTO REQUESTS VALUES (10, '04-06-2021', 12345104, 1, 2, 5);
SELECT * FROM REQUESTS;

/* DOCTOR REQUESTS */
CREATE TABLE DOCTORS(
   REQDOCID INT NOT NULL,
   PRIMARY KEY (REQDOCID),
   REQUESTID INT FOREIGN KEY REFERENCES REQUESTS(REQUESTID),
   DOCID INT FOREIGN KEY REFERENCES DICDOCTORS(DOCID)
);

INSERT INTO DOCTORS VALUES (1, 1 , 1);
INSERT INTO DOCTORS VALUES (2, 2 , 5);
INSERT INTO DOCTORS VALUES (3, 3 , 3);
INSERT INTO DOCTORS VALUES (4, 4 , 2);
INSERT INTO DOCTORS VALUES (5, 5 , 1);

SELECT * FROM DOCTORS;


/* FACILITIES - REQUESTS*/
CREATE TABLE FACILITIES(
   REQFACID INT NOT NULL,
   PRIMARY KEY (REQFACID),
   REQUESTID INT FOREIGN KEY REFERENCES REQUESTS(REQUESTID)
);

INSERT INTO FACILITIES VALUES (1,1);
INSERT INTO FACILITIES VALUES (2,2);
INSERT INTO FACILITIES VALUES (3,3);
INSERT INTO FACILITIES VALUES (4,4);
INSERT INTO FACILITIES VALUES (5,2);


/* DIC TESTS*/
CREATE TABLE DICTESTS(
   TESTID INT NOT NULL,
   TESTCODE VARCHAR (255) NOT NULL,
   TESTNAME VARCHAR (255) NOT NULL,
   TESTPRICE INT NOT NULL
   PRIMARY KEY (TESTID)
);

INSERT INTO DICTESTS VALUES (1, 'ALP', 'Alkaline phosphatase', 20);
INSERT INTO DICTESTS VALUES (2, 'TRIC', 'Trichomonas', 30);
INSERT INTO DICTESTS VALUES (3, 'HIVAS', 'HIV Aliqote', 15);
INSERT INTO DICTESTS VALUES (4, 'HIVRE', 'HIV Resistance', 80);
INSERT INTO DICTESTS VALUES (5, 'POLY', 'Polychromasia', 25);

/* TESTS */
CREATE TABLE TESTS(
   REQTESTID INT NOT NULL,
   RESULTVALUE INT NOT NULL,
   PRIMARY KEY (REQTESTID),
   TESTID INT FOREIGN KEY REFERENCES DICTESTS(TESTID),
   REQUESTID INT FOREIGN KEY REFERENCES REQUESTS(REQUESTID)
);

INSERT INTO TESTS VALUES (1, 34, 1, 1);
INSERT INTO TESTS VALUES (2, 44, 1, 5);
INSERT INTO TESTS VALUES (3, 35, 1, 2);
INSERT INTO TESTS VALUES (4, 66, 1, 4);
INSERT INTO TESTS VALUES (5, 145, 1, 3);
INSERT INTO TESTS VALUES (6, 123, 1, 1);
INSERT INTO TESTS VALUES (7, 89, 1, 1);
INSERT INTO TESTS VALUES (8, 12, 1, 2);
INSERT INTO TESTS VALUES (9, 11, 1, 1);

INSERT INTO TESTS VALUES (10, 34, 2, 1);
INSERT INTO TESTS VALUES (11, 44, 3, 5);
INSERT INTO TESTS VALUES (12, 35, 3, 2);
INSERT INTO TESTS VALUES (13, 66, 3, 4);
INSERT INTO TESTS VALUES (14, 145, 4, 3);
INSERT INTO TESTS VALUES (15, 123, 5, 1);
INSERT INTO TESTS VALUES (16, 89, 2, 1);
INSERT INTO TESTS VALUES (17, 12, 3, 2);
INSERT INTO TESTS VALUES (18, 11, 4, 1);

INSERT INTO TESTS VALUES (19, 34, 2, 6);
INSERT INTO TESTS VALUES (21, 44, 3, 7);
INSERT INTO TESTS VALUES (22, 35, 3, 8);
INSERT INTO TESTS VALUES (23, 66, 3, 9);
INSERT INTO TESTS VALUES (24, 145, 4, 6);
INSERT INTO TESTS VALUES (25, 123, 5, 7);
INSERT INTO TESTS VALUES (26, 89, 2, 8);
INSERT INTO TESTS VALUES (27, 12, 3, 9);
INSERT INTO TESTS VALUES (28, 11, 4, 8);
SELECT * FROM TESTS;

/**
Question 1A
Write a query which displays all tests done at the lab in the year 2018. 
You should show the 
	test name, 
	result value, 
	receive date, 
	access no, 
	facility name, 
	doctor name, 
	patient name, 
	patient age and 
	sex of the patient (1=Male, 2=Female)
*/
SELECT
	DT.TESTNAME AS "Test Name",
	T.RESULTVALUE AS "Result Value",
	R.RECEIVEDDATE AS "Received Date",
	R.ACCESSNO AS "Access No",
	DF.FACNAME AS "Facility Name",
	DD.DOCNAME AS "Doctor Name",
	CONCAT(P.FNAME, ' ', P.LANAME) AS "Patient Name",
	CAST(ROUND(DATEDIFF(DAY, P.DOB, GETDATE()) / 365.2425, 2) AS DECIMAL(10, 2)) AS "Patient Age", -- 365.2425 AS THE MEAN NUM OF DAYS IN A CALENDAR YEAR FOR THE LAST 400 YEARS
	CASE
		WHEN P.SEX = 1 THEN 'Male'
		WHEN P.SEX = 2 THEN 'Female'
		ELSE '-'
	END AS "Sex"
FROM
	DICTESTS DT INNER JOIN TESTS T ON T.TESTID = DT.TESTID
INNER JOIN REQUESTS R ON R.REQUESTID = T.REQUESTID
INNER JOIN DICFACILITIES DF ON DF.FACID = R.FACILITYID
INNER JOIN DICDOCTORS DD ON DD.DOCID = R.DOCTORID
INNER JOIN PATIENTS P ON P.PATID = R.PATIENTID
WHERE
	YEAR(R.RECEIVEDDATE) = '2018';

/*
Question 2A
Write a query which displays the total amount of money made by the lab per test. 
Note that the lab has a promotion on the test ‘TRIC’ (50% off). 
You should also note that the lab bills all patients from the facility ‘CHA’ at 50% but only if they are female and below 18  
(If they do not meet the condition, they pay the full amount).
*/

SELECT
	SUM(TestAmountTble.TotalAmount) AS "Total Amount",
	TestAmountTble.TestCode AS "Test Code"
FROM
	(
	SELECT
		CASE
			WHEN DT.TESTCODE = 'TRIC' THEN ROUND(DT.TESTPRICE*0.50, 2)
			-- 50% translates to 0.5
			WHEN DF.FACCODE = 'CHA'
			AND P.SEX = '2'
			-- Female
			AND CAST(ROUND(DATEDIFF(DAY, P.DOB, GETDATE()) / 365.2425, 2) AS DECIMAL(10, 2)) < 18
			-- 365.2425 is the mean num of days in a calendar year for the last 400 years
				THEN DT.TESTPRICE / 2
			ELSE DT.TESTPRICE
		END AS TotalAmount,
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
		AND R.PATIENTID = P.PATID ) AS TestAmountTble
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
		DT.TESTCODE) MainTble -- main table with the all the testcodes and their test counts
WHERE (2) = ( -- Here 2 is take =n as the third highest testcount from formula (N-1) = being the nth highest test code
		SELECT COUNT(DISTINCT(MainTble2.TestCount2)) -- count all 
		FROM (SELECT
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
				DT.TESTCODE) MainTble2
		WHERE MainTble2.TestCount2 > MainTble.TestCount 
		-- on the condition that testcount in this table is greater than the testcount 
		-- passed from the first main table, if it is not then its not counted. 
		-- in this case if the count is 2 then  
)

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

