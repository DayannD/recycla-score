CREATE
DATABASE  IF NOT EXISTS `recycla-score` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE
`recycla-score`;
-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: recycla-score
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Materiaux`
--

DROP TABLE IF EXISTS `Materiaux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Materiaux`
(
    `ID_Materiau`       int          NOT NULL AUTO_INCREMENT,
    `Nom_Materiau`      varchar(255) NOT NULL,
    `Type_Recyclage`    varchar(100) DEFAULT NULL,
    `Cout_Recyclage`    float        DEFAULT NULL,
    `Energie_Recyclage` float        DEFAULT NULL,
    PRIMARY KEY (`ID_Materiau`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materiaux`
--

LOCK
TABLES `Materiaux` WRITE;
/*!40000 ALTER TABLE `Materiaux` DISABLE KEYS */;
INSERT INTO `Materiaux` (`Nom_Materiau`, `Type_Recyclage`, `Cout_Recyclage`, `Energie_Recyclage`)
VALUES ('Bois', 'Recyclable', 5.0, 10.0),
       ('Métal', 'Recyclable', 8.0, 15.0),
       ('Plastique', 'Non recyclable', 3.0, 5.0),
       ('Verre', 'Recyclable', 6.0, 12.0),
       ('Papier', 'Recyclable', 4.0, 8.0),
       ('Textile', 'Recyclable', 7.0, 14.0),
       ('Aluminium', 'Recyclable', 9.0, 18.0),
       ('Carton', 'Recyclable', 5.5, 11.0);
/*!40000 ALTER TABLE `Materiaux` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Monomaterial`
--

DROP TABLE IF EXISTS `Monomaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Monomaterial`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `material`      enum('ACIER_INOX',
        'POLY_EXPAN',
        'BOIS',
        'VERRE',
        'PLASTIQUE',
        'METAL',
        'PAPIER',
        'TEXTILE',
        'ALUMINIUM',
        'CARTON',) DEFAULT NULL,
    `name`          varchar(255) DEFAULT NULL,
    `recyclability` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Monomaterial`
--

LOCK
TABLES `Monomaterial` WRITE;
/*!40000 ALTER TABLE `Monomaterial` DISABLE KEYS */;
INSERT INTO recycla_score.Monomaterial (name, material, recyclability)
VALUES ('Monomatériau 7', 'PAPIER', 'Recyclable'),
       ('Monomatériau 8', 'TEXTILE', 'Recyclable'),
       ('Monomatériau 9', 'ALUMINIUM', 'Recyclable'),
       ('Monomatériau 10', 'CARTON', 'Recyclable'),
       ('Monomatériau 11', 'ACIER_INOX', 'Recyclable'),
       ('Monomatériau 12', 'POLY_EXPAN', 'Non recyclable'),
       ('Monomatériau 13', 'BOIS', 'Recyclable'),
       ('Monomatériau 14', 'VERRE', 'Recyclable'),
       ('Monomatériau 15', 'PLASTIQUE', 'Non recyclable'),
       ('Monomatériau 16', 'METAL', 'Recyclable');
/*!40000 ALTER TABLE `Monomaterial` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Produit_Materiaux`
--

DROP TABLE IF EXISTS `Produit_Materiaux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Produit_Materiaux`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `ID_Produit`  int   DEFAULT NULL,
    `ID_Materiau` int   DEFAULT NULL,
    `Quantite`    float DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `ID_Produit` (`ID_Produit`),
    KEY           `ID_Materiau` (`ID_Materiau`),
    CONSTRAINT `Produit_Materiaux_ibfk_1` FOREIGN KEY (`ID_Produit`) REFERENCES `Produits` (`ID_Produit`),
    CONSTRAINT `Produit_Materiaux_ibfk_2` FOREIGN KEY (`ID_Materiau`) REFERENCES `Materiaux` (`ID_Materiau`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Produit_Materiaux`
--

LOCK
TABLES `Produit_Materiaux` WRITE;
/*!40000 ALTER TABLE `Produit_Materiaux` DISABLE KEYS */;
INSERT INTO `Produit_Materiaux`
VALUES (1, 1, 1, 0.5),
       (2, 1, 2, 0.5),
       (3, 2, 3, 1),
       (4, 3, 4, 0.3),
       (5, 4, 1, 0.2),
       (6, 4, 2, 0.8),
       (7, 1, 1, 0.5),
       (8, 1, 2, 0.5),
       (9, 2, 3, 1),
       (10, 3, 4, 0.3),
       (11, 4, 1, 0.2),
       (12, 4, 2, 0.8);
