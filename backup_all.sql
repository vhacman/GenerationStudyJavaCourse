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

--
-- Current Database: `mysql`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mysql` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `processor` varchar(255) DEFAULT NULL,
  `ram` int(11) NOT NULL,
  `ssd` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `product`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `product` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `product`;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES
(1,'iPhone 15 Pro Max',1199),
(2,'Samsung Galaxy S24 Ultra',1299),
(3,'MacBook Pro 16 M3',2499),
(4,'iPad Pro 12.9',1099),
(5,'AirPods Pro 2',279),
(6,'Apple Watch Ultra 2',799),
(7,'Samsung Galaxy Watch 6',399),
(8,'Sony WH-1000XM5',399),
(9,'Nintendo Switch OLED',349),
(10,'PlayStation 5',499),
(11,'Xbox Series X',499),
(12,'Dell XPS 15',1899),
(13,'HP Spectre x360',1499),
(14,'Lenovo ThinkPad X1 Carbon',1599),
(15,'Surface Pro 9',1299),
(16,'Canon EOS R6',2499),
(17,'Sony A7 IV',2499),
(18,'DJI Mini 4 Pro',759),
(19,'Bose QuietComfort Ultra',429),
(20,'Sonos Era 300',449),
(21,'Kindle Paperwhite',149),
(22,'Nintendo Switch Lite',199),
(23,'Steam Deck',399),
(24,'LG C3 65 Pollici',1799),
(25,'Samsung The Frame 55',1299),
(26,'Logitech MX Master 3S',99),
(27,'Keychron Q1 Pro',199),
(28,'Samsung 990 Pro 2TB',179),
(29,'WD Black SN850X 1TB',119),
(30,'Anker PowerBank 20000mAh',49),
(31,'Belkin Surge Protector',39),
(32,'SanDisk Extreme Pro 512GB',79),
(33,'Sony WF-1000XM5',299),
(34,'Sennheiser Momentum 4',349),
(35,'Jabra Elite 10',249),
(36,'Google Pixel 8 Pro',1099),
(37,'OnePlus 12',799),
(38,'Xiaomi 14 Ultra',1299),
(39,'Vivo X100 Pro',999),
(40,'Nothing Phone 2a',349),
(41,'Fairphone 5',699),
(42,'Razer Blade 18',2799),
(43,'Alienware m18',3199),
(44,'ASUS ROG Zephyrus G14',1599),
(45,'MSI Raider GE78',2499),
(46,'Acer Predator Helios',1799),
(47,'Tuf Gaming A15',1299),
(48,'Framework Laptop 16',1399),
(49,'Apple Vision Pro',3499),
(50,'Meta Quest 3',499),
(51,'Armadio',1200);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1051 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES
(1,'Marco Rossi',5,'Il migliore iPhone di sempre, fotocamera eccezionale',1),
(2,'Laura Bianchi',4,'Ottimo telefono, batteria migliorabile',1),
(3,'Giuseppe Verdi',5,'Spen 5g incredibile, multitasking fluido',2),
(4,'Anna Neri',4,'Buon prodotto, un po pesante',2),
(5,'Francesco Russo',5,'Potenza pura, perfetto per il video editing',3),
(6,'Giulia Ferrari',4,'Mac perfetto, prezzo alto',3),
(7,'Alessandro Costa',5,'Tablet fantastico per lavoro e svago',4),
(8,'Sofia Romano',4,'Buon tablet, aggiornamenti lenti',4),
(9,'Lorenzo Esposito',5,'Cancellazione rumore top, audio cristallino',5),
(10,'Martina Greco',5,'I migliori auricolari che abbia mai avuto',5),
(11,'Andrea Marino',4,'Ottimo smartwatch, durata batteria ok',6),
(12,'Chiara Ferrari',5,'Perfetto per lo sport, GPS preciso',6),
(13,'Matteo Ricci',4,'Buon orologio, integrazione Samsung ottima',7),
(14,'Emma Colombo',5,'Elegante e funzionale, lo ricomprerei',7),
(15,'Gabriele Bruno',5,'Audio professionale, noise cancelling perfetto',8),
(16,'Alessia Marchetti',4,'Suono ottimo, un po scomodi per ore',8),
(17,'Samuele Fontoura',5,'Perfetto per viaggiare, schermo bellissimo',9),
(18,'Federica Moretti',4,'Divertente, library games eccellente',9),
(19,'Tommaso Caruso',5,'Esclusive imperdibili, grafica stupefacente',10),
(20,'Alice Conti',5,'La console gaming definitiva',10),
(21,'Piergiovanni Marchese',4,'Gamepass incredibile, hardware potente',11),
(22,'Valentina De Luca',5,'Tutti i giochi che vuoi, zero rimpianti',11),
(23,'Gioele Ferrara',5,'Workstation portatile perfetta',12),
(24,'Michela Mancini',4,'Schermo 4k splendido, un po caldo',12),
(25,'Edoardo Santoro',5,'2-in-1 elegante, touchscreen preciso',13),
(26,'Rebecca DAmico',4,'Buon laptop, pennino non incluso',13),
(27,'Nicolas Gentile',5,'Business laptop definitivo, tastiera top',14),
(28,'Giovanna Ferrero',4,'Affidabile e leggero, costo elevato',14),
(29,'Luca Bianchi',4,'Surface migliore di sempre, app x86 ok',15),
(30,'Beatrice Fontana',5,'Perfetto per artisti digitali',15),
(31,'Mattia Testa',5,'Fotocamera professionale, low light eccellente',16),
(32,'Zoe Costa',4,'Ottima per foto, video 8k fluido',16),
(33,'Ethan Marchetti',5,'Mirrorless eccezionale, autofocus perfetto',17),
(34,'Miriam Ferrante',4,'Soddisfatto, batteria migliorabile',17),
(35,'Christian Romano',5,'Drone portatile con prestazioni incredibili',18),
(36,'Laura Bruno',4,'Facile da usare, qualità video top',18),
(37,'Alessio Marchese',5,'Noise cancelling il migliore sul mercato',19),
(38,'Sofia Conti',5,'Audio spatial incredibile, imperdibile',19),
(39,'Giacomo De Santis',4,'Suono surround emozionante, setup facile',20),
(40,'Emma Riva',5,'Design unico, qualità audio premium',20),
(41,'Filippo Gallo',5,'Lettore e-ink perfetto per viaggi',21),
(42,'Giulia Testa',4,'Luce calda regolabile, waterproof ok',21),
(43,'Lorenzo Ferrara',4,'Portatile e colorato, giochi esclusivi',22),
(44,'Martina Marini',5,'Ideale per giocare in mobilita',22),
(45,'Andrea DAmico',5,'PC gaming portatile rivoluzionario',23),
(46,'Chiara Santoro',4,'Potente, un po pesante da trasportare',23),
(47,'Gabriele Caruso',5,'OLED perfetto per film e gaming',24),
(48,'Alessia Ferrero',5,'Neri profondi, colori vivaci, wow',24),
(49,'Samuele Colombo',4,'TV design artistico, qualità ok',25),
(50,'Rebecca Greco',5,'Sembra un quadro, effetto ambient top',25),
(51,'Mattia Bruno',5,'Mouse ergonomico perfetto, scroller silenzioso',26),
(52,'Zoe Mancini',4,'Precisione eccellente, un po costoso',26),
(53,'Nicolas Romano',5,'Mechanical keyboard personalizzabile top',27),
(54,'Edoardo Testa',4,'Qualità costruttiva premium, switch meccanici',27),
(55,'Miriam Costa',5,'NVMe velocissimo, gaming fluido',28),
(56,'Christian De Luca',4,'Installazione facile, prestazioni top',28),
(57,'Ethan Ferrara',5,'SSM velocissimo, boot istantaneo',29),
(58,'Laura Marchetti',4,'Affidabile, rapporto qualita prezzo ok',29),
(59,'Alessio Bianchini',5,'Capiente e compatto, ricarica rapida',30),
(60,'Sofia Conti',4,'Perfetto per viaggi, LED utile',30),
(61,'Giacomo Moretti',4,'Molte prese, protezione affidabile',31),
(62,'Emma Neri',5,'Compatto e funzionale, must-have',31),
(63,'Filippo Ferrari',5,'Veloce e resistente, per professionisti',32),
(64,'Giulia Ricci',4,'Qualita eccellente, prezzo alto ma ne vale la pena',32),
(65,'Lorenzo Romano',5,'Auricolari True Wireless top di gamma',33),
(66,'Martina Esposito',4,'Audio bilanciato, noise cancelling ok',33),
(67,'Andrea Caruso',5,'Suono dettagliato, batteria infinita',34),
(68,'Chiara Bruno',4,'Comodi e eleganti, durata batteria top',34),
(69,'Gabriele Colombo',5,'Noise cancelling adaptive, comfort eccezionale',35),
(70,'Alessia Marchese',4,'Audio premium, multipoint eccellente',35),
(71,'Samuele Santoro',5,'Android stock migliore, fotocamera incredibile',36),
(72,'Rebecca Ferrara',5,'AI impressionante, seven years of updates',36),
(73,'Mattia De Santis',5,'Flagship killer, ricarica 100W pazzesca',37),
(74,'Zoe Mancini',4,'Ottimo rapporto qualita prezzo, foto top',37),
(75,'Nicolas Ferrero',5,'Fotocamera Leica incredibile, zoom eccezionale',38),
(76,'Edoardo Testa',4,'Design elegante, prestazioni al top',38),
(77,'Miriam Costa',5,'Periscopio zoom 100x pazzesco, super',39),
(78,'Christian Bianchi',4,'Ottimo smartphone, aggiornamenti regolari',39),
(79,'LLaura Esposito',5,'Nothing OS fluido, Glyph interface originale',40),
(80,'Giulia Neri',4,'Design trasparente, software minimale',40),
(81,'Marco Caruso',5,'Modulare e sostenibile, futuro della tecnologia',41),
(82,'Anna Romano',4,'Facile da riparare, etica produttiva top',41),
(83,'Francesco Colombo',5,'Gaming desktop replacement definitivo',42),
(84,'Sofia Marchese',4,'Overclock estremo, sistema termico ok',42),
(85,'Giuseppe Ferrara',5,'Alienware legend, potenza illimitata',43),
(86,'Alessia Santoro',4,'RGB RGB RGB, gaming estremo',43),
(87,'Lorenzo De Luca',5,'Portatile gaming sottile e potente',44),
(88,'Giulia Testa',4,'AMD Ryzen 9 demoniaco, grafica top',44),
(89,'Andrea Costa',5,'Desktop replacement definitivo',45),
(90,'Chiara Bruno',4,'Performance estreme, peso elevato',45),
(91,'Gabriele Neri',5,'Gaming laptop accessibile e performante',46),
(92,'Marco Marchetti',5,'Modulare e riparabile, filosofia open',47),
(93,'Alessio Ferrante',5,'VR spaziale incredibile, passthrough HD',48),
(94,'Sofia Testa',4,'Realta mista fluida, gaming VR impressionante',48),
(95,'Lorenzo Romano',5,'Futuro computing, esperienza spaziale totale',49),
(96,'Giulia Ferrari',4,'Rivoluzionario, ma ancora in evoluzione',49),
(97,'Andrea Bianchini',5,'Gaming console definitiva dellera',50),
(98,'Alessia Mancini',5,'Imperdibile per app native Apple',50),
(99,'Gioele De Santis',5,'Deluso, mi aspettavo di piu',48),
(100,'Piergiovanni Ferrante',3,'Prodotto nella media, nulla di che',30),
(101,'Samuele Gallo',1,'Qualita premium',49),
(102,'Matteo Marino',2,'Deluso, mi aspettavo di piu',23),
(103,'Giulia Marchetti',5,'Non puoi sbagliare',27),
(104,'Martina Colombo',2,'Entry level consigliato',46),
(105,'LLaura Moretti',3,'Consigliato, funziona bene',5),
(106,'Alice Bruno',2,'Consigliato, funziona bene',5),
(107,'Valentina Romano',5,'Qualita premium',10),
(108,'Laura Ricci',2,'Upgrade consigliato',36),
(109,'Sofia Ferrari',3,'Potente e veloce',23),
(110,'Gabriele Verdi',5,'Indispensabile per il lavoro',11),
(111,'Giacomo Marchese',4,'Eccellente, qualita top',9),
(112,'Andrea Ricci',1,'Prezzo alto ma ne vale la pena',27),
(113,'Ethan Caruso',2,'Upgrade consigliato',37),
(114,'Giulia Russo',3,'Prodotto nella media, nulla di che',17),
(115,'Anna Bianchi',1,'Non male, pero migliorabile',25),
(116,'Alessia Bianchi',1,'Perfetto per le mie esigenze',49),
(117,'Filippo Greco',5,'Eccellente, qualita top',38),
(118,'Alessio Moretti',1,'Funzionale e pratico',49),
(119,'Giuseppe Marino',5,'Super, superato le aspettative',45),
(120,'Alessia Ricci',3,'Semplicemente fantastico',8),
(121,'Martina Verdi',1,'Prezzo alto ma ne vale la pena',23),
(122,'Ethan Neri',4,'Da evitare, non acquistatelo',28),
(123,'Matteo Neri',3,'Buon rapporto qualita prezzo',29),
(124,'Chiara Marchetti',3,'Indispensabile per il lavoro',19),
(125,'Marco Neri',5,'Peccato per alcuni dettagli',39),
(126,'Gabriele Verdi',2,'Prodotto base ma efficace',41),
(127,'Andrea Santucci',1,'Semplicemente fantastico',20),
(128,'Luca Neri',4,'Perfetto per iniziare',24),
(129,'Laura Testa',1,'Consigliato, funziona bene',47),
(130,'Piergiovanni Ferrante',5,'Soddisfatto dellacquisto',27),
(131,'Valentina Moretti',2,'Buon rapporto qualita prezzo',30),
(132,'Edoardo Neri',5,'Non puoi sbagliare',27),
(133,'Giuseppe Santoro',3,'Funzionale e pratico',47),
(134,'Beatrice Costa',5,'Prodotto nella media, nulla di che',27),
(135,'Christian Bianchi',5,'Qualita premium',5),
(136,'Lorenzo Riva',3,'Entry level consigliato',8),
(137,'Laura Neri',5,'Lo uso ogni giorno',23),
(138,'Martina Santucci',3,'Funzionale e pratico',46),
(139,'Gioele Moretti',5,'Consigliato, funziona bene',19),
(140,'Samuele De Luca',5,'Design elegante e moderno',2),
(141,'Federica Caruso',1,'Non puoi sbagliare',4),
(142,'Giovanna Moretti',3,'Funzionale e pratico',18),
(143,'Federica Bruno',1,'Peccato per alcuni dettagli',29),
(144,'Gabriele Ferrara',4,'Entry level consigliato',38),
(145,'Giulia Esposito',4,'Ottimo prodotto, lo ricompro',34),
(146,'Emma Russo',1,'Potente e veloce',37),
(147,'Laura Moretti',2,'Buon rapporto qualita prezzo',31),
(148,'Marco Rossi',2,'Soddisfatto dellacquisto',22),
(149,'Alessandro Neri',1,'Da evitare, non acquistatelo',12),
(150,'Alessio Ferrero',2,'Perfetto per iniziare',15),
(151,'LLaura Ferrari',3,'Potente e veloce',33),
(152,'Laura Bianchi',1,'Il migliore della categoria',45),
(153,'Alessandro Ferrari',3,'Semplicemente fantastico',3),
(154,'Marco Fontana',3,'Buon rapporto qualita prezzo',33),
(155,'Alessandro Marino',2,'Semplicemente fantastico',6),
(156,'Luca Russo',4,'Soddisfatto dellacquisto',46),
(157,'Sofia Santucci',5,'Qualita premium',15),
(158,'Giulia Ferrante',1,'Design elegante e moderno',26),
(159,'Michela Rossi',2,'Ottimo prodotto, lo ricompro',23),
(160,'Luca Conti',4,'Da evitare, non acquistatelo',42),
(161,'Edoardo Bruno',1,'Perfetto per iniziare',20),
(162,'Lorenzo Greco',2,'Lo uso ogni giorno',22),
(163,'Giulia Ferrante',2,'Non puoi sbagliare',44),
(164,'Mattia Riva',2,'Soddisfatto dellacquisto',39),
(165,'Piergiovanni Neri',3,'Indispensabile per il lavoro',24),
(166,'Lorenzo Ferrero',1,'Consigliato, funziona bene',19),
(167,'Emma Marino',1,'Prezzo alto ma ne vale la pena',9),
(168,'Gabriele Ferrari',5,'Consigliato, funziona bene',40),
(169,'Andrea Esposito',5,'Consigliato, funziona bene',50),
(170,'Federica Costa',1,'Non male, pero migliorabile',7),
(171,'Emma Ricci',3,'Da evitare, non acquistatelo',43),
(172,'Giuseppe Bruno',5,'Da evitare, non acquistatelo',10),
(173,'Tommaso Ferrara',1,'Potente e veloce',17),
(174,'Beatrice Ferrero',5,'Deluso, mi aspettavo di piu',26),
(175,'LLaura Neri',1,'Funzionale e pratico',19),
(176,'LLaura Russo',4,'Eccellente, qualita top',49),
(177,'Christian Santoro',1,'Acquisto azzeccato',3),
(178,'Edoardo Santucci',4,'Upgrade consigliato',34),
(179,'Martina Caruso',5,'Perfetto per le mie esigenze',29),
(180,'Martina Ferrari',1,'Design elegante e moderno',19),
(181,'Alessia Testa',4,'Buon rapporto qualita prezzo',21),
(182,'Valentina Marchese',1,'Acquisto azzeccato',28),
(183,'Christian Fontoura',1,'Upgrade consigliato',42),
(184,'Alessia Gentile',1,'Deluso, mi aspettavo di piu',34),
(185,'Giovanna Riva',1,'Prezzo alto ma ne vale la pena',26),
(186,'Nicolas Greco',2,'Funzionale e pratico',14),
(187,'Emma Fontana',3,'Prodotto nella media, nulla di che',19),
(188,'Giulia Ricci',1,'Design elegante e moderno',25),
(189,'Miriam Bianchi',2,'Perfetto per le mie esigenze',46),
(190,'Giacomo Ferrante',3,'Eccellente, qualita top',30),
(191,'Christian Bruno',5,'Semplicemente fantastico',24),
(192,'Edoardo Costa',1,'Funzionale e pratico',33),
(193,'Ethan Greco',5,'Non puoi sbagliare',1),
(194,'Emma Gallo',3,'Peccato per alcuni dettagli',35),
(195,'Sofia Verdi',1,'Prodotto base ma efficace',13),
(196,'Tommaso Gallo',4,'Non male, pero migliorabile',5),
(197,'Alessandro DAmico',5,'Il migliore della categoria',9),
(198,'Beatrice Moretti',5,'Perfetto per iniziare',44),
(199,'Christian Ferrara',4,'Qualita premium',24),
(200,'Beatrice Colombo',4,'Deluso, mi aspettavo di piu',20),
(201,'Gabriele Moretti',3,'Il migliore della categoria',28),
(202,'Marco De Santis',4,'Consigliato, funziona bene',13),
(203,'Beatrice Verdi',3,'Lo uso ogni giorno',15),
(204,'Giacomo Ferrara',2,'Potente e veloce',5),
(205,'Giulia Ferrante',5,'Acquisto azzeccato',46),
(206,'Andrea Ferrara',1,'Eccellente, qualita top',40),
(207,'Michela Gallo',4,'Buon rapporto qualita prezzo',38),
(208,'Anna Esposito',3,'Soddisfatto dellacquisto',47),
(209,'Christian Caruso',5,'Perfetto per le mie esigenze',4),
(210,'LLaura Bianchi',4,'Ottimo prodotto, lo ricompro',32),
(211,'Valentina Testa',3,'Perfetto per le mie esigenze',19),
(212,'Filippo Riva',5,'Entry level consigliato',7),
(213,'Sofia Moretti',2,'Entry level consigliato',33),
(214,'Piergiovanni Romano',4,'Potente e veloce',19),
(215,'Michela Conti',3,'Peccato per alcuni dettagli',47),
(216,'Chiara Bianchi',2,'Buon rapporto qualita prezzo',37),
(217,'Marco Marchese',1,'Robusto e resistente',13),
(218,'Beatrice Bruno',4,'Upgrade consigliato',7),
(219,'Tommaso Santoro',2,'Lo uso ogni giorno',35),
(220,'Edoardo Ferrero',4,'Prodotto base ma efficace',42),
(221,'Emma Ferrara',5,'Pro gamma',30),
(222,'Laura Marchetti',3,'Peccato per alcuni dettagli',35),
(223,'Alessia Caruso',1,'Entry level consigliato',45),
(224,'Zoe Bianchi',1,'Perfetto per le mie esigenze',29),
(225,'Luca Mancini',5,'Potente e veloce',18),
(226,'Giulia Marchese',2,'Prodotto nella media, nulla di che',35),
(227,'Giulia Bianchi',1,'Robusto e resistente',9),
(228,'Luca Gallo',3,'Soddisfatto dellacquisto',25),
(229,'Alessandro Moretti',4,'Non male, pero migliorabile',27),
(230,'Gabriele Bianchi',4,'Semplicemente fantastico',2),
(231,'Lorenzo Marino',4,'Super, superato le aspettative',42),
(232,'Giulia Marchetti',3,'Perfetto per iniziare',28),
(233,'Michela Marchese',4,'Il migliore della categoria',17),
(234,'Samuele Verdi',2,'Acquisto azzeccato',46),
(235,'Emma Ferrara',1,'Upgrade consigliato',49),
(236,'Tommaso Marino',1,'Soddisfatto dellacquisto',22),
(237,'Ethan Greco',5,'Robusto e resistente',45),
(238,'Alessia Ferrante',5,'Potente e veloce',5),
(239,'Alessandro Marino',2,'Perfetto per iniziare',40),
(240,'Samuele Testa',4,'Ottimo prodotto, lo ricompro',19),
(241,'Alice Conti',3,'Qualita premium',8),
(242,'Tommaso Moretti',4,'Semplicemente fantastico',34),
(243,'Emma Moretti',1,'Entry level consigliato',42),
(244,'Sofia Gentile',1,'Semplicemente fantastico',30),
(245,'Zoe Greco',2,'Soddisfatto dellacquisto',21),
(246,'Matteo Costa',5,'Prodotto base ma efficace',14),
(247,'Edoardo Greco',2,'Consigliato, funziona bene',26),
(248,'Christian Neri',3,'Non puoi sbagliare',12),
(249,'Alessio Riva',2,'Soddisfatto dellacquisto',34),
(250,'Laura De Luca',5,'Upgrade consigliato',46),
(251,'Alessio Rossi',2,'Peccato per alcuni dettagli',27),
(252,'Beatrice Marchetti',1,'Super, superato le aspettative',32),
(253,'Giacomo Gallo',4,'Funzionale e pratico',4),
(254,'Andrea Santucci',3,'Qualita premium',47),
(255,'Emma Ricci',3,'Lo uso ogni giorno',34),
(256,'Andrea Santucci',3,'Pro gamma',27),
(257,'Emma Marchese',2,'Funzionale e pratico',41),
(258,'Martina Conti',5,'Leggero e maneggevole',24),
(259,'Emma Mancini',4,'Soddisfatto dellacquisto',21),
(260,'Tommaso Neri',2,'Deluso, mi aspettavo di piu',29),
(261,'Sofia Esposito',1,'Consigliato, funziona bene',26),
(262,'Tommaso Esposito',5,'Indispensabile per il lavoro',28),
(263,'Giovanna Caruso',5,'Deluso, mi aspettavo di piu',15),
(264,'Alessia Costa',4,'Design elegante e moderno',19),
(265,'Samuele Marchetti',3,'Robusto e resistente',8),
(266,'Alessandro Russo',1,'Upgrade consigliato',26),
(267,'Martina Mancini',3,'Peccato per alcuni dettagli',12),
(268,'Filippo Fontoura',5,'Potente e veloce',26),
(269,'Luca Marchetti',4,'Da evitare, non acquistatelo',4),
(270,'Federica Romano',1,'Perfetto per le mie esigenze',26),
(271,'Giulia Esposito',3,'Design elegante e moderno',46),
(272,'Sofia Ferrari',5,'Entry level consigliato',39),
(273,'Gabriele Fontana',5,'Eccellente, qualita top',42),
(274,'Giuseppe Bianchi',5,'Consigliato, funziona bene',32),
(275,'Giulia Costa',1,'Prezzo alto ma ne vale la pena',29),
(276,'Miriam Fontana',5,'Design elegante e moderno',31),
(277,'Andrea Moretti',4,'Potente e veloce',28),
(278,'Sofia Neri',5,'Il migliore della categoria',14),
(279,'Giacomo Verdi',3,'Prodotto base ma efficace',7),
(280,'Alessio Fontana',1,'Non male, pero migliorabile',16),
(281,'Andrea Marino',3,'Potente e veloce',2),
(282,'Lorenzo Ferrari',5,'Robusto e resistente',17),
(283,'Laura Neri',3,'Acquisto azzeccato',24),
(284,'Alice Marchese',5,'Leggero e maneggevole',9),
(285,'Lorenzo Russo',3,'Semplicemente fantastico',25),
(286,'Francesco Ferrari',4,'Non male, pero migliorabile',1),
(287,'Piergiovanni Neri',1,'Upgrade consigliato',18),
(288,'Andrea Fontana',1,'Indispensabile per il lavoro',47),
(289,'Miriam Marchetti',2,'Prodotto nella media, nulla di che',23),
(290,'Giovanna Greco',5,'Acquisto azzeccato',48),
(291,'Alessia Gentile',3,'Funzionale e pratico',27),
(292,'Federica Gallo',5,'Pro gamma',36),
(293,'Filippo Romano',1,'Upgrade consigliato',38),
(294,'Miriam Ferrari',2,'Perfetto per iniziare',3),
(295,'Lorenzo Bruno',5,'Buon rapporto qualita prezzo',31),
(296,'Miriam Ferrante',1,'Perfetto per iniziare',18),
(297,'Edoardo Costa',3,'Prezzo alto ma ne vale la pena',28),
(298,'Gabriele Neri',3,'Funzionale e pratico',29),
(299,'Gioele Moretti',1,'Upgrade consigliato',33),
(300,'Emma Romano',2,'Robusto e resistente',39),
(301,'Luca Ferrero',3,'Non male, pero migliorabile',3),
(302,'LLaura Gallo',3,'Eccellente, qualita top',40),
(303,'Christian Testa',1,'Leggero e maneggevole',9),
(304,'Gabriele DAmico',4,'Soddisfatto dellacquisto',9),
(305,'Edoardo Mancini',2,'Non male, pero migliorabile',36),
(306,'Alessio Caruso',3,'Funzionale e pratico',17),
(307,'Laura Conti',4,'Peccato per alcuni dettagli',44),
(308,'Giulia Bianchi',1,'Peccato per alcuni dettagli',49),
(309,'Christian Marino',5,'Prezzo alto ma ne vale la pena',26),
(310,'Chiara Conti',3,'Prezzo alto ma ne vale la pena',45),
(311,'Alessio Colombo',5,'Potente e veloce',12),
(312,'Piergiovanni Colombo',1,'Prodotto base ma efficace',20),
(313,'Anna Rossi',3,'Lo uso ogni giorno',16),
(314,'Luca Ferrero',3,'Qualita premium',24),
(315,'Christian Caruso',2,'Semplicemente fantastico',18),
(316,'Emma Santucci',3,'Il migliore della categoria',7),
(317,'Alessio Moretti',5,'Il migliore della categoria',36),
(318,'Michela Costa',4,'Leggero e maneggevole',3),
(319,'Giulia Bruno',3,'Deluso, mi aspettavo di piu',6),
(320,'Giuseppe DAmico',3,'Perfetto per le mie esigenze',36),
(321,'Giulia Verdi',3,'Ottimo prodotto, lo ricompro',9),
(322,'Martina Bianchi',3,'Super, superato le aspettative',10),
(323,'Lorenzo Ferrante',2,'Il migliore della categoria',41),
(324,'LLaura DAmico',1,'Consigliato, funziona bene',30),
(325,'Giulia DAmico',5,'Potente e veloce',46),
(326,'Alessio Marino',2,'Ottimo prodotto, lo ricompro',14),
(327,'Marco Ferrante',2,'Entry level consigliato',27),
(328,'Alice Conti',5,'Lo uso ogni giorno',3),
(329,'Francesco DAmico',2,'Consigliato, funziona bene',23),
(330,'Giulia Ferrara',3,'Leggero e maneggevole',44),
(331,'Marco Ferrero',1,'Prodotto base ma efficace',28),
(332,'Luca Ferrari',5,'Robusto e resistente',1),
(333,'Beatrice Fontoura',4,'Robusto e resistente',36),
(334,'Luca Bruno',2,'Soddisfatto dellacquisto',43),
(335,'Andrea Moretti',2,'Entry level consigliato',6),
(336,'Giuseppe Fontana',5,'Upgrade consigliato',28),
(337,'Filippo De Santis',1,'Pro gamma',33),
(338,'Laura DAmico',5,'Leggero e maneggevole',2),
(339,'Filippo Marino',4,'Prodotto nella media, nulla di che',23),
(340,'Mattia Bianchi',3,'Super, superato le aspettative',3),
(341,'Samuele Greco',3,'Prodotto nella media, nulla di che',34),
(342,'Giacomo Mancini',3,'Entry level consigliato',48),
(343,'Andrea Riva',3,'Ottimo prodotto, lo ricompro',19),
(344,'Federica Costa',2,'Leggero e maneggevole',1),
(345,'Alessia Bianchi',2,'Lo uso ogni giorno',23),
(346,'Emma Gallo',1,'Robusto e resistente',45),
(347,'Gabriele Marchetti',4,'Qualita premium',12),
(348,'Marco Ricci',2,'Leggero e maneggevole',19),
(349,'Zoe Bianchi',2,'Indispensabile per il lavoro',24),
(350,'Miriam Riva',1,'Prodotto base ma efficace',20),
(351,'Martina Marchetti',2,'Acquisto azzeccato',22),
(352,'Mattia DAmico',1,'Leggero e maneggevole',46),
(353,'Andrea Ferrari',3,'Funzionale e pratico',21),
(354,'Giovanna Fontoura',2,'Pro gamma',44),
(355,'Tommaso Verdi',1,'Buon rapporto qualita prezzo',31),
(356,'Gabriele Bruno',5,'Lo uso ogni giorno',23),
(357,'Nicolas Conti',3,'Super, superato le aspettative',29),
(358,'Zoe De Luca',4,'Entry level consigliato',24),
(359,'Alice Bruno',4,'Consigliato, funziona bene',9),
(360,'Alessio Mancini',1,'Super, superato le aspettative',28),
(361,'Giulia Mancini',2,'Non male, pero migliorabile',22),
(362,'Ethan Romano',2,'Peccato per alcuni dettagli',17),
(363,'Sofia De Luca',5,'Non puoi sbagliare',15),
(364,'Alice DAmico',5,'Prodotto base ma efficace',20),
(365,'Nicolas Testa',4,'Consigliato, funziona bene',1),
(366,'Luca Ferrara',5,'Design elegante e moderno',33),
(367,'Michela Ferrara',3,'Prodotto base ma efficace',41),
(368,'Matteo Greco',3,'Entry level consigliato',1),
(369,'Alessio Ferrara',3,'Perfetto per le mie esigenze',24),
(370,'Giacomo Mancini',3,'Pro gamma',13),
(371,'Lorenzo Fontana',1,'Prodotto nella media, nulla di che',42),
(372,'Nicolas Ferrari',3,'Leggero e maneggevole',12),
(373,'Rebecca Bruno',4,'Ottimo prodotto, lo ricompro',50),
(374,'Emma DAmico',5,'Upgrade consigliato',43),
(375,'Lorenzo Ferrante',3,'Perfetto per iniziare',48),
(376,'Edoardo Bianchi',5,'Upgrade consigliato',22),
(377,'Giuseppe Ferrari',1,'Funzionale e pratico',18),
(378,'Matteo Ferrara',2,'Eccellente, qualita top',29),
(379,'Sofia De Santis',5,'Design elegante e moderno',36),
(380,'Sofia Verdi',3,'Eccellente, qualita top',16),
(381,'Emma Colombo',1,'Consigliato, funziona bene',31),
(382,'Luca Marino',2,'Design elegante e moderno',33),
(383,'Michela Gallo',3,'Leggero e maneggevole',14),
(384,'Gioele Costa',3,'Qualita premium',23),
(385,'Sofia Neri',3,'Qualita premium',29),
(386,'Piergiovanni Neri',4,'Leggero e maneggevole',48),
(387,'Alessandro Marchese',4,'Pro gamma',1),
(388,'Federica Verdi',3,'Prodotto base ma efficace',34),
(389,'Valentina Santoro',3,'Funzionale e pratico',41),
(390,'Anna Bruno',2,'Ottimo prodotto, lo ricompro',16),
(391,'Tommaso Costa',2,'Pro gamma',15),
(392,'Martina Santucci',2,'Il migliore della categoria',18),
(393,'Gabriele De Santis',1,'Prodotto base ma efficace',1),
(394,'Alessio De Luca',3,'Ottimo prodotto, lo ricompro',3),
(395,'Anna Mancini',5,'Semplicemente fantastico',28),
(396,'Giovanna Rossi',2,'Entry level consigliato',35),
(397,'Piergiovanni Marchese',3,'Ottimo prodotto, lo ricompro',11),
(398,'Matteo Colombo',5,'Potente e veloce',27),
(399,'Alessandro Marino',2,'Prodotto nella media, nulla di che',13),
(400,'Matteo Marchetti',3,'Prodotto nella media, nulla di che',29),
(401,'Zoe Costa',2,'Peccato per alcuni dettagli',16),
(402,'Federica Gallo',1,'Prezzo alto ma ne vale la pena',43),
(403,'Zoe Moretti',2,'Soddisfatto dellacquisto',10),
(404,'Lorenzo Colombo',2,'Robusto e resistente',39),
(405,'Ethan Verdi',5,'Funzionale e pratico',36),
(406,'LLaura Bianchi',2,'Buon rapporto qualita prezzo',26),
(407,'Sofia Neri',2,'Entry level consigliato',40),
(408,'Lorenzo Ferrero',5,'Indispensabile per il lavoro',11),
(409,'Christian De Santis',1,'Peccato per alcuni dettagli',49),
(410,'Andrea Ricci',3,'Soddisfatto dellacquisto',40),
(411,'Alice Neri',5,'Ottimo prodotto, lo ricompro',25),
(412,'Nicolas Marchese',4,'Non puoi sbagliare',28),
(413,'Chiara Bruno',1,'Robusto e resistente',1),
(414,'Luca Neri',3,'Soddisfatto dellacquisto',10),
(415,'Giacomo Verdi',3,'Lo uso ogni giorno',16),
(416,'Emma Riva',2,'Soddisfatto dellacquisto',22),
(417,'Piergiovanni Verdi',1,'Peccato per alcuni dettagli',24),
(418,'Marco Fontoura',2,'Prezzo alto ma ne vale la pena',27),
(419,'Gioele Fontana',5,'Ottimo prodotto, lo ricompro',49),
(420,'LLaura Testa',1,'Pro gamma',4),
(421,'Anna Ferrero',1,'Perfetto per le mie esigenze',4),
(422,'Alessio Mancini',1,'Il migliore della categoria',27),
(423,'Nicolas Fontoura',2,'Pro gamma',41),
(424,'Miriam Marchese',1,'Non puoi sbagliare',7),
(425,'Laura Bianchi',4,'Upgrade consigliato',33),
(426,'Tommaso De Luca',1,'Lo uso ogni giorno',48),
(427,'Giacomo Conti',5,'Da evitare, non acquistatelo',36),
(428,'Michela De Luca',3,'Non puoi sbagliare',24),
(429,'Giuseppe Rossi',5,'Upgrade consigliato',24),
(430,'Francesco Rossi',5,'Buon rapporto qualita prezzo',29),
(431,'Federica DAmico',1,'Prezzo alto ma ne vale la pena',40),
(432,'Gioele Ferrari',5,'Perfetto per le mie esigenze',47),
(433,'Laura De Santis',4,'Acquisto azzeccato',11),
(434,'Anna Bianchi',5,'Consigliato, funziona bene',9),
(435,'Andrea Fontana',5,'Deluso, mi aspettavo di piu',8),
(436,'Chiara Ferrari',1,'Peccato per alcuni dettagli',49),
(437,'Emma Bruno',3,'Pro gamma',40),
(438,'Federica Ferrara',5,'Lo uso ogni giorno',14),
(439,'Andrea Bruno',5,'Super, superato le aspettative',6),
(440,'Piergiovanni Mancini',4,'Potente e veloce',45),
(441,'Alice Bianchi',4,'Peccato per alcuni dettagli',42),
(442,'Miriam Santucci',3,'Upgrade consigliato',26),
(443,'Filippo Caruso',5,'Eccellente, qualita top',9),
(444,'Marco Conti',5,'Perfetto per le mie esigenze',37),
(445,'Chiara Bianchi',3,'Funzionale e pratico',24),
(446,'Federica Caruso',5,'Non male, pero migliorabile',37),
(447,'Francesco Neri',3,'Buon rapporto qualita prezzo',49),
(448,'Giovanna Ferrante',1,'Peccato per alcuni dettagli',47),
(449,'Zoe Verdi',3,'Qualita premium',19),
(450,'Tommaso Colombo',2,'Semplicemente fantastico',45),
(451,'Martina Bruno',4,'Non puoi sbagliare',14),
(452,'Ethan Santoro',1,'Non puoi sbagliare',45),
(453,'Alice Esposito',4,'Eccellente, qualita top',33),
(454,'Giulia Testa',4,'Pro gamma',24),
(455,'Edoardo Ferrari',2,'Il migliore della categoria',14),
(456,'Alice Marino',1,'Lo uso ogni giorno',25),
(457,'Ethan Marchese',4,'Acquisto azzeccato',48),
(458,'Valentina Fontoura',5,'Prodotto base ma efficace',34),
(459,'LLaura Ferrara',5,'Il migliore della categoria',46),
(460,'Gabriele Neri',4,'Non puoi sbagliare',27),
(461,'Laura Mancini',5,'Robusto e resistente',47),
(462,'Francesco Moretti',5,'Eccellente, qualita top',33),
(463,'Sofia Ferrara',4,'Ottimo prodotto, lo ricompro',11),
(464,'Anna Fontana',4,'Consigliato, funziona bene',38),
(465,'Filippo Bianchi',5,'Pro gamma',31),
(466,'Giulia Ferrara',4,'Non puoi sbagliare',35),
(467,'Zoe DAmico',5,'Qualita premium',32),
(468,'LLaura De Santis',4,'Super, superato le aspettative',41),
(469,'Ethan Marchese',2,'Deluso, mi aspettavo di piu',48),
(470,'Piergiovanni Fontoura',1,'Semplicemente fantastico',17),
(471,'Federica Neri',4,'Prodotto nella media, nulla di che',18),
(472,'Giacomo Rossi',3,'Peccato per alcuni dettagli',28),
(473,'Gabriele Fontana',1,'Buon rapporto qualita prezzo',42),
(474,'Laura De Santis',4,'Qualita premium',20),
(475,'Zoe Esposito',4,'Robusto e resistente',38),
(476,'Tommaso Moretti',4,'Buon rapporto qualita prezzo',47),
(477,'Samuele Fontana',3,'Non male, pero migliorabile',29),
(478,'Gabriele Costa',1,'Non male, pero migliorabile',37),
(479,'Emma Gentile',5,'Deluso, mi aspettavo di piu',11),
(480,'Alessia Ferrero',2,'Lo uso ogni giorno',7),
(481,'Gabriele Neri',3,'Lo uso ogni giorno',18),
(482,'Alessia Rossi',3,'Non male, pero migliorabile',24),
(483,'Giuseppe Mancini',2,'Prodotto nella media, nulla di che',18),
(484,'LLaura Bianchi',5,'Design elegante e moderno',13),
(485,'Giuseppe Marino',5,'Upgrade consigliato',24),
(486,'Alice Marchetti',1,'Prezzo alto ma ne vale la pena',41),
(487,'Federica Romano',1,'Consigliato, funziona bene',36),
(488,'Luca Bruno',4,'Semplicemente fantastico',19),
(489,'Chiara Rossi',5,'Super, superato le aspettative',19),
(490,'Tommaso Riva',5,'Design elegante e moderno',47),
(491,'Nicolas Santucci',5,'Peccato per alcuni dettagli',20),
(492,'Rebecca Russo',5,'Soddisfatto dellacquisto',20),
(493,'Lorenzo Esposito',1,'Buon rapporto qualita prezzo',27),
(494,'Marco Bruno',4,'Eccellente, qualita top',11),
(495,'Giuseppe Rossi',5,'Potente e veloce',42),
(496,'Edoardo Riva',3,'Da evitare, non acquistatelo',30),
(497,'Chiara Caruso',2,'Da evitare, non acquistatelo',36),
(498,'Mattia Costa',1,'Soddisfatto dellacquisto',15),
(499,'Valentina Neri',5,'Upgrade consigliato',6),
(500,'Christian Bruno',5,'Non male, pero migliorabile',12),
(501,'Alessia Russo',5,'Design elegante e moderno',22),
(502,'LLaura Costa',3,'Super, superato le aspettative',40),
(503,'Samuele Ricci',2,'Prodotto base ma efficace',22),
(504,'Francesco Romano',5,'Da evitare, non acquistatelo',15),
(505,'Miriam Marino',4,'Upgrade consigliato',21),
(506,'Giulia Ferrero',1,'Prodotto base ma efficace',50),
(507,'Rebecca Costa',5,'Indispensabile per il lavoro',21),
(508,'Emma Colombo',4,'Leggero e maneggevole',11),
(509,'Alessio Verdi',3,'Il migliore della categoria',37),
(510,'Mattia Bruno',3,'Acquisto azzeccato',24),
(511,'Mattia Marino',3,'Perfetto per le mie esigenze',16),
(512,'Emma Esposito',4,'Acquisto azzeccato',12),
(513,'Valentina Marino',5,'Eccellente, qualita top',24),
(514,'Valentina Moretti',2,'Prodotto base ma efficace',7),
(515,'Laura Ferrara',1,'Perfetto per le mie esigenze',33),
(516,'Marco Moretti',4,'Perfetto per le mie esigenze',30),
(517,'Marco Ferrara',2,'Entry level consigliato',36),
(518,'Alessia De Luca',3,'Design elegante e moderno',32),
(519,'Miriam Fontoura',2,'Acquisto azzeccato',30),
(520,'Samuele Ferrante',3,'Buon rapporto qualita prezzo',28),
(521,'LLaura Colombo',4,'Buon rapporto qualita prezzo',12),
(522,'Giulia Fontoura',4,'Potente e veloce',36),
(523,'Tommaso Conti',4,'Prodotto base ma efficace',41),
(524,'Alessia Esposito',2,'Indispensabile per il lavoro',10),
(525,'Martina Verdi',2,'Non male, pero migliorabile',22),
(526,'Mattia Marchese',1,'Non puoi sbagliare',28),
(527,'Emma Testa',5,'Prodotto base ma efficace',48),
(528,'Miriam De Luca',5,'Ottimo prodotto, lo ricompro',14),
(529,'Giulia Fontoura',2,'Ottimo prodotto, lo ricompro',31),
(530,'Matteo Rossi',4,'Non male, pero migliorabile',16),
(531,'Giovanna Caruso',4,'Consigliato, funziona bene',50),
(532,'Andrea Riva',4,'Pro gamma',45),
(533,'Zoe Santoro',4,'Prodotto base ma efficace',5),
(534,'Lorenzo Ferrari',4,'Lo uso ogni giorno',40),
(535,'Federica Marchese',4,'Lo uso ogni giorno',44),
(536,'Alice Ricci',4,'Peccato per alcuni dettagli',20),
(537,'Nicolas Moretti',2,'Super, superato le aspettative',46),
(538,'Mattia Ferrero',1,'Acquisto azzeccato',19),
(539,'Anna De Santis',5,'Qualita premium',9),
(540,'Marco Santoro',1,'Super, superato le aspettative',33),
(541,'Sofia Ferrara',5,'Design elegante e moderno',12),
(542,'Samuele Neri',5,'Peccato per alcuni dettagli',29),
(543,'Valentina De Santis',1,'Perfetto per le mie esigenze',11),
(544,'Matteo Marchetti',4,'Prezzo alto ma ne vale la pena',26),
(545,'Samuele Testa',3,'Qualita premium',10),
(546,'Gabriele DAmico',2,'Qualita premium',46),
(547,'Emma Gallo',4,'Ottimo prodotto, lo ricompro',14),
(548,'Nicolas Riva',3,'Qualita premium',45),
(549,'Mattia Bianchi',3,'Robusto e resistente',20),
(550,'Edoardo Verdi',1,'Da evitare, non acquistatelo',18),
(551,'Giovanna DAmico',1,'Design elegante e moderno',11),
(552,'Ethan DAmico',3,'Robusto e resistente',37),
(553,'Miriam Santucci',3,'Pro gamma',20),
(554,'Nicolas Fontoura',5,'Peccato per alcuni dettagli',40),
(555,'Christian Moretti',2,'Perfetto per le mie esigenze',22),
(556,'Valentina Moretti',4,'Lo uso ogni giorno',41),
(557,'Sofia De Santis',1,'Perfetto per iniziare',12),
(558,'Giacomo Marchese',1,'Prodotto base ma efficace',20),
(559,'Emma Ferrara',4,'Acquisto azzeccato',35),
(560,'Chiara Rossi',4,'Entry level consigliato',41),
(561,'Filippo Marchetti',1,'Pro gamma',41),
(562,'Emma Bruno',3,'Potente e veloce',13),
(563,'Gabriele Fontana',3,'Acquisto azzeccato',39),
(564,'Anna Marchese',5,'Leggero e maneggevole',20),
(565,'Miriam Romano',5,'Pro gamma',46),
(566,'Gabriele Riva',5,'Perfetto per iniziare',18),
(567,'Lorenzo Russo',5,'Prodotto nella media, nulla di che',30),
(568,'Alessio Bruno',3,'Upgrade consigliato',8),
(569,'Francesco Conti',3,'Design elegante e moderno',1),
(570,'Luca Marchese',1,'Semplicemente fantastico',31),
(571,'Giuseppe Verdi',1,'Non male, pero migliorabile',18),
(572,'Zoe Romano',5,'Semplicemente fantastico',9),
(573,'Anna Romano',3,'Il migliore della categoria',4),
(574,'Alice Colombo',1,'Deluso, mi aspettavo di piu',28),
(575,'Ethan Ferrara',1,'Perfetto per iniziare',40),
(576,'Edoardo Bianchi',2,'Leggero e maneggevole',16),
(577,'Alessio Russo',4,'Soddisfatto dellacquisto',2),
(578,'Lorenzo Verdi',5,'Perfetto per iniziare',45),
(579,'Emma Conti',4,'Pro gamma',17),
(580,'LLaura Esposito',2,'Funzionale e pratico',31),
(581,'Giacomo Marino',3,'Entry level consigliato',6),
(582,'Chiara Santoro',4,'Entry level consigliato',24),
(583,'Piergiovanni Neri',2,'Buon rapporto qualita prezzo',5),
(584,'Giuseppe Santucci',4,'Leggero e maneggevole',20),
(585,'Emma Ferrari',5,'Ottimo prodotto, lo ricompro',9),
(586,'Alice Marchese',2,'Indispensabile per il lavoro',32),
(587,'Francesco Russo',2,'Consigliato, funziona bene',19),
(588,'Michela Neri',1,'Upgrade consigliato',44),
(589,'Gioele Neri',3,'Prodotto nella media, nulla di che',36),
(590,'Ethan Santoro',3,'Super, superato le aspettative',32),
(591,'Federica Rossi',1,'Semplicemente fantastico',25),
(592,'Giacomo Conti',2,'Non male, pero migliorabile',48),
(593,'Piergiovanni Testa',3,'Perfetto per le mie esigenze',44),
(594,'Edoardo Riva',3,'Il migliore della categoria',9),
(595,'Alice Ferrara',2,'Lo uso ogni giorno',50),
(596,'Lorenzo Santoro',5,'Lo uso ogni giorno',4),
(597,'Gioele Marino',2,'Soddisfatto dellacquisto',21),
(598,'Chiara Bruno',4,'Deluso, mi aspettavo di piu',16),
(599,'Luca Marchese',3,'Non male, pero migliorabile',41),
(600,'Piergiovanni Ferrero',2,'Funzionale e pratico',28),
(601,'Martina Ferrara',3,'Non male, pero migliorabile',40),
(602,'Christian De Santis',4,'Upgrade consigliato',22),
(603,'Tommaso Marchese',3,'Prodotto base ma efficace',32),
(604,'Chiara Neri',1,'Qualita premium',31),
(605,'Giulia Caruso',1,'Indispensabile per il lavoro',16),
(606,'Edoardo Santucci',5,'Il migliore della categoria',29),
(607,'Piergiovanni Gallo',3,'Entry level consigliato',34),
(608,'Mattia Bruno',5,'Peccato per alcuni dettagli',27),
(609,'Ethan Ricci',3,'Super, superato le aspettative',1),
(610,'Ethan Santoro',5,'Acquisto azzeccato',2),
(611,'Giulia Ferrero',3,'Acquisto azzeccato',50),
(612,'Francesco Marino',5,'Funzionale e pratico',42),
(613,'Federica Gentile',2,'Deluso, mi aspettavo di piu',33),
(614,'Giulia Esposito',2,'Prezzo alto ma ne vale la pena',45),
(615,'Giovanna Romano',1,'Prodotto nella media, nulla di che',32),
(616,'Tommaso Santoro',4,'Funzionale e pratico',38),
(617,'Tommaso Bianchi',3,'Buon rapporto qualita prezzo',35),
(618,'Alice Marchetti',3,'Prezzo alto ma ne vale la pena',20),
(619,'Giuseppe Gentile',2,'Peccato per alcuni dettagli',12),
(620,'Ethan Gallo',5,'Il migliore della categoria',42),
(621,'Filippo Costa',3,'Leggero e maneggevole',35),
(622,'Emma Neri',3,'Pro gamma',28),
(623,'Alessio Fontoura',1,'Robusto e resistente',17),
(624,'Miriam Ferrara',4,'Perfetto per le mie esigenze',38),
(625,'Piergiovanni Marino',5,'Deluso, mi aspettavo di piu',10),
(626,'Edoardo Neri',1,'Robusto e resistente',4),
(627,'Alessia Ferrara',5,'Super, superato le aspettative',14),
(628,'Luca Ricci',2,'Perfetto per iniziare',39),
(629,'Martina Caruso',3,'Perfetto per iniziare',1),
(630,'Filippo Neri',4,'Non male, pero migliorabile',40),
(631,'Andrea Moretti',2,'Il migliore della categoria',34),
(632,'Giulia Moretti',4,'Deluso, mi aspettavo di piu',49),
(633,'Alessia Esposito',2,'Deluso, mi aspettavo di piu',24),
(634,'Edoardo Marchese',1,'Super, superato le aspettative',2),
(635,'Alice Bianchi',1,'Deluso, mi aspettavo di piu',21),
(636,'Beatrice Marchetti',4,'Qualita premium',14),
(637,'Gioele Esposito',2,'Prezzo alto ma ne vale la pena',4),
(638,'Emma De Santis',3,'Qualita premium',34),
(639,'Giacomo Santoro',2,'Lo uso ogni giorno',39),
(640,'Miriam Santoro',2,'Indispensabile per il lavoro',46),
(641,'Tommaso Santoro',3,'Prodotto base ma efficace',15),
(642,'Alessandro Marchetti',3,'Peccato per alcuni dettagli',24),
(643,'Nicolas Gentile',3,'Indispensabile per il lavoro',45),
(644,'Michela Russo',2,'Prodotto nella media, nulla di che',33),
(645,'Giovanna Testa',1,'Semplicemente fantastico',43),
(646,'Gabriele Gallo',3,'Prezzo alto ma ne vale la pena',13),
(647,'Beatrice De Luca',1,'Ottimo prodotto, lo ricompro',12),
(648,'Federica Moretti',2,'Deluso, mi aspettavo di piu',5),
(649,'Samuele Esposito',5,'Potente e veloce',16),
(650,'Marco Romano',3,'Super, superato le aspettative',37),
(651,'Gioele Costa',2,'Semplicemente fantastico',15),
(652,'Sofia Bruno',4,'Leggero e maneggevole',38),
(653,'Christian Ferrero',2,'Lo uso ogni giorno',22),
(654,'Matteo Rossi',5,'Super, superato le aspettative',11),
(655,'Alessandro Bruno',3,'Non male, pero migliorabile',48),
(656,'Rebecca Neri',5,'Lo uso ogni giorno',3),
(657,'Chiara Greco',3,'Super, superato le aspettative',4),
(658,'Giuseppe Bianchi',2,'Eccellente, qualita top',50),
(659,'Francesco Rossi',2,'Acquisto azzeccato',4),
(660,'Federica Riva',2,'Prezzo alto ma ne vale la pena',2),
(661,'Emma Gentile',2,'Leggero e maneggevole',43),
(662,'Alessia Gallo',4,'Potente e veloce',41),
(663,'Nicolas Costa',3,'Super, superato le aspettative',24),
(664,'Alessio Ferrante',1,'Leggero e maneggevole',32),
(665,'Miriam Santoro',1,'Eccellente, qualita top',5),
(666,'Francesco Bianchi',2,'Non male, pero migliorabile',26),
(667,'Luca Santoro',5,'Perfetto per le mie esigenze',43),
(668,'Samuele Moretti',4,'Non puoi sbagliare',11),
(669,'Andrea Ricci',3,'Acquisto azzeccato',39),
(670,'Emma Riva',5,'Non puoi sbagliare',21),
(671,'Christian Romano',3,'Prezzo alto ma ne vale la pena',45),
(672,'Martina Esposito',2,'Prodotto base ma efficace',11),
(673,'Piergiovanni Santucci',2,'Da evitare, non acquistatelo',14),
(674,'Sofia Mancini',3,'Pro gamma',16),
(675,'Zoe Fontoura',1,'Design elegante e moderno',2),
(676,'Giulia Colombo',1,'Potente e veloce',14),
(677,'Miriam Riva',3,'Perfetto per le mie esigenze',40),
(678,'Chiara Verdi',2,'Design elegante e moderno',49),
(679,'Anna Ferrara',5,'Potente e veloce',20),
(680,'Valentina Neri',4,'Indispensabile per il lavoro',23),
(681,'Anna Ferrara',5,'Da evitare, non acquistatelo',44),
(682,'Francesco Gallo',5,'Potente e veloce',37),
(683,'Michela Caruso',2,'Perfetto per iniziare',28),
(684,'Andrea Costa',5,'Design elegante e moderno',21),
(685,'Tommaso Ferrara',4,'Perfetto per iniziare',21),
(686,'Beatrice Fontoura',5,'Indispensabile per il lavoro',32),
(687,'Luca Marchese',5,'Leggero e maneggevole',17),
(688,'Emma Marchese',4,'Super, superato le aspettative',24),
(689,'Alice Marchese',4,'Funzionale e pratico',28),
(690,'Marco Marchetti',2,'Il migliore della categoria',32),
(691,'Anna Fontana',4,'Lo uso ogni giorno',44),
(692,'Edoardo Romano',3,'Potente e veloce',29),
(693,'Federica Ferrara',3,'Acquisto azzeccato',19),
(694,'Michela Moretti',1,'Consigliato, funziona bene',14),
(695,'Giulia Bianchi',1,'Leggero e maneggevole',50),
(696,'Lorenzo Esposito',4,'Peccato per alcuni dettagli',16),
(697,'Zoe Verdi',1,'Prodotto nella media, nulla di che',17),
(698,'Giulia Esposito',5,'Perfetto per le mie esigenze',50),
(699,'Michela Bruno',4,'Buon rapporto qualita prezzo',11),
(700,'Alice Bianchi',4,'Upgrade consigliato',28),
(701,'Luca Bianchi',4,'Leggero e maneggevole',12),
(702,'Ethan Bruno',2,'Soddisfatto dellacquisto',30),
(703,'Christian Ricci',1,'Funzionale e pratico',26),
(704,'Gioele Bianchi',5,'Lo uso ogni giorno',10),
(705,'Christian Conti',5,'Qualita premium',16),
(706,'Alice Ricci',2,'Soddisfatto dellacquisto',29),
(707,'Sofia Bianchi',4,'Funzionale e pratico',24),
(708,'Alessandro Ferrara',3,'Non male, pero migliorabile',49),
(709,'Alice Neri',3,'Da evitare, non acquistatelo',45),
(710,'Anna Ferrara',4,'Indispensabile per il lavoro',21),
(711,'Nicolas Gentile',4,'Upgrade consigliato',37),
(712,'Alice Gentile',4,'Deluso, mi aspettavo di piu',11),
(713,'Chiara Ricci',4,'Super, superato le aspettative',16),
(714,'Alice Ferrari',4,'Perfetto per iniziare',4),
(715,'Samuele Gentile',4,'Potente e veloce',12),
(716,'Lorenzo Ferrante',5,'Il migliore della categoria',23),
(717,'Giacomo De Santis',4,'Consigliato, funziona bene',4),
(718,'Tommaso Russo',5,'Ottimo prodotto, lo ricompro',6),
(719,'Alice Gentile',1,'Indispensabile per il lavoro',18),
(720,'Valentina Neri',1,'Indispensabile per il lavoro',11),
(721,'Lorenzo Bruno',2,'Upgrade consigliato',49),
(722,'Federica Romano',5,'Acquisto azzeccato',43),
(723,'Giulia Ferrero',1,'Upgrade consigliato',45),
(724,'Tommaso Bruno',3,'Da evitare, non acquistatelo',19),
(725,'Anna Ferrara',1,'Entry level consigliato',33),
(726,'Christian Moretti',1,'Perfetto per iniziare',18),
(727,'LLaura Costa',2,'Design elegante e moderno',31),
(728,'Giovanna Esposito',3,'Super, superato le aspettative',2),
(729,'Martina Bianchi',5,'Qualita premium',33),
(730,'Ethan Ricci',5,'Prodotto nella media, nulla di che',12),
(731,'Miriam Moretti',5,'Semplicemente fantastico',5),
(732,'Gabriele Santucci',5,'Perfetto per le mie esigenze',18),
(733,'Gioele Costa',1,'Leggero e maneggevole',24),
(734,'Giuseppe Marchese',4,'Semplicemente fantastico',30),
(735,'Christian Bianchi',2,'Entry level consigliato',48),
(736,'Gioele Fontoura',5,'Qualita premium',3),
(737,'Giuseppe Colombo',5,'Design elegante e moderno',33),
(738,'Beatrice Bruno',5,'Deluso, mi aspettavo di piu',24),
(739,'Michela Santoro',1,'Ottimo prodotto, lo ricompro',49),
(740,'Emma Ferrara',1,'Upgrade consigliato',25),
(741,'Tommaso Gallo',3,'Entry level consigliato',24),
(742,'Giulia DAmico',3,'Soddisfatto dellacquisto',37),
(743,'Samuele Bruno',4,'Indispensabile per il lavoro',10),
(744,'Beatrice Santoro',2,'Potente e veloce',44),
(745,'Beatrice Marino',2,'Semplicemente fantastico',35),
(746,'Andrea Bruno',1,'Buon rapporto qualita prezzo',2),
(747,'Lorenzo Ferrara',4,'Da evitare, non acquistatelo',10),
(748,'Emma Ferrero',4,'Non male, pero migliorabile',24),
(749,'Luca Gallo',1,'Super, superato le aspettative',5),
(750,'LLaura Fontana',3,'Semplicemente fantastico',36),
(751,'Alessia De Santis',1,'Non puoi sbagliare',27),
(752,'Beatrice Costa',2,'Deluso, mi aspettavo di piu',35),
(753,'Giacomo Ferrante',3,'Perfetto per le mie esigenze',4),
(754,'Edoardo Bianchi',3,'Prezzo alto ma ne vale la pena',11),
(755,'Andrea Mancini',2,'Non puoi sbagliare',50),
(756,'Marco Bruno',4,'Prodotto base ma efficace',19),
(757,'Tommaso Ferrari',4,'Design elegante e moderno',5),
(758,'Giacomo Russo',3,'Indispensabile per il lavoro',17),
(759,'Lorenzo Moretti',3,'Qualita premium',11),
(760,'LLaura Marchese',2,'Soddisfatto dellacquisto',48),
(761,'Alessio Costa',5,'Super, superato le aspettative',32),
(762,'Beatrice Gallo',5,'Peccato per alcuni dettagli',10),
(763,'Marco Testa',4,'Non male, pero migliorabile',9),
(764,'Zoe Marchese',5,'Qualita premium',39),
(765,'Beatrice Bianchi',1,'Buon rapporto qualita prezzo',26),
(766,'Lorenzo Riva',4,'Super, superato le aspettative',20),
(767,'Emma Marchese',2,'Perfetto per iniziare',3),
(768,'Beatrice Marchese',1,'Leggero e maneggevole',16),
(769,'Mattia Esposito',2,'Design elegante e moderno',12),
(770,'Zoe Conti',1,'Indispensabile per il lavoro',18),
(771,'Marco DAmico',5,'Semplicemente fantastico',40),
(772,'Sofia Marchetti',2,'Prodotto base ma efficace',27),
(773,'Alice Ferrante',4,'Lo uso ogni giorno',31),
(774,'Alice Moretti',3,'Deluso, mi aspettavo di piu',47),
(775,'Chiara Bruno',1,'Super, superato le aspettative',50),
(776,'Michela Marchese',1,'Prodotto base ma efficace',12),
(777,'Emma Bruno',5,'Prezzo alto ma ne vale la pena',24),
(778,'Valentina Santoro',4,'Perfetto per le mie esigenze',49),
(779,'Beatrice Moretti',1,'Lo uso ogni giorno',19),
(780,'Alessandro Esposito',5,'Non puoi sbagliare',49),
(781,'Matteo Conti',3,'Upgrade consigliato',37),
(782,'Edoardo Ferrari',5,'Funzionale e pratico',20),
(783,'Alessio Russo',5,'Qualita premium',31),
(784,'LLaura Bianchi',1,'Upgrade consigliato',23),
(785,'Sofia Ferrero',4,'Non male, pero migliorabile',36),
(786,'LLaura Marchetti',5,'Design elegante e moderno',3),
(787,'Alessio Santucci',4,'Consigliato, funziona bene',11),
(788,'Matteo Rossi',5,'Acquisto azzeccato',34),
(789,'Laura Ricci',3,'Qualita premium',47),
(790,'Alessia Riva',4,'Da evitare, non acquistatelo',20),
(791,'Nicolas DAmico',1,'Pro gamma',31),
(792,'Nicolas Conti',2,'Perfetto per le mie esigenze',40),
(793,'Andrea Bruno',2,'Soddisfatto dellacquisto',24),
(794,'Lorenzo Mancini',1,'Lo uso ogni giorno',18),
(795,'Valentina Santoro',5,'Qualita premium',15),
(796,'Samuele Marino',5,'Il migliore della categoria',42),
(797,'Luca Esposito',3,'Design elegante e moderno',8),
(798,'Giovanna Fontoura',4,'Eccellente, qualita top',27),
(799,'Marco Bruno',2,'Qualita premium',10),
(800,'Gioele Marino',3,'Non male, pero migliorabile',31),
(801,'Martina Marchese',2,'Lo uso ogni giorno',24),
(802,'Alice Ferrero',5,'Prodotto base ma efficace',42),
(803,'Emma DAmico',3,'Acquisto azzeccato',10),
(804,'Giovanna Fontana',5,'Prodotto base ma efficace',45),
(805,'Emma Russo',2,'Ottimo prodotto, lo ricompro',29),
(806,'Gioele Costa',4,'Perfetto per iniziare',13),
(807,'Mattia Moretti',1,'Prodotto nella media, nulla di che',38),
(808,'Edoardo De Luca',2,'Non male, pero migliorabile',8),
(809,'Piergiovanni Ferrero',4,'Funzionale e pratico',6),
(810,'Gioele Fontana',4,'Non puoi sbagliare',18),
(811,'Alice Colombo',2,'Prodotto base ma efficace',12),
(812,'Alice Rossi',4,'Leggero e maneggevole',47),
(813,'Gabriele Rossi',2,'Pro gamma',28),
(814,'Matteo DAmico',1,'Da evitare, non acquistatelo',23),
(815,'Gioele Bruno',2,'Perfetto per iniziare',36),
(816,'Alessio Riva',4,'Entry level consigliato',8),
(817,'Alessio Bruno',2,'Leggero e maneggevole',24),
(818,'Zoe Conti',3,'Il migliore della categoria',43),
(819,'Sofia Neri',3,'Prodotto nella media, nulla di che',50),
(820,'Luca Fontoura',1,'Prodotto base ma efficace',8),
(821,'Giuseppe Marchetti',4,'Upgrade consigliato',49),
(822,'Tommaso Colombo',3,'Perfetto per iniziare',38),
(823,'Martina Fontana',1,'Perfetto per le mie esigenze',3),
(824,'Alessandro Gallo',4,'Non puoi sbagliare',44),
(825,'Mattia Russo',4,'Pro gamma',14),
(826,'Mattia Neri',3,'Non puoi sbagliare',6),
(827,'Francesco Ferrara',2,'Lo uso ogni giorno',30),
(828,'Piergiovanni Rossi',2,'Pro gamma',23),
(829,'Federica Neri',1,'Perfetto per le mie esigenze',26),
(830,'Emma Colombo',2,'Design elegante e moderno',28),
(831,'Anna De Santis',5,'Non puoi sbagliare',9),
(832,'Mattia Conti',3,'Upgrade consigliato',47),
(833,'Luca Bruno',1,'Indispensabile per il lavoro',42),
(834,'Lorenzo Rossi',1,'Perfetto per le mie esigenze',43),
(835,'Chiara Marchetti',5,'Prodotto base ma efficace',6),
(836,'Marco Esposito',3,'Design elegante e moderno',35),
(837,'Christian Verdi',1,'Eccellente, qualita top',19),
(838,'Matteo Conti',1,'Consigliato, funziona bene',13),
(839,'Martina Fontana',2,'Design elegante e moderno',23),
(840,'Marco De Santis',1,'Deluso, mi aspettavo di piu',47),
(841,'Marco Neri',4,'Eccellente, qualita top',15),
(842,'Andrea Costa',4,'Il migliore della categoria',40),
(843,'Rebecca Ferrara',3,'Semplicemente fantastico',22),
(844,'Giovanna Verdi',1,'Eccellente, qualita top',45),
(845,'Martina Ferrero',5,'Pro gamma',40),
(846,'Giovanna Bianchi',5,'Potente e veloce',39),
(847,'Matteo Bruno',4,'Soddisfatto dellacquisto',47),
(848,'Beatrice Moretti',1,'Prodotto base ma efficace',2),
(849,'Giulia Marchese',3,'Buon rapporto qualita prezzo',48),
(850,'Gabriele Fontana',3,'Da evitare, non acquistatelo',22),
(851,'Filippo Santoro',3,'Semplicemente fantastico',12),
(852,'Federica Neri',2,'Semplicemente fantastico',43),
(853,'Ethan Conti',5,'Semplicemente fantastico',42),
(854,'Christian Santoro',4,'Buon rapporto qualita prezzo',21),
(855,'Samuele Bruno',3,'Eccellente, qualita top',38),
(856,'Giulia Fontana',3,'Potente e veloce',36),
(857,'Emma Verdi',3,'Perfetto per le mie esigenze',4),
(858,'Nicolas De Santis',1,'Deluso, mi aspettavo di piu',11),
(859,'Andrea Bianchi',3,'Prodotto base ma efficace',3),
(860,'Mattia De Luca',3,'Eccellente, qualita top',43),
(861,'Alessandro Moretti',5,'Robusto e resistente',45),
(862,'Chiara De Santis',2,'Funzionale e pratico',26),
(863,'Emma Marchese',5,'Buon rapporto qualita prezzo',48),
(864,'Matteo Bianchi',2,'Prodotto base ma efficace',38),
(865,'Samuele Verdi',5,'Qualita premium',50),
(866,'Federica Ferrante',1,'Perfetto per le mie esigenze',9),
(867,'Andrea Bianchi',2,'Robusto e resistente',26),
(868,'Alessio Ferrero',3,'Da evitare, non acquistatelo',35),
(869,'Matteo Moretti',5,'Prezzo alto ma ne vale la pena',22),
(870,'Giacomo Ferrari',5,'Prodotto base ma efficace',28),
(871,'Mattia Rossi',5,'Pro gamma',43),
(872,'Miriam Bianchi',2,'Perfetto per le mie esigenze',1),
(873,'Giulia Verdi',4,'Funzionale e pratico',39),
(874,'Christian De Luca',4,'Consigliato, funziona bene',8),
(875,'Alice Mancini',3,'Leggero e maneggevole',14),
(876,'Giulia Marino',1,'Leggero e maneggevole',23),
(877,'Laura Bianchi',3,'Prodotto base ma efficace',9),
(878,'Alessio Bruno',2,'Upgrade consigliato',3),
(879,'Federica De Santis',2,'Prodotto base ma efficace',20),
(880,'Nicolas Mancini',4,'Funzionale e pratico',44),
(881,'Mattia Marchese',4,'Leggero e maneggevole',28),
(882,'Rebecca Romano',5,'Potente e veloce',47),
(883,'Gabriele Bruno',3,'Qualita premium',32),
(884,'Giovanna Caruso',5,'Non male, pero migliorabile',19),
(885,'Mattia Bianchi',5,'Acquisto azzeccato',7),
(886,'Lorenzo Esposito',2,'Da evitare, non acquistatelo',32),
(887,'Valentina Marchetti',5,'Peccato per alcuni dettagli',36),
(888,'Valentina Santucci',5,'Perfetto per iniziare',4),
(889,'Edoardo Greco',5,'Prodotto base ma efficace',41),
(890,'Filippo Gentile',3,'Non puoi sbagliare',29),
(891,'Edoardo Gallo',1,'Semplicemente fantastico',13),
(892,'Luca Neri',4,'Robusto e resistente',31),
(893,'Tommaso Marchese',4,'Il migliore della categoria',15),
(894,'Gabriele DAmico',2,'Semplicemente fantastico',20),
(895,'Nicolas De Santis',2,'Consigliato, funziona bene',8),
(896,'Francesco Rossi',2,'Deluso, mi aspettavo di piu',3),
(897,'Christian Moretti',4,'Non puoi sbagliare',7),
(898,'Michela Neri',2,'Il migliore della categoria',18),
(899,'Laura De Luca',1,'Lo uso ogni giorno',12),
(900,'Filippo Riva',5,'Super, superato le aspettative',42),
(901,'Matteo Marchese',2,'Leggero e maneggevole',28),
(902,'Samuele Costa',4,'Prodotto nella media, nulla di che',7),
(903,'Chiara De Santis',2,'Acquisto azzeccato',12),
(904,'Tommaso Colombo',1,'Deluso, mi aspettavo di piu',10),
(905,'Samuele Russo',2,'Perfetto per iniziare',3),
(906,'Lorenzo Moretti',5,'Acquisto azzeccato',13),
(907,'Giulia Neri',5,'Peccato per alcuni dettagli',42),
(908,'Rebecca Gallo',1,'Prodotto base ma efficace',23),
(909,'Alessandro Bruno',5,'Eccellente, qualita top',23),
(910,'Mattia Santoro',4,'Pro gamma',25),
(911,'LLaura Neri',4,'Design elegante e moderno',7),
(912,'Filippo Bianchi',5,'Indispensabile per il lavoro',29),
(913,'Nicolas Verdi',3,'Super, superato le aspettative',5),
(914,'Tommaso Ferrara',5,'Robusto e resistente',48),
(915,'Giuseppe Moretti',1,'Indispensabile per il lavoro',37),
(916,'Miriam Bianchi',1,'Soddisfatto dellacquisto',47),
(917,'Gabriele Verdi',1,'Super, superato le aspettative',8),
(918,'Laura Fontoura',5,'Semplicemente fantastico',17),
(919,'Samuele Neri',5,'Il migliore della categoria',43),
(920,'Lorenzo Caruso',5,'Entry level consigliato',30),
(921,'Gabriele DAmico',1,'Robusto e resistente',15),
(922,'Valentina Ferrara',5,'Eccellente, qualita top',31),
(923,'Gabriele Testa',2,'Design elegante e moderno',24),
(924,'Ethan Marchese',1,'Pro gamma',11),
(925,'Valentina Marchese',2,'Perfetto per le mie esigenze',20),
(926,'Beatrice Costa',3,'Entry level consigliato',44),
(927,'Samuele Marchese',4,'Perfetto per le mie esigenze',13),
(928,'Alessandro Ferrero',5,'Non male, pero migliorabile',2),
(929,'Marco Caruso',5,'Ottimo prodotto, lo ricompro',38),
(930,'Beatrice Verdi',5,'Upgrade consigliato',8),
(931,'Giovanna Bruno',1,'Pro gamma',21),
(932,'Laura Colombo',5,'Deluso, mi aspettavo di piu',22),
(933,'Michela Bianchi',3,'Da evitare, non acquistatelo',49),
(934,'Giulia Bianchi',2,'Entry level consigliato',26),
(935,'Chiara Moretti',3,'Ottimo prodotto, lo ricompro',17),
(936,'Martina Santucci',1,'Design elegante e moderno',26),
(937,'Beatrice Costa',4,'Da evitare, non acquistatelo',14),
(938,'LLaura Esposito',4,'Semplicemente fantastico',31),
(939,'Emma De Santis',5,'Acquisto azzeccato',41),
(940,'Anna Mancini',5,'Entry level consigliato',24),
(941,'Martina Marino',5,'Super, superato le aspettative',34),
(942,'Giovanna Romano',4,'Buon rapporto qualita prezzo',11),
(943,'Mattia Ferrara',1,'Qualita premium',7),
(944,'Piergiovanni De Santis',1,'Prodotto base ma efficace',38),
(945,'Gioele Bianchi',4,'Super, superato le aspettative',27),
(946,'Mattia Rossi',5,'Da evitare, non acquistatelo',27),
(947,'Christian Marchese',3,'Ottimo prodotto, lo ricompro',5),
(948,'Giacomo Greco',4,'Super, superato le aspettative',15),
(949,'Valentina Gentile',3,'Eccellente, qualita top',40),
(950,'Edoardo Santoro',4,'Leggero e maneggevole',45),
(951,'Luca Ferrante',1,'Super, superato le aspettative',42),
(952,'Valentina Greco',1,'Robusto e resistente',40),
(953,'Alice De Luca',2,'Eccellente, qualita top',49),
(954,'Samuele Ferrara',4,'Upgrade consigliato',50),
(955,'Laura Rossi',4,'Buon rapporto qualita prezzo',30),
(956,'Laura Costa',2,'Il migliore della categoria',27),
(957,'Alessio Ferrante',2,'Perfetto per le mie esigenze',16),
(958,'Martina Bianchi',5,'Lo uso ogni giorno',5),
(959,'Piergiovanni De Luca',3,'Ottimo prodotto, lo ricompro',15),
(960,'Filippo Esposito',1,'Pro gamma',25),
(961,'Giulia Marino',5,'Qualita premium',38),
(962,'Gioele Mancini',2,'Potente e veloce',49),
(963,'Chiara Ricci',1,'Super, superato le aspettative',20),
(964,'Filippo Fontoura',1,'Robusto e resistente',6),
(965,'Rebecca DAmico',4,'Prezzo alto ma ne vale la pena',30),
(966,'Luca De Santis',4,'Buon rapporto qualita prezzo',29),
(967,'Alessio Russo',5,'Qualita premium',8),
(968,'Alessio Gallo',5,'Funzionale e pratico',32),
(969,'Sofia Bruno',3,'Non male, pero migliorabile',36),
(970,'Emma Greco',4,'Upgrade consigliato',9),
(971,'Alice Neri',2,'Indispensabile per il lavoro',9),
(972,'Emma Gentile',5,'Prodotto nella media, nulla di che',7),
(973,'Lorenzo Caruso',5,'Semplicemente fantastico',1),
(974,'Ethan Fontana',5,'Perfetto per le mie esigenze',25),
(975,'Federica Bruno',4,'Soddisfatto dellacquisto',30),
(976,'Michela Marchese',4,'Super, superato le aspettative',32),
(977,'Piergiovanni Ferrari',3,'Robusto e resistente',12),
(978,'Alice Santucci',5,'Super, superato le aspettative',22),
(979,'Mattia Marchetti',4,'Robusto e resistente',17),
(980,'Martina Bianchi',1,'Funzionale e pratico',20),
(981,'Martina Marchetti',1,'Deluso, mi aspettavo di piu',44),
(982,'Emma De Luca',2,'Entry level consigliato',15),
(983,'Lorenzo Ricci',5,'Indispensabile per il lavoro',30),
(984,'Giulia Greco',4,'Non puoi sbagliare',31),
(985,'Beatrice Ferrero',3,'Leggero e maneggevole',4),
(986,'Mattia Colombo',3,'Soddisfatto dellacquisto',50),
(987,'Giovanna Bruno',2,'Entry level consigliato',32),
(988,'Alessandro Neri',4,'Deluso, mi aspettavo di piu',28),
(989,'Laura Mancini',5,'Soddisfatto dellacquisto',27),
(990,'Nicolas Greco',4,'Consigliato, funziona bene',3),
(991,'LLaura Santucci',1,'Entry level consigliato',14),
(992,'Federica Fontoura',4,'Robusto e resistente',26),
(993,'Chiara Bruno',2,'Funzionale e pratico',7),
(994,'Alessandro Russo',5,'Soddisfatto dellacquisto',45),
(995,'Edoardo Fontana',2,'Indispensabile per il lavoro',6),
(996,'Edoardo Mancini',4,'Prodotto base ma efficace',18),
(997,'Anna Neri',1,'Pro gamma',8),
(998,'Giovanna Verdi',5,'Non puoi sbagliare',36),
(999,'Giulia Mancini',4,'Soddisfatto dellacquisto',10),
(1000,'Alessio Riva',1,'Leggero e maneggevole',21),
(1001,'Rebecca Costa',4,'Qualita premium',22),
(1002,'Filippo Romano',1,'Super, superato le aspettative',39),
(1003,'Gioele Santucci',5,'Indispensabile per il lavoro',36),
(1004,'Miriam De Santis',1,'Pro gamma',35),
(1005,'Emma Colombo',3,'Pro gamma',20),
(1006,'Matteo Santoro',5,'Il migliore della categoria',33),
(1007,'Laura Conti',3,'Buon rapporto qualita prezzo',15),
(1008,'Lorenzo Gentile',5,'Perfetto per le mie esigenze',22),
(1009,'Ethan Fontoura',4,'Lo uso ogni giorno',27),
(1010,'LLaura Ferrante',3,'Indispensabile per il lavoro',47),
(1011,'Zoe Ricci',2,'Peccato per alcuni dettagli',4),
(1012,'Alessandro Marchetti',1,'Prezzo alto ma ne vale la pena',38),
(1013,'Mattia Rossi',5,'Leggero e maneggevole',5),
(1014,'Samuele DAmico',1,'Prodotto nella media, nulla di che',22),
(1015,'Andrea Esposito',1,'Indispensabile per il lavoro',24),
(1016,'Sofia Santucci',5,'Non puoi sbagliare',50),
(1017,'Lorenzo Mancini',4,'Prodotto base ma efficace',17),
(1018,'Andrea Conti',5,'Buon rapporto qualita prezzo',20),
(1019,'Giuseppe Moretti',2,'Lo uso ogni giorno',48),
(1020,'Beatrice Marchetti',4,'Prodotto nella media, nulla di che',8),
(1021,'Michela Costa',5,'Peccato per alcuni dettagli',4),
(1022,'Giacomo Ferrara',5,'Prodotto nella media, nulla di che',23),
(1023,'Gioele De Luca',1,'Buon rapporto qualita prezzo',19),
(1024,'Nicolas Ricci',1,'Super, superato le aspettative',19),
(1025,'Gioele Gallo',3,'Ottimo prodotto, lo ricompro',23),
(1026,'Rebecca Testa',3,'Leggero e maneggevole',2),
(1027,'Chiara Fontana',4,'Eccellente, qualita top',4),
(1028,'Martina Marino',4,'Indispensabile per il lavoro',17),
(1029,'Miriam Moretti',4,'Entry level consigliato',3),
(1030,'Lorenzo Marchese',2,'Deluso, mi aspettavo di piu',41),
(1031,'Emma Verdi',1,'Potente e veloce',12),
(1032,'Emma Conti',1,'Peccato per alcuni dettagli',10),
(1033,'Giuseppe Mancini',4,'Semplicemente fantastico',23),
(1034,'Beatrice Ferrara',5,'Lo uso ogni giorno',30),
(1035,'Gabriele Caruso',2,'Prodotto nella media, nulla di che',12),
(1036,'Valentina Romano',1,'Buon rapporto qualita prezzo',13),
(1037,'Giacomo Fontoura',3,'Potente e veloce',13),
(1038,'Martina Santucci',4,'Leggero e maneggevole',34),
(1039,'Giulia Colombo',1,'Leggero e maneggevole',47),
(1040,'Federica Verdi',3,'Prodotto nella media, nulla di che',44),
(1041,'Beatrice Gentile',1,'Entry level consigliato',4),
(1042,'Gabriele Caruso',2,'Qualita premium',1),
(1043,'Emma Marchetti',3,'Acquisto azzeccato',33),
(1044,'Edoardo Ferrara',2,'Indispensabile per il lavoro',17),
(1045,'Chiara Ferrara',5,'Entry level consigliato',21),
(1046,'Laura Testa',5,'Qualita premium',38),
(1047,'Nicolas Ferrara',4,'Prezzo alto ma ne vale la pena',35),
(1048,'Emma Ferrante',1,'Soddisfatto dellacquisto',12),
(1049,'Gennaro',5,'Ottimo Prdotto, resistente',1),
(1050,'Roberto',5,'Ottimo prodotto',4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `sanction`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sanction` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `sanction`;

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `citizen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen`
--

