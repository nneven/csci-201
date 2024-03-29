DROP DATABASE IF EXISTS 201Lab;
CREATE DATABASE 201Lab;
USE 201Lab;
CREATE TABLE Page (
	pageID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	pageName VARCHAR(50) NOT NULL
);
INSERT INTO Page (pageName) VALUES ('cat.jsp');
INSERT INTO Page (pageName) VALUES ('dog.jsp');
CREATE TABLE PageVisited (
	pageVisitiedID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	pageID INT(11) NOT NULL,
	IPAddress VARCHAR(50) NOT NULL,
	portNum INT(11) NOT NULL,
	count INT(11) NOT NULL,
	FOREIGN KEY (pageID) REFERENCES Page(pageID)
);
