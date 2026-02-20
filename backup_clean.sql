/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.15-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: 
-- ------------------------------------------------------
-- Server version	10.11.15-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `booking`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `booking` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `booking`;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `guest_name` varchar(255) DEFAULT NULL,
  `nightly_rate` double NOT NULL,
  `number_of_nights` int(11) NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_amount` double NOT NULL,
  `total_room_cost` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES
(1,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','cancelled',340,340),
(2,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','cancelled',340,340),
(3,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','cancelled',340,340),
(4,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','cancelled',340,340),
(5,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','pending',340,340),
(6,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','pending',340,340),
(7,'2026-02-10','2026-02-12','Gabriela',25,2,'cash','single','cancelled',50,50),
(8,'2026-02-23','2026-02-28','Valerio',35,5,'cash','suite','confirmed',175,175),
(9,'2026-02-23','2026-03-07','Prova',100,12,'bank_transfer','double','confirmed',1200,1200),
(10,'2026-02-09','2026-02-13','Prova 2',100,4,'cash','single','checked-in',400,400),
(11,'2026-02-09','2026-02-09','GianCavolo',1200,1,'credit_card','single','checked-out',1200,1200),
(12,'2026-02-09','2026-04-24','GianFilippo Neri',1500,74,'credit_card','suite','checked-out',111000,111000),
(13,'2026-02-09','2026-02-14','prova4',12,5,'credit_card','single','checked-in',60,60),
(14,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','cancelled',340,340),
(15,'2026-02-09','2026-02-09','prova 3',3,1,'credit_card','single','checked-out',3,3),
(16,'2026-02-09','2026-03-05','Mario Rossi',85,24,'credit_card','double','checked-in',2040,2040),
(17,'2026-02-09','2026-02-09','prova 5',90,1,'cash','single','checked-out',90,90),
(18,'2026-03-01','2026-03-05','Mario Rossi',85,4,'credit_card','double','pending',340,340);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `dinner`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dinner` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `dinner`;

--
-- Table structure for table `dinner`
--

DROP TABLE IF EXISTS `dinner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tip` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinner`
--

LOCK TABLES `dinner` WRITE;
/*!40000 ALTER TABLE `dinner` DISABLE KEYS */;
INSERT INTO `dinner` VALUES
(1,12,'Pizza Margherita','delivered',0),
(2,12,'Pizza Margherita','pending',0);
/*!40000 ALTER TABLE `dinner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `emergency`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `emergency` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `emergency`;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `queue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `javaeat`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `javaeat` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `javaeat`;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES
(1,'Milano','MI'),
(2,'Roma','RM'),
(3,'Napoli','NA'),
(4,'Torino','TO'),
(5,'Firenze','FI');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `city_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt79b5wvqbf38jtkjx36vp9vam` (`city_id`),
  CONSTRAINT `FKt79b5wvqbf38jtkjx36vp9vam` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES
(1,1,'Via Roma 1','mario.rossi@email.com','Mario Rossi','password123'),
(2,2,'Via Milano 2','anna.bianchi@email.com','Anna Bianchi','password123'),
(3,3,'Via Napoli 3','luigi.verdi@email.com','Luigi Verdi','password123'),
(4,4,'Via Torino 4','giulia.gialli@email.com','Giulia Gialli','password123'),
(5,5,'Via Firenze 5','paolo.blu@email.com','Paolo Blu','password123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `customer_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `restaurant_id` int(11) DEFAULT NULL,
  `rider_id` int(11) DEFAULT NULL,
  `delivery_time_open` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr0mg2e4p18frsju6qut84g8fs` (`customer_id`),
  KEY `FKeagt49doseanxh8r2hrb18goj` (`restaurant_id`),
  KEY `FK99dn9j7ajhgde30tt8xikla6f` (`rider_id`),
  CONSTRAINT `FK99dn9j7ajhgde30tt8xikla6f` FOREIGN KEY (`rider_id`) REFERENCES `rider` (`id`),
  CONSTRAINT `FKeagt49doseanxh8r2hrb18goj` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKr0mg2e4p18frsju6qut84g8fs` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES
(1,1,15.5,1,1,'2026-02-12 12:39:15.000000','Pizza margherita','OPEN'),
(2,2,18,2,2,'2026-02-11 14:39:15.000000','Pasta carbonara','DELIVERED'),
(3,3,35,3,3,'2026-02-12 13:39:15.000000','Sushi platter','OPEN'),
(4,4,12,4,4,'2026-02-10 14:39:15.000000','Hamburger XL','CANCELED'),
(5,5,45,5,5,'2026-02-12 14:09:15.000000','Bistecca fiorentina','OPEN');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `capacity` int(11) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl968d8d7966yymvsxtdsni1vw` (`city_id`),
  CONSTRAINT `FKl968d8d7966yymvsxtdsni1vw` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES
(50,1,1,'Via Pizza 1','pizzeria.uno@email.com','Pizzeria Uno','password123'),
(40,2,2,'Via Pasta 2','trattoria.due@email.com','Trattoria Due','password123'),
(60,3,3,'Via Gourmet 3','ristorante.tre@email.com','Ristorante Tre','password123'),
(35,4,4,'Via Tokyo 4','sushi.quattro@email.com','Sushi Quattro','password123'),
(45,5,5,'Via America 5','burger.cinque@email.com','Burger Cinque','password123');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rider`
--

DROP TABLE IF EXISTS `rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `rider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_cost` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rider`
--

