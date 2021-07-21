-- Question 4A
SELECT
	r.requestId AS "Request ID",
	format(r.receivedDate, 'dd-MMM-yyyy') AS "Received Date",
	r.accessNo AS "Access No",
	p.patId AS "Patient ID",
	count(*) AS "Tests",
	HighestResultTble.testName AS "HighTest"
FROM
	Requests r,
	Tests t,
	Patients p,
	-- temp table query to fetch tests with the highest results
	(SELECT
		dt.testId,
		dt.testName,
		TestResTble.HighestRes
	FROM
		DicTests dt,
		(SELECT
			t.testId AS TestID,
			max(t.resultValue) AS HighestRes
		FROM
			Tests t
		GROUP BY
			t.testId) AS TestResTble
		-- main test result table with the test and 
		-- their corresponding highest test result values
		WHERE dt.testId IN (TestResTble.TestID)) AS HighestResultTble
		-- this gets the details (testname, id and result) of each highest result value tests
WHERE 
	t.requestId = r.requestId
	AND p.patId = r.patId
	AND HighestResultTble.testId = t.testId
GROUP BY 
	r.requestId,
	r.receivedDate,
	r.accessNo,
	p.patId,
	HighestResultTble.testName;