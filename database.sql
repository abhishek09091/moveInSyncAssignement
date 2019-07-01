drop database if exists moveinSync;
create database moveinSync;
use moveinSync;
DROP TABLE IF exists `theater_movie`;
DROP TABLE IF EXISTS `theater_entries`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `movies`;


CREATE TABLE IF NOT EXISTS `address` (
  `address_id` INT(11) NOT NULL,
  `city` VARCHAR(255)  NOT NULL,
  `pin_code` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `landmark` VARCHAR(255) NOT NULL,
  `street_no` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `theater_entries` (
  `theater_id` INT(11) NOT NULL,
  `theater_name` VARCHAR(255)  NOT NULL,
  `rating` DOUBLE NOT NULL,
  `address_id` INT(11) NOT NULL,
  FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  PRIMARY KEY (`theater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `movies` (
  `movie_id` INT(11) NOT NULL,
  `movie_name` VARCHAR(255)  NOT NULL,
  `movie_type` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `theater_movie` (
  `theater_id` INT(11) NOT NULL,
  `movie_id` INT(11)  NOT NULL,
  FOREIGN KEY (`theater_id`) REFERENCES `theater_entries` (`theater_id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

show tables;
