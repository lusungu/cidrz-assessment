-- Question 3A
-- Method 2
SELECT * -- main outer query
FROM
	(SELECT
		COUNT(*) AS TestCount,
		dt.testCode AS "Test Code"
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
