-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 14, 2017 alle 16:28
-- Versione del server: 10.1.21-MariaDB
-- Versione PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `igw`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum`
--

CREATE TABLE `curriculum` (
  `Id` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `dataCaricamento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `curriculum`
--

INSERT INTO `curriculum` (`Id`, `size`, `path`, `dataCaricamento`) VALUES
(1, 12, 'ioio', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Struttura della tabella `messaggio`
--

CREATE TABLE `messaggio` (
  `Id` int(10) NOT NULL,
  `testo` varchar(1000) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT 'pubblico',
  `id_progetto` int(10) DEFAULT NULL,
  `id_sviluppatore` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Struttura della tabella `progetto`
--

CREATE TABLE `progetto` (
  `Id` int(11) NOT NULL,
  `titolo` varchar(45) NOT NULL,
  `descrizione` varchar(1000) NOT NULL,
  `id_coordinatore` int(11) NOT NULL,
  `dataInserimento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `stato` varchar(45) NOT NULL DEFAULT 'in corso'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `progetto`
--

INSERT INTO `progetto` (`Id`, `titolo`, `descrizione`, `id_coordinatore`, `dataInserimento`, `stato`) VALUES
(2, 'prova', 'hjnkm', 3, '2017-07-12 15:23:10', 'in corso');

-- --------------------------------------------------------

--
-- Struttura della tabella `skill`
--

CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `skill`
--

INSERT INTO `skill` (`id`, `nome`) VALUES
(1, 'C'),
(2, 'C#');

-- --------------------------------------------------------

--
-- Struttura della tabella `skill_sviluppatore`
--

CREATE TABLE `skill_sviluppatore` (
  `id_skill` int(10) NOT NULL,
  `id_sviluppatore` int(10) NOT NULL,
  `competenza` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `skill_task`
--

CREATE TABLE `skill_task` (
  `id_task` int(10) NOT NULL,
  `id_skill` int(10) NOT NULL,
  `competenzaMin` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `sviluppatore`
--

CREATE TABLE `sviluppatore` (
  `Id` int(10) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(60) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dataDiNascita` date DEFAULT NULL,
  `curriculum_pdf` int(10) DEFAULT NULL,
  `curriculumString` varchar(1000) DEFAULT NULL,
  `ruolo` varchar(45) NOT NULL DEFAULT 'sviluppatore'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dump dei dati per la tabella `sviluppatore`
--

INSERT INTO `sviluppatore` (`Id`, `nome`, `cognome`, `username`, `email`, `password`, `dataDiNascita`, `curriculum_pdf`, `curriculumString`, `ruolo`) VALUES
(3, 'luca', 'dorazio', 'sdf', 'sdgd', '123', '0002-02-02', 1, 'asdgf', 'sviluppatore');

-- --------------------------------------------------------

--
-- Struttura della tabella `sviluppatore_task`
--

CREATE TABLE `sviluppatore_task` (
  `id_task` int(10) NOT NULL,
  `id_sviluppatore` int(10) NOT NULL,
  `stato` int(11) DEFAULT '0',
  `voto` int(11) DEFAULT '0',
  `email` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `task`
--

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `data_inizio` date DEFAULT NULL,
  `data_fine` date DEFAULT NULL,
  `descrizione` text,
  `stato` tinyint(1) DEFAULT '1',
  `numCollaboratori` int(11) DEFAULT NULL,
  `id_progetto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `task`
--

INSERT INTO `task` (`id`, `nome`, `data_inizio`, `data_fine`, `descrizione`, `stato`, `numCollaboratori`, `id_progetto`) VALUES
(1, 'server', '0000-00-00', '0000-00-00', 'ftvyguhij', 0, 5, 2);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `curriculum`
--
ALTER TABLE `curriculum`
  ADD PRIMARY KEY (`Id`);

--
-- Indici per le tabelle `messaggio`
--
ALTER TABLE `messaggio`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_project_idx` (`id_progetto`),
  ADD KEY `fk_dev_idx` (`id_sviluppatore`);

--
-- Indici per le tabelle `progetto`
--
ALTER TABLE `progetto`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_project_developer_idx` (`id_coordinatore`);

--
-- Indici per le tabelle `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `skill_sviluppatore`
--
ALTER TABLE `skill_sviluppatore`
  ADD PRIMARY KEY (`id_skill`,`id_sviluppatore`),
  ADD KEY `fk_skill_has_developer_developer1_idx` (`id_sviluppatore`),
  ADD KEY `fk_skill_has_developer_skill1_idx` (`id_skill`);

--
-- Indici per le tabelle `skill_task`
--
ALTER TABLE `skill_task`
  ADD PRIMARY KEY (`id_task`,`id_skill`),
  ADD KEY `fk_task_has_skill_skill1_idx` (`id_skill`),
  ADD KEY `fk_task_has_skill_task1_idx` (`id_task`);

--
-- Indici per le tabelle `sviluppatore`
--
ALTER TABLE `sviluppatore`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `UC_username` (`username`),
  ADD KEY `FK_curriculumPdf` (`curriculum_pdf`);

--
-- Indici per le tabelle `sviluppatore_task`
--
ALTER TABLE `sviluppatore_task`
  ADD PRIMARY KEY (`id_task`,`id_sviluppatore`),
  ADD KEY `fk_task_has_developer_developer1_idx` (`id_sviluppatore`),
  ADD KEY `fk_task_has_developer_task1_idx` (`id_task`),
  ADD KEY `fk_sender_idx` (`email`);

--
-- Indici per le tabelle `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_task_project1_idx` (`id_progetto`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `curriculum`
--
ALTER TABLE `curriculum`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT per la tabella `messaggio`
--
ALTER TABLE `messaggio`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `progetto`
--
ALTER TABLE `progetto`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT per la tabella `skill`
--
ALTER TABLE `skill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT per la tabella `sviluppatore`
--
ALTER TABLE `sviluppatore`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT per la tabella `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `messaggio`
--
ALTER TABLE `messaggio`
  ADD CONSTRAINT `fk_dev` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_project` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `progetto`
--
ALTER TABLE `progetto`
  ADD CONSTRAINT `fk_project_developer` FOREIGN KEY (`id_coordinatore`) REFERENCES `sviluppatore` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `skill_sviluppatore`
--
ALTER TABLE `skill_sviluppatore`
  ADD CONSTRAINT `fk_skill_has_developer_developer1` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_skill_has_developer_skill1` FOREIGN KEY (`id_skill`) REFERENCES `skill` (`id`);

--
-- Limiti per la tabella `skill_task`
--
ALTER TABLE `skill_task`
  ADD CONSTRAINT `fk_task_has_skill_skill1` FOREIGN KEY (`id_skill`) REFERENCES `skill` (`id`),
  ADD CONSTRAINT `fk_task_has_skill_task1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `sviluppatore`
--
ALTER TABLE `sviluppatore`
  ADD CONSTRAINT `FK_curriculumPdf` FOREIGN KEY (`curriculum_pdf`) REFERENCES `curriculum` (`Id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Limiti per la tabella `sviluppatore_task`
--
ALTER TABLE `sviluppatore_task`
  ADD CONSTRAINT `fk_sender` FOREIGN KEY (`email`) REFERENCES `sviluppatore` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_task_has_developer_developer1` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_task_has_developer_task1` FOREIGN KEY (`id_task`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fk_task_project1` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
