-- MySQL dump 10.13  Distrib 8.2.0, for macos13.5 (arm64)
--
-- Host: 127.0.0.1    Database: pos-electricity-app
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pelanggan`
--

DROP TABLE IF EXISTS `pelanggan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelanggan` (
  `pelanggan_id` int NOT NULL AUTO_INCREMENT COMMENT 'The customers ID''s',
  `tarif_listrik_id` int NOT NULL,
  `no_meter` varchar(15) NOT NULL COMMENT 'The customer''s number meter. Example: 12238888, 12334445, etc.',
  `nama` varchar(150) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  PRIMARY KEY (`pelanggan_id`),
  UNIQUE KEY `noMeter_unique` (`no_meter`),
  KEY `pelanggan_tarif_listrik_tarif_listrik_id_fk` (`tarif_listrik_id`),
  CONSTRAINT `pelanggan_tarif_listrik_tarif_listrik_id_fk` FOREIGN KEY (`tarif_listrik_id`) REFERENCES `tarif_listrik` (`tarif_listrik_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='The customers';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelanggan`
--

LOCK TABLES `pelanggan` WRITE;
/*!40000 ALTER TABLE `pelanggan` DISABLE KEYS */;
INSERT INTO `pelanggan` VALUES (1,1,'8776672178','Japra','Cilidig'),(2,2,'8776672177','Jayadinar','Cibunar Parung Panjang SGP'),(3,2,'8776672179','Jayadun','Cibunar Parung Panjang SGP');
/*!40000 ALTER TABLE `pelanggan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tagihan`
--

DROP TABLE IF EXISTS `tagihan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tagihan` (
  `tagihan_id` int NOT NULL AUTO_INCREMENT COMMENT 'The tagihan ID''s',
  `pelanggan_id` int NOT NULL COMMENT 'The customer ID''s',
  `tahun_tagihan` varchar(4) NOT NULL COMMENT 'The year of invoice. Example: 2023, 2022, etc.',
  `bulan_tagihan` varchar(2) NOT NULL COMMENT 'The monthly invoice. Example: 01 (January), 02, (February), etc.',
  `pemakaian` decimal(10,0) NOT NULL COMMENT 'The total invoice. Exmple: 1000000, 500000, 100000, etc.',
  PRIMARY KEY (`tagihan_id`),
  KEY `tagihan_pelanggan_pelanggan_id_fk` (`pelanggan_id`),
  CONSTRAINT `tagihan_pelanggan_pelanggan_id_fk` FOREIGN KEY (`pelanggan_id`) REFERENCES `pelanggan` (`pelanggan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='The customer''s invoice';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tagihan`
--

LOCK TABLES `tagihan` WRITE;
/*!40000 ALTER TABLE `tagihan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tagihan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarif_listrik`
--

DROP TABLE IF EXISTS `tarif_listrik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarif_listrik` (
  `tarif_listrik_id` int NOT NULL AUTO_INCREMENT COMMENT 'The Tarif Listrik ID''s',
  `kode_tarif` varchar(4) NOT NULL COMMENT 'Example value: R101, R102, R103, R201, etc.',
  `beban` decimal(10,0) NOT NULL COMMENT 'The ''Daya'' in VA. Example: 900, 1300, 2200, etc.',
  `tarif_perkwh` decimal(10,0) NOT NULL COMMENT 'The bill per kWh. Example: 1352, 1444, etc.',
  `created_by` int NOT NULL COMMENT 'The created by User ID',
  `created_date` datetime NOT NULL COMMENT 'Date time created date',
  `updated_date` datetime DEFAULT NULL COMMENT 'The date time updated date',
  PRIMARY KEY (`tarif_listrik_id`),
  UNIQUE KEY `kode_tarif` (`kode_tarif`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarif_listrik`
--

LOCK TABLES `tarif_listrik` WRITE;
/*!40000 ALTER TABLE `tarif_listrik` DISABLE KEYS */;
INSERT INTO `tarif_listrik` VALUES (1,'R101',900,1000,2,'2024-01-09 00:25:42','2024-01-09 15:38:59'),(2,'R102',1300,1600,3,'2024-01-09 00:26:54',NULL);
/*!40000 ALTER TABLE `tarif_listrik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'The user ID''s',
  `username` varchar(12) NOT NULL,
  `password` text NOT NULL COMMENT 'Hashed password',
  `hak_akses` int NOT NULL COMMENT 'The access right. 1. ADMIN 2. SALES',
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='All the user has access to the app';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'user-002','[-31, -28, 49, 28, 66, -110, -64, -102, 38, 61, 84, 85, 19, -59, -66, -45]',1,'2024-01-08 00:58:27'),(3,'user-003','[-47, 63, -21, 9, -112, -82, -35, -72, 39, 44, -7, 82, 76, -100, -96, -116]',1,'2024-01-08 01:01:31'),(4,'user-004','[47, 15, -83, -127, -65, -102, -54, 33, 25, -105, -73, 87, -119, -22, -7, -15]',1,'2024-01-08 01:10:32'),(5,'user-005','[-28, 120, -105, 11, -29, -41, -112, 101, 81, -95, 114, 86, 13, -113, -41, -74]',1,'2024-01-08 01:11:26'),(6,'123','[15, 85, 0, -93, 105, 73, -99, -104, 31, -89, 80, 114, -107, 57, 15, -46]',2,'2024-01-09 19:02:26');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-10 22:33:48
