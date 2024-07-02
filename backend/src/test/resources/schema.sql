-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 17, 2023 at 12:09 PM
-- Server version: 8.0.35-0ubuntu0.20.04.1
-- PHP Version: 7.4.3-4ubuntu2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zbijlsmy001`
--

-- --------------------------------------------------------

--
-- Table structure for table `InviteMatch`
--

CREATE TABLE `InviteMatch` (
  `idInviteMatch` int NOT NULL,
  `status` enum('pending','match','declined') NOT NULL DEFAULT 'pending',
  `message` varchar(255) NOT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_sender` int NOT NULL,
  `id_recipient` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Label`
--

CREATE TABLE `Label` (
  `idLabel` int NOT NULL,
  `label_name` varchar(45) NOT NULL,
  `label_description` varchar(255) DEFAULT NULL,
  `label_type` enum('GENRE','INSTRUMENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Label_has_MusicSample`
--

CREATE TABLE `Label_has_MusicSample` (
  `idLabel` int NOT NULL,
  `idMusicSample` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MusicSample`
--

CREATE TABLE `MusicSample` (
  `idMusicSample` int NOT NULL,
  `idUser` int NOT NULL,
  `sample_name` varchar(45) NOT NULL,
  `sample_length` int DEFAULT NULL,
  `bpm` int DEFAULT NULL,
  `sample_description` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MusicSampleDislike`
--

CREATE TABLE `MusicSampleDislike` (
  `idMusicSample` int NOT NULL,
  `idUser` int NOT NULL,
  `date_dislike` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MusicSampleLike`
--

CREATE TABLE `MusicSampleLike` (
  `idMusicSample` int NOT NULL,
  `idUser` int NOT NULL,
  `date_liked` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MusicSamplePlay`
--

CREATE TABLE `MusicSamplePlay` (
  `idMusicSample` int NOT NULL,
  `idUser` int NOT NULL,
  `date_played` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MusicSampleReport`
--

CREATE TABLE `MusicSampleReport` (
  `idMusicSample` int NOT NULL,
  `idUser` int NOT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `date_report` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `idUser` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `description` longtext,
  `account_type` enum('ARTIST','MANAGER','ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_modified` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User_has_Label`
--

CREATE TABLE `User_has_Label` (
  `idUser` int NOT NULL,
  `idLabel` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `InviteMatch`
--
ALTER TABLE `InviteMatch`
  ADD PRIMARY KEY (`idInviteMatch`),
  ADD KEY `fk_InviteMatch_User1_idx` (`id_sender`),
  ADD KEY `fk_InviteMatch_User2_idx` (`id_recipient`);

--
-- Indexes for table `Label`
--
ALTER TABLE `Label`
  ADD PRIMARY KEY (`idLabel`);

--
-- Indexes for table `Label_has_MusicSample`
--
ALTER TABLE `Label_has_MusicSample`
  ADD PRIMARY KEY (`idLabel`,`idMusicSample`),
  ADD KEY `fk_Label_has_MusicSample_MusicSample1_idx` (`idMusicSample`),
  ADD KEY `fk_Label_has_MusicSample_Label1_idx` (`idLabel`);

--
-- Indexes for table `MusicSample`
--
ALTER TABLE `MusicSample`
  ADD PRIMARY KEY (`idMusicSample`);

--
-- Indexes for table `MusicSampleDislike`
--
ALTER TABLE `MusicSampleDislike`
  ADD PRIMARY KEY (`idMusicSample`,`idUser`);

--
-- Indexes for table `MusicSampleLike`
--
ALTER TABLE `MusicSampleLike`
  ADD PRIMARY KEY (`idMusicSample`,`idUser`);

--
-- Indexes for table `MusicSamplePlay`
--
ALTER TABLE `MusicSamplePlay`
  ADD PRIMARY KEY (`idMusicSample`,`idUser`,`date_played`);

--
-- Indexes for table `MusicSampleReport`
--
ALTER TABLE `MusicSampleReport`
  ADD PRIMARY KEY (`idMusicSample`,`idUser`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`);

--
-- Indexes for table `User_has_Label`
--
ALTER TABLE `User_has_Label`
  ADD PRIMARY KEY (`idUser`,`idLabel`),
  ADD KEY `fk_User_has_Label_Label1_idx` (`idLabel`),
  ADD KEY `fk_User_has_Label_User_idx` (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `InviteMatch`
--
ALTER TABLE `InviteMatch`
  MODIFY `idInviteMatch` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Label`
--
ALTER TABLE `Label`
  MODIFY `idLabel` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `MusicSample`
--
ALTER TABLE `MusicSample`
  MODIFY `idMusicSample` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `idUser` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `InviteMatch`
--
ALTER TABLE `InviteMatch`
  ADD CONSTRAINT `fk_InviteMatch_User1` FOREIGN KEY (`id_sender`) REFERENCES `User` (`idUser`),
  ADD CONSTRAINT `fk_InviteMatch_User2` FOREIGN KEY (`id_recipient`) REFERENCES `User` (`idUser`);

--
-- Constraints for table `Label_has_MusicSample`
--
ALTER TABLE `Label_has_MusicSample`
  ADD CONSTRAINT `fk_Label_has_MusicSample_Label1` FOREIGN KEY (`idLabel`) REFERENCES `Label` (`idLabel`),
  ADD CONSTRAINT `fk_Label_has_MusicSample_MusicSample1` FOREIGN KEY (`idMusicSample`) REFERENCES `MusicSample` (`idMusicSample`);

--
-- Constraints for table `User_has_Label`
--
ALTER TABLE `User_has_Label`
  ADD CONSTRAINT `fk_User_has_Label_Label1` FOREIGN KEY (`idLabel`) REFERENCES `Label` (`idLabel`),
  ADD CONSTRAINT `fk_User_has_Label_User` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
