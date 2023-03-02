-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for debian-linux-gnu (x86_64)
 --
 -- Host: 127.0.0.1    Database: pharmacy-recommendation
 -- ------------------------------------------------------
 -- Server version	10.7.3-MariaDB-1:10.7.3+maria~focal

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
 -- Table structure for table `pharmacy`
 --

 DROP TABLE IF EXISTS `pharmacy`;
 /*!40101 SET @saved_cs_client     = @@character_set_client */;
 /*!40101 SET character_set_client = utf8 */;
 CREATE TABLE `pharmacy` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `created_date` datetime(6) DEFAULT NULL,
   `modified_date` datetime(6) DEFAULT NULL,
   `latitude` double NOT NULL,
   `longitude` double NOT NULL,
   `pharmacy_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `pharmacy_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 /*!40101 SET character_set_client = @saved_cs_client */;

 --
 -- Dumping data for table `pharmacy`
 --
