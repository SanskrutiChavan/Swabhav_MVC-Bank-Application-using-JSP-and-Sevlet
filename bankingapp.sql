-- Database for BankingAppMVC


CREATE DATABASE IF NOT EXISTS bankingapp;
USE bankingapp;

-- Admins Table
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL
);

-- Customers Table
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(200),
    balance DECIMAL(15,2) DEFAULT 0.00,
    active BOOLEAN DEFAULT TRUE
);

-- Transactions Table
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_account VARCHAR(20),
    to_account VARCHAR(20),
    type ENUM('CREDIT','TRANSFER') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE transactions
ADD COLUMN description VARCHAR(255) AFTER amount;


-- Insert Default Admin
INSERT INTO admins (username, password, full_name)
VALUES ('admin', 'admin123', 'System Administrator');

-- Insert Sample Customers
INSERT INTO customers (account_number, username, email, password, full_name, phone, address, balance, active)
VALUES 
('BANK20250001','john_doe','john@example.com','pass123','John Doe','9998887777','New York','5000.00',TRUE),
('BANK20250002','jane_smith','jane@example.com','pass123','Jane Smith','8887776666','Los Angeles','3000.00',TRUE);

-- Insert Sample Transactions
INSERT INTO transactions (from_account, to_account, type, amount)
VALUES
('BANK20250001', 'BANK20250001', 'CREDIT', 1000.00),
('BANK20250001', 'BANK20250002', 'TRANSFER', 500.00),
('BANK20250002', 'BANK20250002', 'CREDIT', 2000.00);

select * from transactions;

select * from customers;

select * from admins;


UPDATE admins SET password='240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9' WHERE username='admin';
UPDATE customers SET password='9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c' WHERE username IN ('john_doe','jane_smith');


SELECT account_number, username, balance FROM customers;

UPDATE transactions 
SET description = CONCAT('Self deposit of ', amount)
WHERE type = 'CREDIT' AND description IS NULL AND id > 0;

UPDATE transactions 
SET description = CONCAT('Transfer of ', amount, ' from ', from_account, ' to ', to_account)
WHERE type = 'TRANSFER' AND description IS NULL AND id > 0;

SET SQL_SAFE_UPDATES = 0;

UPDATE transactions 
SET description = CONCAT('Self deposit of ', amount)
WHERE type = 'CREDIT' AND description IS NULL;

UPDATE transactions 
SET description = CONCAT('Transfer of ', amount, ' from ', from_account, ' to ', to_account)
WHERE type = 'TRANSFER' AND description IS NULL;

SET SQL_SAFE_UPDATES = 1;  -- re-enable after

SELECT id, from_account, to_account, type, amount, description, timestamp 
FROM transactions;


ALTER TABLE transactions 
    MODIFY COLUMN type ENUM('CREDIT','TRANSFER_IN','TRANSFER_OUT') NOT NULL;

ALTER TABLE transactions 
    ADD COLUMN description VARCHAR(255) AFTER amount;

UPDATE transactions
SET from_account = to_account
WHERE from_account IS NULL OR from_account = '';


drop table transactions;




TRUNCATE TABLE transactions;






select * from transactions;

select * from customers;

DELETE FROM customers WHERE id = 9;