/*!40000 ALTER TABLE `Produit_Materiaux` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Produits`
--

DROP TABLE IF EXISTS `Produits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Produits`
(
    `ID_Produit`          int          NOT NULL AUTO_INCREMENT,
    `Nom_Produit`         varchar(255) NOT NULL,
    `Description`         tinytext,
    `Score_Recyclabilite` float        DEFAULT NULL,
    `Poids`               float        DEFAULT NULL,
    `Volume`              float        DEFAULT NULL,
    `URL_Image`           varchar(255) DEFAULT NULL,
    PRIMARY KEY (`ID_Produit`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Produits`
--

LOCK
TABLES `Produits` WRITE;
/*!40000 ALTER TABLE `Produits` DISABLE KEYS */;
INSERT INTO `Produits`
VALUES (1, 'Produit A', 'Description du Produit A', 0.8, 1.2, 0.003, NULL),
       (2, 'Produit B', 'Description du Produit B', 0.6, 0.8, 0.002, NULL),
       (3, 'Produit C', 'Description du Produit C', 0.7, 1.5, 0.004, NULL),
       (4, 'Produit D', 'Description du Produit D', 0.9, 0.9, 0.001, NULL),
       (5, 'Produit v', 'Description du Produit A', 0.8, 1.2, 0.003, NULL),
       (6, 'Produit B', 'Description du Produit B', 0.6, 0.8, 0.002, NULL),
       (7, 'Produit C', 'Description du Produit C', 0.7, 1.5, 0.004, NULL),
       (8, 'Produit D', 'Description du Produit D', 0.9, 0.9, 0.001, NULL);
/*!40000 ALTER TABLE `Produits` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `libelle` enum('ADMIN','USER') DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK
TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role`
VALUES (1, 'USER'),
       (2, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (5, 'USER'),
       (6, 'USER'),
       (9, 'ADMIN'),
       (10, 'USER'),
       (11, 'ADMIN'),
       (12, 'USER'),
       (13, 'USER'),
       (14, 'USER');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Utilisateurs`
--

DROP TABLE IF EXISTS `Utilisateurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utilisateurs`
(
    `ID_Utilisateur`  bigint NOT NULL AUTO_INCREMENT,
    `actif`           bit(1) NOT NULL,
    `Email`           varchar(255) DEFAULT NULL,
    `Mot_de_Passe`    varchar(255) DEFAULT NULL,
    `Nom_Utilisateur` varchar(255) DEFAULT NULL,
    `role_id`         bigint       DEFAULT NULL,
    PRIMARY KEY (`ID_Utilisateur`),
    UNIQUE KEY `UK_po0p8x2yrshi75jehwkvo5sjo` (`role_id`),
    CONSTRAINT `FKrg6xvmaap2yev66v5y57mxqt4` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utilisateurs`
--

LOCK
TABLES `Utilisateurs` WRITE;
/*!40000 ALTER TABLE `Utilisateurs` DISABLE KEYS */;
INSERT INTO `Utilisateurs`
VALUES (1, _binary '', 'test2@gmail.com', '$2a$10$XRyk5dR/lPzexYH6dvKxKeW8kxW8PBezuC07Wnms/auKhI6xvfWgu', 'Benoit', 1),
       (2, _binary '\0', 'test@gmail.com', '$2a$10$8Zj0jpzJ3X/sS0VNN7uSt.1ZrsBeoY8d.MjvcwOt1LU1kvpAcWUUC', 'Jacque', 2),
       (3, _binary '\0', 'test3@gmail.com', '$2a$10$oYcrnBoqgWEZP8mUePN/eOKbvJs9erWwGX99.rpUIP0J7O/0sYRuW', 'Jacque',
        3),
       (4, _binary '\0', 'test4@gmail.com', '$2a$10$TSRm5Svh6qfRmuQsxZIpiesvkSOWwzMrOHG53Yb7g040sm/3SavT2', 'Jacque',
        4),
       (5, _binary '\0', 'test8@gmail.com', '$2a$10$ul98g4RrBDhLZaf0zVGTF.8RbB6SIWH1ZtTSQ7O9dr/ZT6wc2K726', 'Jacque',
        5),
       (6, _binary '\0', 'test9@gmail.com', '$2a$10$wQNzANhSiQJ85SstduJo5eXhasYkxwky1QveuO1czRepbCg3SrHU6', 'Pierre',
        6),
       (9, _binary '', 'test11@gmail.com', '$2a$10$wPfKRtOlwBFJyjeAEBouheDbRDicuPTrkOyro7GfndW9YznvmFAim', 'Jean', 9),
       (10, _binary '\0', 'test12@gmail.com', '$2a$10$drrLLvIJBPeExuxzG1J4LuJgRxNoWUMadp8XURMqu6bAcoc976l6O', 'Jean',
        10),
       (11, _binary '', 'test118@gmail.com', '$2a$10$H/5b3cOYEN8JfiHSYO8hs.2AVrCogLOADRw8492Mfu4/PArep79ye', 'test',
        11),
       (12, _binary '', 'test119@gmail.com', '$2a$10$SymyLT2NR.5cpan/h4YLSeTzve2VUcZ.i8q0XXLTmWp9VJ0MHH2Mi', 'terst',
        12),
       (13, _binary '\0', 'test1881@gmail.com', '$2a$10$gXfkJzcVPkmvjDG7vLFPCOtrnyKqyG4Enf5My85OzQ2WXD.BqAFba', 'test',
        13),
       (14, _binary '\0', 'test192@gmail.com', '$2a$10$6rfUO71dJcSwbKUuC3GlpeIG.sAAmMGoHdYNgzcctta9dBBaByYny', 'Jean',
        14);
