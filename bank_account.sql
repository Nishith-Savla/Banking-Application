CREATE SCHEMA IF NOT EXISTS BankingApplication;

USE BankingApplication;

CREATE TABLE bank_account
(
    Name VARCHAR(50) NOT NULL,
    AccNo CHAR(10) NOT NULL PRIMARY KEY,
    Password VARCHAR(20) NOT NULL,
    Age TINYINT NULL,
    Gender CHAR(6) NULL,
    Email VARCHAR(30) NOT NULL UNIQUE,
    Balance INT DEFAULT 1 NOT NULL
);
