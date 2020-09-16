DROP DATABASE IF EXISTS inventoryservice;
DROP USER IF EXISTS `inventory_service`@`%`;
CREATE DATABASE IF NOT EXISTS inventoryservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `inventory_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `inventoryservice`.* TO `inventory_service`@`%`;