-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 24, 2020 alle 22:29
-- Versione del server: 10.1.32-MariaDB
-- Versione PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `5ci_davidemauri_gestioneimpegni`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `impegni`
--

CREATE TABLE `impegni` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(32) NOT NULL,
  `Data` date NOT NULL,
  `Durata` int(3) NOT NULL,
  `Importanza` int(2) NOT NULL,
  `Materia` varchar(32) NOT NULL,
  `Tipologia` varchar(32) NOT NULL,
  `IDUtente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `impegni`
--

INSERT INTO `impegni` (`ID`, `Nome`, `Data`, `Durata`, `Importanza`, `Materia`, `Tipologia`, `IDUtente`) VALUES
(1, 'Impegno1', '2020-05-06', 20, 1, 'Materia1', 'Tiplologia1', 1),
(2, 'Impegno2', '2017-04-06', 12, 2, 'Materia2', 'Tipologia2', 2),
(3, 'Impegno1', '2020-05-19', 74, 41, 'Materia3', 'Tipologia3', 3),
(4, 'Impegno4', '2020-05-03', 11, 12, 'Materia4', 'Tipologia4', 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `ID` int(11) NOT NULL,
  `Username` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`ID`, `Username`, `Password`, `Admin`) VALUES
(1, 'Admin', 'admin', 1),
(2, 'UtenteNormale', 'password', 0),
(3, 'Gigio', 'Gelato1', 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `impegni`
--
ALTER TABLE `impegni`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `impegni`
--
ALTER TABLE `impegni`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