/*!40000 ALTER TABLE `Utilisateurs` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Validation`
--

DROP TABLE IF EXISTS `Validation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Validation`
(
    `id`                         bigint NOT NULL AUTO_INCREMENT,
    `Activation`                 datetime(6) DEFAULT NULL,
    `code`                       varchar(255) DEFAULT NULL,
    `creationDate`               datetime(6) DEFAULT NULL,
    `expire`                     datetime(6) DEFAULT NULL,
    `utilisateur_ID_Utilisateur` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_n6wid0s8r9yt0ipg7pn1cs00k` (`utilisateur_ID_Utilisateur`),
    CONSTRAINT `FKbj928f8atgb0pn0vrr9enyq6g` FOREIGN KEY (`utilisateur_ID_Utilisateur`) REFERENCES `Utilisateurs` (`ID_Utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Validation`
--

LOCK
TABLES `Validation` WRITE;
/*!40000 ALTER TABLE `Validation` DISABLE KEYS */;
INSERT INTO `Validation`
VALUES (1, NULL, '612102', '2023-12-06 15:57:17.430261', '2023-12-06 16:57:17.430261', 2),
       (2, NULL, '367349', '2023-12-06 16:05:48.651774', '2023-12-06 17:05:48.651774', 3),
       (3, NULL, '922856', '2023-12-06 16:15:45.922308', '2023-12-06 17:15:45.922308', 4),
       (4, NULL, '600807', '2023-12-06 16:30:14.544955', '2023-12-06 17:30:14.544955', 5),
       (5, NULL, '811251', '2023-12-07 08:38:54.087591', '2023-12-07 09:38:54.087591', 6),
       (9, NULL, '361747', '2023-12-07 10:46:00.091153', '2023-12-07 11:46:00.091153', 10),
       (12, NULL, '551945', '2023-12-15 08:35:35.847820', '2023-12-15 09:35:35.847820', 13),
       (13, NULL, '048966', '2024-01-08 12:58:10.597250', '2024-01-08 13:58:10.597250', 14);
/*!40000 ALTER TABLE `Validation` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `produit_tags`
--

DROP TABLE IF EXISTS `produit_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit_tags`
(
    `Produit_ID_Produit` int NOT NULL,
    `tag`                enum('BATTERIES_D3E','ECOLOGY','GLASS','HAZARDOUS_WASTE','METAL','ORGANIC','PAPER_CARDBOARD','PLASTIC','VHU','WOOD') DEFAULT NULL,
    KEY                  `FKsi9vljcwwe67g6hx8bc045w19` (`Produit_ID_Produit`),
    CONSTRAINT `FKsi9vljcwwe67g6hx8bc045w19` FOREIGN KEY (`Produit_ID_Produit`) REFERENCES `Produits` (`ID_Produit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit_tags`
--

LOCK
TABLES `produit_tags` WRITE;
/*!40000 ALTER TABLE `produit_tags` DISABLE KEYS */;
INSERT INTO `produit_tags`
VALUES (1, 'ECOLOGY'),
       (1, 'HAZARDOUS_WASTE'),
       (2, 'ECOLOGY'),
       (2, 'ORGANIC'),
       (3, 'ECOLOGY'),
       (3, 'PLASTIC'),
       (4, 'ECOLOGY'),
       (4, 'HAZARDOUS_WASTE'),
       (5, 'ECOLOGY'),
       (5, 'GLASS'),
       (6, 'ECOLOGY'),
       (6, 'PAPER_CARDBOARD'),
       (7, 'ECOLOGY'),
       (7, 'WOOD'),
       (8, 'ECOLOGY'),
       (8, 'PAPER_CARDBOARD');
/*!40000 ALTER TABLE `produit_tags` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 12:01:40