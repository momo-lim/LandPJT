-- MySQL dump 10.13  Distrib 8.0.28, for macos12.2 (arm64)
--
-- Host: 3.35.135.123    Database: ssafyland
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `filepdf`
--

DROP TABLE IF EXISTS `filepdf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filepdf` (
  `file_id` bigint NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `UK_tk815142480e7pro9tondnvlu` (`file_name`),
  UNIQUE KEY `UK_q2eedy459fs2s1dxumx0o6ph1` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filepdf`
--

LOCK TABLES `filepdf` WRITE;
/*!40000 ALTER TABLE `filepdf` DISABLE KEYS */;
INSERT INTO `filepdf` VALUES (1,'application/pdf','(220516) 6기 2학기 프로젝트 최종평가안내.pdf','/data/files','1bc2836a-4cfa-4ca1-8035-4be9eb88be0f');
/*!40000 ALTER TABLE `filepdf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treasure`
--

DROP TABLE IF EXISTS `treasure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treasure` (
  `treasure_id` bigint NOT NULL AUTO_INCREMENT,
  `treasure_code` varchar(255) NOT NULL,
  `treasure_name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`treasure_id`),
  KEY `FK3jr1w9wq7q5x9bmv9dm1bfc68` (`user_id`),
  CONSTRAINT `FK3jr1w9wq7q5x9bmv9dm1bfc68` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treasure`
--

LOCK TABLES `treasure` WRITE;
/*!40000 ALTER TABLE `treasure` DISABLE KEYS */;
/*!40000 ALTER TABLE `treasure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `forest_record` time DEFAULT NULL,
  `treasure_count` int NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'00:00:00',0,'서울_1반_sa'),(2,'00:00:00',0,'구미_1반_강싸피'),(3,'00:00:00',0,'서울_1반_테스터'),(4,'00:00:00',0,'서울_1반_sdsd'),(5,'00:00:00',0,'서울_1반_test1'),(6,'00:00:00',0,'서울_1반_test2'),(7,'00:00:00',0,'서울_1반_test3'),(8,'00:00:00',0,'서울_1반_ㄴㅇㄴㅇ'),(9,'00:00:00',0,'서울_1반_이상현'),(10,'00:00:00',1,'서울_1반_최대호'),(11,'00:00:00',0,'서울_1반_명성'),(12,'00:00:00',0,'서울_1반_aa'),(13,'00:00:00',0,'서울_1반_홍길동'),(14,'00:00:00',0,'서울_1반_ㅇㅇㅇ'),(15,'00:00:00',0,'서울_1반_dd'),(16,'00:04:32',0,'구미_1반_최대호'),(17,'00:00:00',0,'서울_1반_ㅁㅁ'),(18,'00:00:00',0,'서울_1반_wer'),(19,'00:00:00',0,'서울_1반_rer'),(20,'00:09:48',0,'구미_1반_명성'),(21,'00:00:00',0,'서울_1반_ww'),(22,'00:00:00',0,'서울_1반_audtj'),(23,'00:00:00',0,'서울_1반_rptmx'),(24,'00:00:00',0,'서울_1반_ㅁㄴㅇㅁㄴ'),(25,'00:00:00',0,'서울_1반_명게스트'),(26,'00:00:00',0,'서울_1반_게스트'),(27,'00:00:00',0,'구미_1반_12312'),(28,'00:00:00',0,'서울_1반_asdas'),(29,'00:05:03',1,'구미_1반_임현모'),(30,'00:00:00',0,'서울_1반_성우'),(31,'00:00:00',0,'대전_2반_명성'),(32,'00:00:00',0,'서울_1반_임현모'),(33,'00:00:00',0,'서울_1반_ㅁㄴㅇ'),(34,'00:00:00',0,'서울_1반_343'),(35,'00:00:00',0,'서울_1반_ㅈㄷㄱ'),(36,'00:00:00',0,'서울_1반_audgt'),(37,'00:00:00',0,'서울_1반_ㄴㅁㅇㄹ'),(38,'00:00:00',0,'서울_1반_eee'),(39,'00:04:48',2,'구미_1반_이상현'),(40,'00:00:00',0,'서울_1반_ss'),(41,'00:00:00',0,'서울_1반_eeeee'),(42,'00:00:00',0,'서울_1반_3ㅈ'),(43,'00:00:00',0,'서울_1반_asdf'),(44,'00:00:00',0,'서울_1반_qq'),(45,'00:00:00',0,'서울_1반_상현1'),(46,'00:00:00',0,'서울_1반_상현2'),(47,'00:00:00',0,'서울_1반_ㅁㄴㅇㄹ'),(48,'00:00:00',0,'서울_1반_ddrf'),(49,'00:00:00',0,'서울_1반_홋스트'),(50,'00:00:00',0,'서울_1반_호슻트'),(51,'00:00:00',0,'서울_1반_tt'),(52,'00:00:00',0,'서울_1반_qwe'),(53,'00:00:00',0,'서울_1반_w'),(54,'00:00:00',0,'서울_1반_k'),(55,'00:00:00',0,'서울_1반_김다예'),(56,'00:00:00',0,'부울경_5반_김다예'),(57,'00:00:00',0,'부울경_2반_김다예'),(58,'00:00:00',0,'구미_1반_김성우'),(59,'00:00:00',0,'서울_1반_ㄴㅇㄹ'),(60,'00:00:00',0,'서울_1반_ㄷㄷ'),(61,'00:00:30',0,'서울_1반_red'),(62,'00:00:15',0,'부울경_1반_rrr'),(63,'00:00:00',0,'서울_1반_drgf'),(64,'00:00:00',0,'구미_1반_이명성'),(65,'00:00:28',0,'서울_1반_gfd'),(66,'00:00:57',0,'대전_1반_sdg'),(67,'00:00:00',0,'서울_1반_ㅈㄷ'),(68,'00:00:14',0,'대전_1반_ddd'),(69,'00:08:17',0,'구미_1반_김다예'),(70,'00:00:05',0,'광주_6반_qwe'),(71,'00:00:00',0,'서울_1반_ㅂㅈㄷ'),(72,'00:00:00',0,'서울_1반_dlfma'),(73,'00:00:12',0,'서울_1반_sdf'),(74,'00:00:00',0,'서울_1반_123'),(75,'00:00:00',0,'서울_1반_ㅂㅈㄱ'),(76,'00:00:00',0,'서울_1반_ㅈㅈ'),(77,'00:00:00',0,'서울_1반_호스트'),(78,'00:00:00',0,'서울_1반_sadf'),(79,'00:00:00',0,'서울_1반_asd'),(80,'00:00:00',0,'서울_1반_qw'),(81,'00:00:00',0,'서울_1반_00'),(82,'00:00:00',0,'서울_1반_kk'),(83,'00:00:00',0,'서울_1반_sse'),(84,'00:06:34',0,'구미_1반_성우'),(85,'00:00:00',0,'광주_3반_김다예');
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

-- Dump completed on 2022-05-19 16:58:57
