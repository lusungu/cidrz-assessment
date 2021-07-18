# cidrz-assessment
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