LOCK TABLES `citizen` WRITE;
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT INTO `citizen` VALUES
(1,'RSSMRA80A01H501Q','Mario','Rossi'),
(2,'BNCLCU85B02H501R','Luca','Bianchi'),
(3,'VRDANN90B41H501A','Anna','Verdi'),
(4,'NRIPLA85C12H501B','Paolo','Neri'),
(5,'GLLSRA92A22H501C','Sara','Gialli'),
(6,'BLUMRC88M33H501D','Marco','Blu'),
(7,'RNCLNE87L54H501E','Elena','Arancioni'),
(8,'VLFVNI83E65H501F','Giovanni','Viola'),
(9,'RSSFRN91U76H501G','Francesca','Rosa'),
(10,'MRRLSD84I87H501H','Alessandro','Marrone');
/*!40000 ALTER TABLE `citizen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanction`
--

DROP TABLE IF EXISTS `sanction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `paid_on` date DEFAULT NULL,
  `price` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `citizen_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgpxyu3jx5nlmd7vtr7s6fxcbi` (`citizen_id`),
  CONSTRAINT `FKgpxyu3jx5nlmd7vtr7s6fxcbi` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanction`
--

LOCK TABLES `sanction` WRITE;
/*!40000 ALTER TABLE `sanction` DISABLE KEYS */;
INSERT INTO `sanction` VALUES
(1,'2024-01-15','Mario','Rossi',NULL,150,'Eccesso di velocita','UNPAID',1),
(2,'2024-02-20','Luca','Bianchi','2024-02-25',50,'Parcometro scaduto','PAID',2),
(3,'2024-03-01','Anna','Verdi','2024-03-05',80,'Divieto di sosta','PAID',3),
(4,'2024-03-02','Paolo','Neri',NULL,150,'Semaforo rosso','UNPAID',4),
(5,'2024-03-03','Sara','Gialli','2024-03-10',100,'Zona traffico limitato','PAID',5),
(6,'2024-03-04','Marco','Blu',NULL,75,'Parcheggio indebito','UNPAID',6),
(7,'2024-03-05','Elena','Arancioni','2024-03-07',200,'Eccesso di velocita','PAID',7),
(8,'2024-03-06','Giovanni','Viola',NULL,40,'Sosta prolungata','UNPAID',8),
(9,'2024-03-07','Francesca','Rosa','2024-03-12',80,'Fermata autobus','PAID',9),
(10,'2024-03-08','Alessandro','Marrone',NULL,120,'Passaggio pedonale','UNPAID',10);
/*!40000 ALTER TABLE `sanction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ticket`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ticket` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `ticket`;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `closed_on` datetime(6) DEFAULT NULL,
  `closure` varchar(255) DEFAULT NULL,
  `open_on` datetime(6) DEFAULT NULL,
  `opening` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `vault`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `vault` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `vault`;

--
-- Table structure for table `membership_request`
--

DROP TABLE IF EXISTS `membership_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `income` int(11) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `vault` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_request`
--

LOCK TABLES `membership_request` WRITE;
/*!40000 ALTER TABLE `membership_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `visits`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `visits` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `visits`;

--
-- Current Database: `webclinic`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `webclinic` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `webclinic`;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `history` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES
(1,'Via Roma 10','Roma','1985-08-01','marco.rossi@email.it','Marco','M','Ipertensione','Rossi','RSSMRC85M01H501Z'),
(2,'Via Milano 22','Milano','1990-01-01','giulia.bianchi@email.it','Giulia','F','Nessuna patologia','Bianchi','BNCGLI90A41F205X'),
(3,'Via Napoli 5','Napoli','1978-04-15','luca.verdi@email.it','Luca','M','Diabete tipo 2','Verdi','VRDLCU78D15L219K'),
(4,'Via Torino 8','Torino','1992-03-12','anna.ferrari@email.it','Anna','F','Allergia ai pollini','Ferrari','FRRNNA92C52G273T'),
(5,'Via Venezia 33','Venezia','1988-06-20','paolo.colombo@email.it','Paolo','M','Asma bronchiale','Colombo','CLMPLA88H20F839W'),
(6,'Via Firenze 17','Firenze','1995-07-05','sara.ricci@email.it','Sara','F','Nessuna patologia','Ricci','RCCSRA95L45D612R'),
(7,'Via Bologna 42','Bologna','1982-02-10','andrea.marino@email.it','Andrea','M','Ernia del disco','Marino','MRNNDR82B10A944J'),
(8,'Via Palermo 3','Palermo','1999-11-15','elena.greco@email.it','Elena','F','Nessuna patologia','Greco','GRCLNE99S55C351H'),
(9,'Via Genova 19','Genova','1975-05-28','francesco.bruno@email.it','Francesco','M','Colesterolo alto','Bruno','BRNFNC75E28D969L'),
(10,'Via Bari 7','Bari','1987-12-20','chiara.conti@email.it','Chiara','F','Tiroide','Conti','CNTCHR87T60B354M');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
--
-- Current Database: `pcconfigurator`
--

USE `pcconfigurator`;

--
-- Current Database: `product`
--

USE `product`;

--
-- Current Database: `sanction`
--

USE `sanction`;

--
-- Current Database: `ticket`
--

USE `ticket`;

--
-- Current Database: `vault`
--

USE `vault`;

--
-- Current Database: `visits`
--

USE `visits`;

--
-- Current Database: `webclinic`
--

USE `webclinic`;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-13 10:43:54
