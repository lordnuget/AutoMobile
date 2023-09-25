--
-- Data Control Language (DCL) for database AutoMobile
--

USE `automobile`

-- Create database admin user with full privileges
CREATE USER 'db_admin'@'%' IDENTIFIED BY 'changeme123';
GRANT Alter ON automobile.* TO 'db_admin'@'%';
GRANT Create ON automobile.* TO 'db_admin'@'%';
GRANT Create view ON automobile.* TO 'db_admin'@'%';
GRANT Delete ON automobile.* TO 'db_admin'@'%';
GRANT Drop ON automobile.* TO 'db_admin'@'%';
GRANT Grant option ON automobile.* TO 'db_admin'@'%';
GRANT Index ON automobile.* TO 'db_admin'@'%';
GRANT Insert ON automobile.* TO 'db_admin'@'%';
GRANT References ON automobile.* TO 'db_admin'@'%';
GRANT Select ON automobile.* TO 'db_admin'@'%';
GRANT Show view ON automobile.* TO 'db_admin'@'%';
GRANT Trigger ON automobile.* TO 'db_admin'@'%';
GRANT Update ON automobile.* TO 'db_admin'@'%';
GRANT Alter routine ON automobile.* TO 'db_admin'@'%';
GRANT Create routine ON automobile.* TO 'db_admin'@'%';
GRANT Create temporary tables ON automobile.* TO 'db_admin'@'%';
GRANT Execute ON automobile.* TO 'db_admin'@'%';
GRANT Lock tables ON automobile.* TO 'db_admin'@'%';
GRANT Grant option ON automobile.* TO 'db_admin'@'%';

-- Create database application user with limited privileges
CREATE USER 'db_app'@'%' IDENTIFIED BY 'changeme123';
GRANT Delete ON *.* TO 'db_app'@'%';
GRANT Insert ON *.* TO 'db_app'@'%';
GRANT Select ON *.* TO 'db_app'@'%';
GRANT Show view ON *.* TO 'db_app'@'%';
GRANT Trigger ON *.* TO 'db_app'@'%';
GRANT Update ON *.* TO 'db_app'@'%';
GRANT Execute ON *.* TO 'db_app'@'%';
GRANT Lock tables ON *.* TO 'db_app'@'%';