CREATE DATABASE Bank;

CREATE TABLE Accounts (
	AccountID SERIAL PRIMARY KEY,
	AccountName varchar(40),
	Balance DOUBLE PRECISION,
	DateOpened DATE,
	CustomerID int
);

CREATE TABLE Customers (
	CustomerID SERIAL PRIMARY KEY,
	FirstName varchar(40),
	LastName varchar(40),
	CustomerPhone varchar(10),
	CustomerEmail varchar(40),
	Login varchar(40) UNIQUE NOT NULL,
	PASSWORD varchar(40) NOT NULL,
	PowerLevel int CHECK (PowerLevel>=1 AND PowerLevel<=3)
);

ALTER TABLE Accounts 
ADD FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID);

CREATE TABLE Applications (
	ApplicationID SERIAL PRIMARY KEY,
	AccountName varchar(40),
	AccountStartingBalance DOUBLE PRECISION,
	CustomerID int
);

ALTER TABLE Applications
ADD FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID);

INSERT INTO Customers (customername, customerphone, customeremail, login, "password", PowerLevel)
VALUES ('Jason Chang', '7649081121', 'nosaj@email.com', 'login', 'password', 3);
INSERT INTO Accounts (AccountName, Balance, dateopened, customerid)
VALUES ('Personal', 1500.01, '2021-10-07', 1);
INSERT INTO Accounts (AccountName, Balance, dateopened, customerid)
VALUES ('Checking', 250.01, '2021-10-13', 1);

SELECT * FROM Accounts;

CREATE OR REPLACE PROCEDURE changebalance(bal DOUBLE PRECISION, id int)
	LANGUAGE plpgsql
AS $procedure$
	BEGIN
		UPDATE Accounts
		SET Balance = bal
		WHERE AccountID = id;
	END;
$procedure$


