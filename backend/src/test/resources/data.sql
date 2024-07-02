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

--
-- Dumping data for table `InviteMatch`
--

INSERT INTO `InviteMatch` (`idInviteMatch`, `status`, `message`, `date_created`, `id_sender`, `id_recipient`) VALUES
(51, 'declined', 'The witch backs tow-truck.', '2023-12-05 19:50:15', 60, 70),
(52, 'pending', 'The modernist strives coat.', '2023-12-05 19:50:15', 60, 70),
(53, 'declined', 'The distortion finds instance.', '2023-12-05 19:50:15', 60, 70),
(54, 'match', 'The absence shaves speech.', '2023-12-05 19:50:15', 60, 70),
(55, 'match', 'The softening frames watchmaker.', '2023-12-05 19:50:15', 60, 70),
(56, 'declined', 'The counsel hangs fame.', '2023-12-05 19:50:15', 60, 70),
(57, 'declined', 'The visit bumps trader.', '2023-12-05 19:50:15', 60, 70),
(58, 'match', 'The soybean fancies kitty.', '2023-12-05 19:50:15', 60, 70),
(59, 'match', 'The carotene gives attic.', '2023-12-05 19:50:15', 60, 70),
(60, 'pending', 'The slapstick displays tenth.', '2023-12-05 19:50:15', 60, 70),
(61, 'match', 'The parchment meddles suspect.', '2023-12-05 19:50:15', 60, 70),
(62, 'pending', 'The filth illustrates bear.', '2023-12-05 19:50:15', 60, 70),
(63, 'declined', 'The shortage hums collagen.', '2023-12-05 19:50:15', 60, 70),
(64, 'declined', 'The clinic coordinates optimization.', '2023-12-05 19:50:15', 60, 70),
(65, 'declined', 'The cesspool pleads commercial.', '2023-12-05 19:50:15', 60, 70),
(66, 'declined', 'The toothpaste forsakes standoff.', '2023-12-05 19:50:15', 60, 70),
(67, 'pending', 'The tendency sprouts lacquerware.', '2023-12-05 19:50:15', 60, 70),
(68, 'pending', 'The faith phones college.', '2023-12-05 19:50:15', 60, 70),
(69, 'declined', 'The gamebird queues beating.', '2023-12-05 19:50:15', 60, 70),
(70, 'match', 'The fleck enforces substitution.', '2023-12-05 19:50:15', 60, 70),
(71, 'match', 'The regulator depends soap.', '2023-12-05 19:50:15', 60, 70),
(72, 'pending', 'The refectory conducts pickup.', '2023-12-05 19:50:15', 60, 70),
(73, 'pending', 'The dime confesses aquifer.', '2023-12-05 19:50:15', 60, 70),
(74, 'pending', 'The overhead expects chord.', '2023-12-05 19:50:15', 60, 70),
(75, 'pending', 'The modernity answers flash.', '2023-12-05 19:50:15', 60, 70),
(76, 'match', 'The cacao buys crewmember.', '2023-12-05 19:50:15', 60, 70),
(77, 'match', 'The half-brother tries amber.', '2023-12-05 19:50:15', 60, 70),
(78, 'match', 'The aggression depends stack.', '2023-12-05 19:50:15', 60, 70),
(79, 'declined', 'The commandment lands stupidity.', '2023-12-05 19:50:15', 60, 70),
(80, 'pending', 'The overweight reports warmth.', '2023-12-05 19:50:15', 60, 70),
(81, 'declined', 'The website scatters airport.', '2023-12-05 19:50:15', 60, 70),
(82, 'pending', 'The meadow phones nurse.', '2023-12-05 19:50:15', 60, 70),
(83, 'match', 'The mukluk backs initialize.', '2023-12-05 19:50:15', 60, 70),
(84, 'pending', 'The learning experiments seed.', '2023-12-05 19:50:15', 60, 70),
(85, 'declined', 'The cod verifies mobile.', '2023-12-05 19:50:15', 60, 70),
(86, 'match', 'The headlight excites census.', '2023-12-05 19:50:15', 60, 70),
(87, 'match', 'The stamp lasts racing.', '2023-12-05 19:50:15', 60, 70),
(88, 'pending', 'The tourist handles synthesis.', '2023-12-05 19:50:15', 60, 70),
(89, 'pending', 'The alpaca pedals finish.', '2023-12-05 19:50:15', 60, 70),
(90, 'match', 'The uncertainty jails gratitude.', '2023-12-05 19:50:15', 60, 70),
(91, 'pending', 'The microlending confronts numeracy.', '2023-12-05 19:50:15', 60, 70),
(92, 'declined', 'The foundation overhears pond.', '2023-12-05 19:50:15', 60, 70),
(93, 'match', 'The girdle informs vanity.', '2023-12-05 19:50:15', 60, 70),
(94, 'pending', 'The exercise closes crisis.', '2023-12-05 19:50:15', 60, 70),
(95, 'match', 'The efficacy rises baboon.', '2023-12-05 19:50:15', 60, 70),
(96, 'declined', 'The opposition presents quince.', '2023-12-05 19:50:15', 60, 70),
(97, 'pending', 'The sanity arrests photodiode.', '2023-12-05 19:50:15', 60, 70),
(98, 'pending', 'The yurt pokes teletype.', '2023-12-05 19:50:15', 60, 70),
(99, 'match', 'The dragon nails travel.', '2023-12-05 19:50:15', 60, 70),
(100, 'match', 'The cousin perceives molding.', '2023-12-05 19:50:15', 60, 70);

