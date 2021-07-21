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