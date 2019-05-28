-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 28. Mai 2019 um 06:13
-- Server-Version: 10.0.38-MariaDB-cll-lve
-- PHP-Version: 7.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Tabellenstruktur für Tabelle `sp_fam`
--

CREATE TABLE IF NOT EXISTS `sp_fam` (
  `spFamID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` varchar(250) NOT NULL,
  `spInf2` varchar(250) NOT NULL,
  `spInf3` varchar(50) NOT NULL,
  `spInf4` varchar(50) NOT NULL,
  `spInf5` varchar(250) NOT NULL,
  `spInf6` int(11) NOT NULL,
  `spInf7` varchar(50) NOT NULL,
  PRIMARY KEY (`spFamID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_info`
--

CREATE TABLE IF NOT EXISTS `sp_info` (
  `keyInf` varchar(250) NOT NULL,
  `inf` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_members`
--

CREATE TABLE IF NOT EXISTS `sp_members` (
  `spMemberID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` varchar(50) NOT NULL,
  `spInf2` varchar(50) NOT NULL,
  `spInf3` varchar(50) NOT NULL,
  `spInf4` int(11) NOT NULL,
  `spInf5` int(11) NOT NULL,
  `spInf6` int(11) NOT NULL,
  `spInf7` int(11) NOT NULL,
  `spInf8` int(11) NOT NULL,
  `spInf9` int(11) NOT NULL,
  `spInf10` int(11) NOT NULL,
  `spInf11` int(11) NOT NULL,
  `spInf12` int(11) NOT NULL,
  `spInf13` int(11) NOT NULL,
  `spInf14` int(11) NOT NULL,
  `spInf15` int(11) NOT NULL,
  `spInf16` int(11) NOT NULL,
  `spInf17` int(11) NOT NULL,
  `spInf18` int(11) NOT NULL,
  `spInf19` int(11) NOT NULL,
  `spInf20` int(11) NOT NULL,
  `spInf21` int(11) NOT NULL,
  `spInf22` int(11) NOT NULL,
  `spInf23` int(11) NOT NULL,
  `spInf24` int(11) NOT NULL,
  `spInf25` int(11) NOT NULL,
  `spInf26` int(11) NOT NULL,
  `spInf27` int(11) NOT NULL,
  `spInf28` int(11) NOT NULL,
  `spInf29` int(11) NOT NULL,
  `spInf30` int(11) NOT NULL,
  `spInf31` int(11) NOT NULL,
  `spInf32` int(11) NOT NULL,
  `spInf33` int(11) NOT NULL,
  `spInf34` int(11) NOT NULL,
  `spInf35` int(11) NOT NULL,
  `spInf36` int(11) NOT NULL,
  `spInf37` int(11) NOT NULL,
  `spInf38` int(11) NOT NULL,
  `spInf39` varchar(50) NOT NULL,
  `spInf40` varchar(50) NOT NULL,
  `spInf41` varchar(50) NOT NULL,
  `spInf42` int(11) NOT NULL,
  `spInf43` int(11) NOT NULL,
  `spInf44` int(11) NOT NULL,
  `spInf45` int(11) NOT NULL,
  `spInf46` int(11) NOT NULL,
  PRIMARY KEY (`spMemberID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_sergr`
--

CREATE TABLE IF NOT EXISTS `sp_sergr` (
  `spSerGrID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` varchar(250) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  PRIMARY KEY (`spSerGrID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_users`
--

CREATE TABLE IF NOT EXISTS `sp_users` (
  `spUserID` int(11) NOT NULL AUTO_INCREMENT,
  `spUserSU` int(11) NOT NULL,
  `spUserName` varchar(50) NOT NULL,
  `spUserPassword` varchar(50) NOT NULL,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  `spInf4` int(11) NOT NULL,
  PRIMARY KEY (`spUserID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week`
--

CREATE TABLE IF NOT EXISTS `sp_week` (
  `spWeekID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  `spInf4` int(11) NOT NULL,
  `spInf5` varchar(250) NOT NULL,
  `spInf6` varchar(250) NOT NULL,
  `spInf7` varchar(250) NOT NULL,
  `spInf8` varchar(250) NOT NULL,
  `spInf9` varchar(250) NOT NULL,
  `spInf10` varchar(250) NOT NULL,
  `spInf11` int(11) NOT NULL,
  `spInf12` varchar(250) NOT NULL,
  `spInf13` varchar(250) NOT NULL,
  `spInf14` int(11) NOT NULL,
  `spInf15` varchar(250) NOT NULL,
  `spInf16` varchar(250) NOT NULL,
  `spInf17` varchar(250) NOT NULL,
  `spInf18` int(11) NOT NULL,
  `spInf19` varchar(250) NOT NULL,
  `spInf20` varchar(250) NOT NULL,
  `spInf21` varchar(250) NOT NULL,
  `spInf22` varchar(250) NOT NULL,
  `spInf23` int(11) NOT NULL,
  `spInf24` varchar(250) NOT NULL,
  `spInf25` varchar(250) NOT NULL,
  `spInf26` varchar(250) NOT NULL,
  `spInf27` int(11) NOT NULL,
  PRIMARY KEY (`spWeekID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week_cr`
--

CREATE TABLE IF NOT EXISTS `sp_week_cr` (
  `spWeekCrID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` varchar(250) NOT NULL,
  `spInf4` text NOT NULL,
  `spInf5` text NOT NULL,
  `spInf6` int(11) NOT NULL,
  PRIMARY KEY (`spWeekCrID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week_min`
--

CREATE TABLE IF NOT EXISTS `sp_week_min` (
  `spWeekMinID` int(11) NOT NULL AUTO_INCREMENT,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  `spInf4` varchar(250) NOT NULL,
  `spInf5` text NOT NULL,
  `spInf6` text NOT NULL,
  `spInf7` int(11) NOT NULL,
  `spInf8` int(11) NOT NULL,
  `spInf9` int(11) NOT NULL,
  `spInf10` int(11) NOT NULL,
  PRIMARY KEY (`spWeekMinID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