--
-- Dumping data for table `Label`
--

INSERT INTO `Label` (`idLabel`, `label_name`, `label_description`, `label_type`, `date_created`, `date_modified`) VALUES
(1, 'Piano', NULL, 'INSTRUMENT', '2023-12-09 15:07:33', '2023-12-09 15:07:33'),
(2, 'Guitar', NULL, 'INSTRUMENT', '2023-12-09 15:07:33', '2023-12-09 15:07:33'),
(3, 'Hiphop', NULL, 'GENRE', '2023-12-09 15:07:33', '2023-12-09 15:07:33'),
(7, 'Violin', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(8, 'Flute', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(9, 'Cello', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(10, 'Saxophone', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(11, 'Harp', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(12, 'Bass guitar', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(13, 'Drums', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(14, 'Trumpet', NULL, 'INSTRUMENT', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(15, 'House', NULL, 'GENRE', '2023-12-10 18:57:34', '2023-12-10 18:57:34'),
(16, 'Techno', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(17, 'Classical', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(18, 'Jazz', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(19, 'Rap', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(20, 'R&B', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(22, 'Pop', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(23, 'Blues', NULL, 'GENRE', '2023-12-10 18:58:56', '2023-12-10 18:58:56'),
(24, 'Rock', NULL, 'GENRE', '2023-12-10 18:59:33', '2023-12-10 18:59:33'),
(25, 'Country', NULL, 'GENRE', '2023-12-10 18:59:33', '2023-12-10 18:59:33'),
(26, 'Reggae', NULL, 'GENRE', '2023-12-10 18:59:33', '2023-12-10 18:59:33');

--
-- Dumping data for table `Label_has_MusicSample`
--

INSERT INTO `Label_has_MusicSample` (`idLabel`, `idMusicSample`) VALUES
(2, 201),
(3, 201),
(2, 202),
(3, 202),
(1, 203),
(3, 203),
(1, 204),
(3, 204);

--
-- Dumping data for table `MusicSample`
--

INSERT INTO `MusicSample` (`idMusicSample`, `idUser`, `sample_name`, `sample_length`, `bpm`, `sample_description`, `date_created`, `date_modified`) VALUES
(201, 58, 'Gates Guitar I', 3, 109, 'Jazzy guitar sample by me', '2023-12-09 14:54:41', '2023-12-09 14:54:41'),
(202, 58, 'Gates Guitar II', 38, 119, 'Lofi guitar sample by me', '2023-12-09 14:54:49', '2023-12-09 14:54:49'),
(203, 66, 'Mild Piano Loop [FREE]', 30, 160, 'Lofi Piano Loop (E Minor).', '2023-12-09 14:57:12', '2023-12-09 14:57:12'),
(204, 66, 'Upbeat Piano Loop', 5, 140, 'Upbeat Hardstyle piano loop', '2023-12-09 14:57:14', '2023-12-09 14:57:14'),
(215, 1, 'SprintDemoSample', 280, 120, 'Demoing File Upload', NULL, NULL),
(216, 66, 'SprintDemoSample', 280, 120, 'Demoing File Upload', NULL, NULL),
(217, 91, 'Second Amazing Sprint Review Sample', 280, 120, 'Demoing File Upload', NULL, NULL);

--
-- Dumping data for table `MusicSampleLike`
--

INSERT INTO `MusicSampleLike` (`idMusicSample`, `idUser`, `date_liked`) VALUES
(201, 72, '2023-12-08 16:50:29');

--
-- Dumping data for table `MusicSamplePlay`
--

INSERT INTO `MusicSamplePlay` (`idMusicSample`, `idUser`, `date_played`) VALUES
(51, 72, '2023-12-08 17:22:17'),
(51, 72, '2023-12-08 17:22:23'),
(51, 72, '2023-12-08 19:59:55'),
(52, 72, '2023-12-08 19:08:55'),
(69, 72, '2023-12-08 19:53:48'),
(75, 72, '2023-12-08 19:54:08');

--
-- Dumping data for table `MusicSampleReport`
--

INSERT INTO `MusicSampleReport` (`idMusicSample`, `idUser`, `message`, `date_report`) VALUES
(51, 72, 'nsfw', '2023-12-08 17:34:39');

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`idUser`, `email`, `password`, `first_name`, `last_name`, `phonenumber`, `contact_info`, `occupation`, `country`, `description`, `account_type`, `date_created`, `date_modified`) VALUES
(53, 'admin1@google.com', '$2a$10$0Qk.S/0EzhTxT0LLwj72ZOUyNLffO5SPLJkrM8ScTLcA02tjEWeoC', 'Bobby', 'Schmurda', '9111', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-27 14:30:15', '2023-11-27 14:30:15'),
(54, 'admin2@google.com', '$2a$10$lA280v06R9Vq4Sp6qLsT7e9xaGYbLhrMUCAadzJHBliRMGUxy7Q62', 'Bobby', 'Schmurda', '911', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-27 14:39:01', '2023-11-27 14:39:01'),
(55, 'admin3@microsoft.com', '$2a$10$06ElU0DaGA035ZKZRPlmCelsbjVxU1hIBTJeoS6mj8KHbmFjIb/zm', 'Bill', 'Gates', '551124', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-27 21:41:18', '2023-11-27 21:41:18'),
(58, 'bill@gates.com', '$2a$10$Qj0QEz1l3ZQu71GPsSBOr.fzUPDoM1XNnSH951PFfq2BBVd1ONaim', 'Bill', 'Gates', '33444', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-28 10:59:25', '2023-11-28 10:59:25'),
(59, 'admin3@google.com', '$2a$10$.gUfjs4e7VyqZCRQ6AASMefD6atobaBZqn95bmcMaO4JdVBp./4u6', 'Bobby', 'Schmurda', '91133', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-28 12:40:41', '2023-11-28 12:40:41'),
(60, 'genghiskhan@supremeleader.com', '$2a$10$PIW8i7OizqUUwrJJ.q.dG.Ws6eD.7L0B1SGoAGs27R6Iylg739KRK', 'Genghis', 'Khan', '112911', NULL, NULL, NULL, '', 'ARTIST', '2023-11-28 13:19:40', '2023-11-28 13:19:40'),
(61, 'testertesttest@gmail.com', '$2a$10$D3K0QGn6AHYqWNpUTqM1W.PKgENHjepizkLkOgIE0OHecdN4RPQJa', 'Bobby', 'Schmurda', '144422211', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-28 13:32:55', '2023-11-28 13:32:55'),
(63, 'oegetester@gmail.com', '$2a$10$BIfPuIwbr7u5J2O/lYVFKu4D1p/t1QEzfUnhgAK2WJb3YsAySY6B.', 'Oege', 'Schmurda', '525266621', 'www.nu.nl', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-28 13:51:58', '2023-11-28 13:51:58'),
(64, 'bill@gatesss.com', '$2a$10$.Ae468BsXLZLUXCVS8JkWO32VFnQmj45YYywYx9dNOC/Ghg.mz.O.', 'Bill', 'Gates', '3345544', 'www.nu.nl HvA', 'Cheese Maker', 'America', '', 'ARTIST', '2023-11-28 14:02:35', '2023-11-28 14:02:35'),
(65, 'abc@xyz.com', '$2a$10$QnfRqwOOHa2cq/FF0vJgi.h70JywbKSRPlv1ZeEI5VxyS2uwZtTli', 'Bobby', 'Schmurda', '555666', NULL, NULL, NULL, '', 'MANAGER', '2023-11-28 15:16:35', '2023-11-28 15:16:35'),
(66, 'werk@xyz.com', '$2a$10$Zgks94BcaCRT7PTeOVljY.E6NiGU7ooTYnMtzIBhFSsi.Vzt8swhy', 'Geert', 'Milders', '3353757', NULL, NULL, NULL, '', 'ARTIST', '2023-11-28 16:17:30', '2023-11-28 16:17:30'),
(67, 'jbjbjbj@jcjl.com', '$2a$10$tGEMYUAQkf2DWdDwEt1VBOyU57T4whdWKPssUuv0gofVvYszxtbeu', 'pass', 'word', '4646464646', NULL, NULL, NULL, '', 'ARTIST', '2023-11-28 17:19:48', '2023-11-28 17:19:48'),
(69, 'abc@xyzzzz.com', '$2a$10$7JUc7Sk4NReyMxgpbHF.5.muD4aQS7lbCrxIHJ1YN2bdZMojnTC.G', 'Geert', 'SuperMilders', '4639873987456', 'Mild HvA', NULL, NULL, '', 'ARTIST', '2023-11-28 21:03:30', '2023-11-28 21:03:30'),
(70, 'abc@blalblallz.com', '$2a$10$mlHsxrvye/NIfsnhsZPqiuRt9/ytSCqqeVZItPtjtVekHoqVr3Ssm', 'Bla', 'Blabla', '3344556969696', NULL, NULL, NULL, '', 'ARTIST', '2023-11-29 11:07:16', '2023-11-29 11:07:16'),
(71, 'sprint@review.com', '$2a$10$Z5sRY/7g45UavCdJ1t0JLeZ4NJSxU6QX8PCyiw7XUhueDNmD5vo.6', 'Sprint', 'Review', '346563456346', NULL, NULL, NULL, '', 'ARTIST', '2023-11-29 12:22:49', '2023-11-29 12:22:49'),
(72, 'cor@niels.com', '$2a$10$L4EsgnTkT6S7VBYk05zo3.R41viyi0J7/de6pcAa49Ro4yvaIBP9S', 'Corniels', 'de Best', '0123123123', NULL, NULL, NULL, '', 'ARTIST', '2023-12-02 16:51:12', '2023-12-02 16:51:12'),
(78, 'test@test.com', '$2a$10$xm3uTExCzA8Z1LdSHJ3dMur4r3GIilpeVSxrt6OK3V.j9TRxC654i', 'test', 'test', '12312123', NULL, NULL, NULL, '', 'ARTIST', '2023-12-08 18:34:03', '2023-12-08 18:34:03'),
(79, 'admin@nu.nl', '$2a$10$369q.A7O.PY.V3elYA7e7ukk..yAGmpfZHe1CLu50Zu0yGnwe4Hsi', 'abc', 'xyz', '123457575712323', NULL, NULL, NULL, '', 'ARTIST', '2023-12-09 20:37:26', '2023-12-09 20:37:26'),
(82, 'test@test.nl', '$2a$10$y8smfMvBK6c5OxN8AlpU1eSf5ae78UkjZY.ixIoHEGOtj3IIPmf6y', 'sadsdfa', 'sdS', '123123123', NULL, NULL, NULL, '', 'ARTIST', '2023-12-10 14:17:34', '2023-12-10 14:17:34'),
(83, 'corry@niels.nl', '$2a$10$508OrC3SRzn8sCy0MOrFyuQJIGk5ZWZDtWpclQiZrLeLDmCplbqz6', 'sdfa', 'adsf', '123423234', NULL, NULL, NULL, '', 'ARTIST', '2023-12-10 14:19:26', '2023-12-10 14:19:26'),
(88, 'test@testtest.com', '$2a$10$SVeiLb3/fax6kbKwrm7Ks.tf/jXf5rbGFrxr1Ns1qnAEdfiLiluk6', 'Corry', 'de Beste', '123123322', NULL, NULL, NULL, '', 'ARTIST', '2023-12-10 18:24:12', '2023-12-10 18:24:12'),
(90, 'testje@aaaa.com', '$2a$10$tf0Lkvm6uLok4O8w/fVp2eyEBw6RZc13vQN./NWEJN/OHTAROTH4G', 'asdf', 'asfd', '1231236766', NULL, NULL, NULL, '', 'ARTIST', '2023-12-10 18:26:33', '2023-12-10 18:26:33'),
(91, 'john@cena.com', '$2a$10$WbsN/.1wVgcxtPbPqqxP9.x6rLOmlh/DTEpa.5tzYcGndc/Z42.RG', 'John', 'Cena', '1123337377', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 14:04:09', '2023-12-12 14:04:09'),
(92, 'john@bena.com', '$2a$10$LYkESlN4vCFH5v.2/Kw51u.6Kj7UnC1Lt3bab4iXDVgYMb9JMl2p2', 'John', 'Bena', '771237172377', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 14:12:06', '2023-12-12 14:12:06'),
(93, 'blabla@bla.com', '$2a$10$SCbqRf5tSnmRtisZF/CciOJuQaKUfTzN21zO/xpoz4sr/u3GuQz9e', 'bla', 'bla', '234737473757', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 15:15:02', '2023-12-12 15:15:02'),
(94, 'testff@test.com', '$2a$10$iCS8FvDonmgCjmY/gyZe3eJakgjxvGWQTzVBxf3fCLHU4e2k.O2du', 'asdfasfd', 'asdfasfd', '132123123123', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 17:37:14', '2023-12-12 17:37:14'),
(96, 'testff@test.codsfgm', '$2a$10$IdnXhzvfNflj62XrkoBNZuwRpUc/GenmMoH2AdSRSvrdAXdW4EDQ6', 'asdf', 'sadf', '213123', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 17:42:18', '2023-12-12 17:42:18'),
(97, 'test@asdfasdfas.com', '$2a$10$OrdnT63BWnhfCmmvTK9UkuAcTzE8yqq7yb6yZOv/mxabxFttGqWGq', 'adsf', 'asfd', '214356785432', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 17:44:22', '2023-12-12 17:44:22'),
(98, 'asdffdsa@asdfcom.com', '$2a$10$LLGGmn08C6JwWcO4pWQ.d.ClkZVmF0oGbdhSoMyoJjQQ2oM3HjYWu', 'dfssdfg', 'adsfadsf', '2134234234', NULL, NULL, NULL, '', 'ARTIST', '2023-12-12 18:24:20', '2023-12-12 18:24:20'),
(101, 'fjlajsdf@hfhh.com', '$2a$10$nTuKvd8ESKv0QILx25b4.ueBj8WPHVDBNPJebsll0DycKRI.QddKm', 'fjfjfj', 'hhshdfahsdf', '234626346234', NULL, NULL, NULL, '', 'ARTIST', '2023-12-13 11:30:20', '2023-12-13 11:30:20'),
(102, 'sprint@demo.com', '$2a$10$cZKxYI63ztp.7u0yRXY/D.R491R1RXY87t4nO07vRieTZkGm7eRr6', 'Sprint', 'Demo', '2364664646', NULL, NULL, NULL, '', 'ARTIST', '2023-12-13 14:03:18', '2023-12-13 14:03:18'),
(103, 'tico@test.nl', '$2a$10$l7/f86Z.SK7/CSWyL1EHnel3pggnPyIDqYiDnQlrZ8bmt7I30C/GS', 'Tico', 'Vermeer', '00612345678', NULL, NULL, NULL, '', 'ARTIST', '2023-12-15 14:19:13', '2023-12-15 14:19:13');

--
-- Dumping data for table `User_has_Label`
--

INSERT INTO `User_has_Label` (`idUser`, `idLabel`) VALUES
(70, 1),
(72, 1),
(78, 1),
(101, 1),
(66, 2),
(70, 2),
(72, 2),
(78, 2),
(102, 2),
(103, 2),
(66, 3),
(70, 3),
(72, 3),
(78, 3),
(102, 3),
(103, 3),
(90, 8),
(90, 9),
(102, 12),
(90, 13),
(101, 13),
(103, 13),
(88, 17),
(90, 17),
(98, 17),
(103, 22),
(90, 23),
(97, 23),
(101, 23),
(102, 23),
(103, 24),
(90, 25),
(101, 25);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
