-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Erstellungszeit: 23. Jan 2020 um 10:26
-- Server-Version: 10.0.38-MariaDB-cll-lve
-- PHP-Version: 7.2.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_fam`
--

CREATE TABLE `sp_fam` (
  `spFamID` int(11) NOT NULL,
  `spInf1` varchar(250) NOT NULL,
  `spInf2` varchar(250) NOT NULL,
  `spInf3` varchar(50) NOT NULL,
  `spInf4` varchar(50) NOT NULL,
  `spInf5` varchar(250) NOT NULL,
  `spInf6` int(11) NOT NULL,
  `spInf7` varchar(50) NOT NULL,
  `spInf8` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_info`
--

CREATE TABLE `sp_info` (
  `keyInf` varchar(250) NOT NULL,
  `inf` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_members`
--

CREATE TABLE `sp_members` (
  `spMemberID` int(11) NOT NULL,
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
  `spInf47` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_sergr`
--

CREATE TABLE `sp_sergr` (
  `spSerGrID` int(11) NOT NULL,
  `spInf1` varchar(250) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_users`
--

CREATE TABLE `sp_users` (
  `spUserID` int(11) NOT NULL,
  `spUserSU` int(11) NOT NULL,
  `spUserName` varchar(50) NOT NULL,
  `spUserPassword` varchar(50) NOT NULL,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  `spInf4` int(11) NOT NULL,
  `spInf5` int(11) NOT NULL,
  `spInf6` int(11) NOT NULL,
  `spInf7` int(11) NOT NULL,
  `spInf8` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week`
--

CREATE TABLE `sp_week` (
  `spWeekID` int(11) NOT NULL,
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
  `spInf28` int(11) NOT NULL,
  `spInf29` int(11) NOT NULL,
  `spInf30` int(11) NOT NULL,
  `spInf31` varchar(250) NOT NULL,
  `spInf32` varchar(250) NOT NULL,
  `spInf33` varchar(250) NOT NULL,
  `spInf34` varchar(250) NOT NULL,
  `spInf35` varchar(250) NOT NULL,
  `spInf36` varchar(250) NOT NULL,
  `spInf37` int(11) NOT NULL,
  `spInf38` int(11) NOT NULL,
  `spInf39` varchar(250) NOT NULL,
  `spInf40` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week_cr`
--

CREATE TABLE `sp_week_cr` (
  `spWeekCrID` int(11) NOT NULL,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` varchar(250) NOT NULL,
  `spInf4` text NOT NULL,
  `spInf5` text NOT NULL,
  `spInf6` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week_min`
--

CREATE TABLE `sp_week_min` (
  `spWeekMinID` int(11) NOT NULL,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` int(11) NOT NULL,
  `spInf4` varchar(250) NOT NULL,
  `spInf5` text NOT NULL,
  `spInf6` text NOT NULL,
  `spInf7` int(11) NOT NULL,
  `spInf8` int(11) NOT NULL,
  `spInf9` int(11) NOT NULL,
  `spInf10` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sp_week_ov`
--

CREATE TABLE `sp_week_ov` (
  `spWeekOvID` int(11) NOT NULL,
  `spInf1` int(11) NOT NULL,
  `spInf2` int(11) NOT NULL,
  `spInf3` varchar(255) NOT NULL,
  `spInf4` varchar(255) NOT NULL,
  `spInf5` varchar(255) NOT NULL,
  `spInf6` varchar(255) NOT NULL,
  `spInf7` varchar(255) NOT NULL,
  `spInf8` varchar(255) NOT NULL,
  `spInf9` varchar(255) NOT NULL,
  `spInf10` varchar(255) NOT NULL,
  `spInf11` varchar(255) NOT NULL,
  `spInf12` varchar(255) NOT NULL,
  `spInf13` varchar(255) NOT NULL,
  `spInf14` varchar(255) NOT NULL,
  `spInf15` varchar(255) NOT NULL,
  `spInf16` varchar(50) NOT NULL,
  `spInf17` varchar(50) NOT NULL,
  `spInf18` varchar(50) NOT NULL,
  `spInf19` varchar(50) NOT NULL,
  `spInf20` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `sp_fam`
--
ALTER TABLE `sp_fam`
  ADD PRIMARY KEY (`spFamID`);

--
-- Indizes für die Tabelle `sp_members`
--
ALTER TABLE `sp_members`
  ADD PRIMARY KEY (`spMemberID`);

--
-- Indizes für die Tabelle `sp_sergr`
--
ALTER TABLE `sp_sergr`
  ADD PRIMARY KEY (`spSerGrID`);

--
-- Indizes für die Tabelle `sp_users`
--
ALTER TABLE `sp_users`
  ADD PRIMARY KEY (`spUserID`);

--
-- Indizes für die Tabelle `sp_week`
--
ALTER TABLE `sp_week`
  ADD PRIMARY KEY (`spWeekID`);

--
-- Indizes für die Tabelle `sp_week_cr`
--
ALTER TABLE `sp_week_cr`
  ADD PRIMARY KEY (`spWeekCrID`);

--
-- Indizes für die Tabelle `sp_week_min`
--
ALTER TABLE `sp_week_min`
  ADD PRIMARY KEY (`spWeekMinID`);

--
-- Indizes für die Tabelle `sp_week_ov`
--
ALTER TABLE `sp_week_ov`
  ADD PRIMARY KEY (`spWeekOvID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `sp_fam`
--
ALTER TABLE `sp_fam`
  MODIFY `spFamID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_members`
--
ALTER TABLE `sp_members`
  MODIFY `spMemberID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_sergr`
--
ALTER TABLE `sp_sergr`
  MODIFY `spSerGrID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_users`
--
ALTER TABLE `sp_users`
  MODIFY `spUserID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_week`
--
ALTER TABLE `sp_week`
  MODIFY `spWeekID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_week_cr`
--
ALTER TABLE `sp_week_cr`
  MODIFY `spWeekCrID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_week_min`
--
ALTER TABLE `sp_week_min`
  MODIFY `spWeekMinID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `sp_week_ov`
--
ALTER TABLE `sp_week_ov`
  MODIFY `spWeekOvID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