LOCK TABLES `rider` WRITE;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
INSERT INTO `rider` VALUES
(1,5,'rider.uno@email.com','Marco Neri','password123'),
(2,6,'rider.due@email.com','Laura Arancioni','password123'),
(3,4,'rider.tre@email.com','Giuseppe Bianchi','password123'),
(4,7,'rider.quattro@email.com','Sofia Verdi','password123'),
(5,5,'rider.cinque@email.com','Luca Blu','password123');
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `javaeattest`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `javaeattest` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `javaeattest`;

--
-- Current Database: `lesson`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lesson` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `lesson`;

--
-- Current Database: `modulo 8`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `modulo 8` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `modulo 8`;

--
-- Table structure for table `AUTO`
--

DROP TABLE IF EXISTS `AUTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `AUTO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLATE` varchar(20) NOT NULL,
  `MODEL` varchar(100) NOT NULL,
  `COST` decimal(10,2) DEFAULT NULL,
  `OWNER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `OWNER_ID` (`OWNER_ID`),
  CONSTRAINT `AUTO_ibfk_1` FOREIGN KEY (`OWNER_ID`) REFERENCES `PERSON` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTO`
--

LOCK TABLES `AUTO` WRITE;
/*!40000 ALTER TABLE `AUTO` DISABLE KEYS */;
INSERT INTO `AUTO` VALUES
(1,'AA-111-AA','Fiat Panda',15000.00,1),
(2,'BB-222-BB','BMW Serie 3',45000.00,2),
(3,'CC-333-CC','Ford Fiesta',18000.00,3),
(4,'DD-444-DD','Audi A4',25000.00,5),
(5,'EE-555-EE','Toyota Yaris',22000.00,7);
/*!40000 ALTER TABLE `AUTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AUTO_CASCADE`
--

DROP TABLE IF EXISTS `AUTO_CASCADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `AUTO_CASCADE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLATE` varchar(20) NOT NULL,
  `MODEL` varchar(100) NOT NULL,
  `COST` decimal(10,2) DEFAULT NULL,
  `OWNER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `OWNER_ID` (`OWNER_ID`),
  CONSTRAINT `AUTO_CASCADE_ibfk_1` FOREIGN KEY (`OWNER_ID`) REFERENCES `PERSON_CASCADE` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTO_CASCADE`
--

LOCK TABLES `AUTO_CASCADE` WRITE;
/*!40000 ALTER TABLE `AUTO_CASCADE` DISABLE KEYS */;
INSERT INTO `AUTO_CASCADE` VALUES
(3,'CC-333-CC','Ford Fiesta',18000.00,2),
(4,'DD-444-DD','Toyota Yaris',22000.00,3);
/*!40000 ALTER TABLE `AUTO_CASCADE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AUTO_RESTRICT`
--

DROP TABLE IF EXISTS `AUTO_RESTRICT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `AUTO_RESTRICT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLATE` varchar(20) NOT NULL,
  `MODEL` varchar(100) NOT NULL,
  `COST` decimal(10,2) DEFAULT NULL,
  `OWNER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `OWNER_ID` (`OWNER_ID`),
  CONSTRAINT `AUTO_RESTRICT_ibfk_1` FOREIGN KEY (`OWNER_ID`) REFERENCES `PERSON_RESTRICT` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTO_RESTRICT`
--

LOCK TABLES `AUTO_RESTRICT` WRITE;
/*!40000 ALTER TABLE `AUTO_RESTRICT` DISABLE KEYS */;
/*!40000 ALTER TABLE `AUTO_RESTRICT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CITY`
--

DROP TABLE IF EXISTS `CITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `CITY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CITY`
--

LOCK TABLES `CITY` WRITE;
/*!40000 ALTER TABLE `CITY` DISABLE KEYS */;
INSERT INTO `CITY` VALUES
(1,'Lisbona'),
(2,'Porto'),
(3,'Faro'),
(4,'Coimbra');
/*!40000 ALTER TABLE `CITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `PERSON` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `SURNAME` varchar(100) NOT NULL,
  `CITY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CITY_ID` (`CITY_ID`),
  CONSTRAINT `PERSON_ibfk_1` FOREIGN KEY (`CITY_ID`) REFERENCES `CITY` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
INSERT INTO `PERSON` VALUES
(1,'Mario','Rossi',1),
(2,'Luigi','Bianchi',1),
(3,'Anna','Verdi',1),
(4,'Paolo','Neri',1),
(5,'Maria','Gialli',2),
(6,'Giovanni','Blu',2),
(7,'Sofia','Rossi',3),
(8,'Marco','Bianchi',NULL);
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_CASCADE`
--

DROP TABLE IF EXISTS `PERSON_CASCADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `PERSON_CASCADE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `SURNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_CASCADE`
--

LOCK TABLES `PERSON_CASCADE` WRITE;
/*!40000 ALTER TABLE `PERSON_CASCADE` DISABLE KEYS */;
INSERT INTO `PERSON_CASCADE` VALUES
(2,'Luigi','Bianchi'),
(3,'Anna','Verdi');
/*!40000 ALTER TABLE `PERSON_CASCADE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_RESTRICT`
--

DROP TABLE IF EXISTS `PERSON_RESTRICT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `PERSON_RESTRICT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `SURNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_RESTRICT`
--

LOCK TABLES `PERSON_RESTRICT` WRITE;
/*!40000 ALTER TABLE `PERSON_RESTRICT` DISABLE KEYS */;
INSERT INTO `PERSON_RESTRICT` VALUES
(1,'Paolo','Neri'),
(2,'Sara','Marrone');
/*!40000 ALTER TABLE `PERSON_RESTRICT` ENABLE KEYS */;
UNLOCK TABLES;
