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
	TestAmountTble.TestCode;