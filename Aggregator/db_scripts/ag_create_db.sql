CREATE DATABASE `aggregator` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `ag_identities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(45) NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId_UNIQUE` (`loginId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `aggregator`.`ag_identities`
(`id`,
`loginId`,
`fname`,
`lname`,
`email`,
`password`)
VALUES(1,'admin','System','Administrator','Sys@admin.com','AXmaCS2Dy2Ds4h+Y4WftHw==');
