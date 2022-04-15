-- DROP SCHEMA IF EXISTS `db_sgo` ;
-- CREATE SCHEMA IF NOT EXISTS `db_sgo` ;
-- USE `db_sgo` ;
DROP USER IF EXISTS 'user_sgo'@'%' ;
CREATE USER IF NOT EXISTS 'user_sgo'@'%' IDENTIFIED BY 'Sg0p@SsWd';
-- REVOKE ALL ON db_sgo.* FROM 'user_sgo'@'%' ;
GRANT ALL ON db_sgo.* TO 'user_sgo'@'%' ;
-- GRANT SELECT, INSERT, DELETE, UPDATE PRIVILEGES ON db_sgo.* TO 'user_sgo'@'%' ;

-- --------------------------------------------------------------------------------

-- DROP SCHEMA IF EXISTS `db_keycloak` ;
CREATE SCHEMA IF NOT EXISTS `db_keycloak` ;
USE `db_keycloak` ;
-- DROP USER IF EXISTS 'user_keycloak'@'%' ;
-- CREATE USER 'user_keycloak'@'%' IDENTIFIED BY 'Kcp@SsWd';
-- REVOKE ALL ON db_keycloak.* FROM 'user_keycloak'@'%' ;
-- GRANT ALL ON db_keycloak.* TO 'user_keycloak'@'%' ;
-- GRANT SELECT, INSERT, DELETE, UPDATE PRIVILEGES ON db_keycloak.* TO 'user_keycloak'@'%' ;
-- REVOKE ALL ON db_keycloak.* FROM 'user_sgo'@'%' ;
GRANT ALL ON db_keycloak.* TO 'user_sgo'@'%' ;
-- GRANT SELECT, INSERT, DELETE, UPDATE PRIVILEGES ON db_keycloak.* TO 'user_sgo'@'%' ;

