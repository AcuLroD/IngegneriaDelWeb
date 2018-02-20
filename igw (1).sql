-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 20, 2018 alle 13:49
-- Versione del server: 10.1.28-MariaDB
-- Versione PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
-- Struttura della tabella `file`
--

CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `localfile` varchar(255) NOT NULL,
  `caricamento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `size` bigint(100) NOT NULL,
  `digest` varchar(64) NOT NULL,
  `type` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `messaggio`
--

CREATE TABLE `messaggio` (
  `id` int(11) NOT NULL,
  `privato` int(1) DEFAULT '0',
  `testo` text,
  `tipo` varchar(128) DEFAULT NULL,
  `dataInserimento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_progetto` int(11) DEFAULT NULL,
  `id_sviluppatore` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `messaggio`
--

INSERT INTO `messaggio` (`id`, `privato`, `testo`, `tipo`, `dataInserimento`, `id_progetto`, `id_sviluppatore`) VALUES
(1, 0, ' ciao...questa è una prova', 'messaggio di prova', '2018-02-12 12:03:06', 1, 1),
(2, 0, ' ciao...questa è una prova', 'messaggio di prova', '2018-02-12 12:03:06', 1, 1),
(3, 0, ' ciao...questa è una prova', 'messaggio di prova', '2018-02-12 12:03:06', 1, 1),
(4, 0, ' ciao...questa è una prova', 'messaggio di prova', '2018-02-12 12:03:06', 1, 1),
(5, 0, ' ciao...questa è una prova', 'messaggio di prova', '2018-02-12 12:03:06', 1, 1),
(6, 0, ' sdfcgvbhjn', 'kkk', '2018-02-12 12:03:06', 5, 3),
(7, 0, ' progetto scadente', 'provaprovaprova', '2018-02-12 12:03:06', 6, 3),
(8, 1, ' www', 'provaprivato', '2018-02-12 12:03:06', 5, 3),
(9, 0, 'bu ', 'bla ', '2018-02-12 12:03:06', 5, 3),
(10, 0, ' ertfijk,o', 'trfhnuj,', '2018-02-12 15:40:10', 5, 3),
(11, 0, ' prova messaggio pubblico', 'prova', '2018-02-18 16:34:32', 2, 14),
(12, 1, ' prova messaggio privato', 'prova messaggio privato', '2018-02-18 16:35:00', 2, 14);

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipazione`
--

CREATE TABLE `partecipazione` (
  `id` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `id_coordinatore` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `stato` int(11) NOT NULL DEFAULT '0',
  `tipo` varchar(50) NOT NULL,
  `letto` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `partecipazione`
--

INSERT INTO `partecipazione` (`id`, `id_task`, `id_coordinatore`, `sender`, `data`, `stato`, `tipo`, `letto`) VALUES
(3, 1, 1, 14, NULL, 0, 'richiesta', 1),
(4, 3, 1, 2, NULL, 0, 'richiesta', 0),
(5, 2, 1, 2, NULL, 0, 'richiesta', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `progetto`
--

CREATE TABLE `progetto` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `descrizione` text NOT NULL,
  `id_coordinatore` int(11) NOT NULL,
  `dataCreazione` date NOT NULL,
  `stato` varchar(64) NOT NULL DEFAULT 'in corso'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `progetto`
--

INSERT INTO `progetto` (`id`, `nome`, `descrizione`, `id_coordinatore`, `dataCreazione`, `stato`) VALUES
(1, 'prog1', ' sdfghjk', 1, '2018-02-15', 'in corso'),
(2, 'sdfghjk', 'dfghjkl ', 1, '2018-02-15', 'in corso'),
(3, 'zxcvb', ' cfgh', 14, '2018-02-18', 'in corso'),
(4, 'sdfghj', 'ertyuio ', 14, '2018-02-18', 'in corso'),
(5, 'sdfgh', 'dfghjk ', 1, '2018-02-19', 'in corso'),
(6, 'progetto prova', ' descrizione progetto prova', 1, '2018-02-19', 'in corso'),
(7, 'sdfgh', 'dfghjkl ', 14, '2018-02-20', 'in corso');

-- --------------------------------------------------------

--
-- Struttura della tabella `skill`
--

CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `nome` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `skill`
--

INSERT INTO `skill` (`id`, `nome`) VALUES
(1, 'Css3'),
(2, 'Php7'),
(3, 'Html5'),
(4, 'Java'),
(5, 'Javascript'),
(6, 'C'),
(7, 'C++'),
(8, 'C#'),
(9, 'Python'),
(10, 'angularjs'),
(11, 'typescript');

-- --------------------------------------------------------

--
-- Struttura della tabella `skillreltask`
--

CREATE TABLE `skillreltask` (
  `id` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `id_skill` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `skillreltask`
--

INSERT INTO `skillreltask` (`id`, `id_task`, `id_skill`) VALUES
(1, 2, 1),
(2, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `skillsviluppatore`
--

CREATE TABLE `skillsviluppatore` (
  `id_skill` int(11) NOT NULL,
  `id_sviluppatore` int(11) NOT NULL,
  `livello` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `skillsviluppatore`
--

INSERT INTO `skillsviluppatore` (`id_skill`, `id_sviluppatore`, `livello`) VALUES
(1, 2, 6),
(1, 1, 6),
(3, 1, 8),
(2, 1, 7),
(1, 3, 5),
(2, 3, 6),
(4, 3, 6),
(5, 3, 9),
(10, 3, 10),
(4, 12, 7),
(3, 14, 10),
(1, 14, 10);

-- --------------------------------------------------------

--
-- Struttura della tabella `skilltask`
--

CREATE TABLE `skilltask` (
  `id` int(11) NOT NULL,
  `id_skill` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `competenzaMinima` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `skilltask`
--

INSERT INTO `skilltask` (`id`, `id_skill`, `id_task`, `competenzaMinima`) VALUES
(1, 1, 1, 7),
(2, 2, 2, 5),
(3, 3, 2, 8),
(4, 1, 3, 3),
(5, 3, 5, 2),
(6, 1, 6, 3),
(7, 2, 7, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `sviluppatore`
--

CREATE TABLE `sviluppatore` (
  `id` int(11) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `biografia` varchar(2000) DEFAULT NULL,
  `id_curriculum` int(11) DEFAULT NULL,
  `id_foto` int(11) DEFAULT NULL,
  `dataDiNascita` date DEFAULT NULL,
  `ruolo` varchar(32) NOT NULL DEFAULT 'sviluppatore'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sviluppatore`
--

INSERT INTO `sviluppatore` (`id`, `email`, `password`, `nome`, `cognome`, `biografia`, `id_curriculum`, `id_foto`, `dataDiNascita`, `ruolo`) VALUES
(1, 'emancini@libero.it', '1234', 'eugenio', 'mancini', 'Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione. È sopravvissuto non solo a più di cinque secoli, ma anche al passaggio alla videoimpaginazione, pervenendoci sostanzialmente inalterato. Fu reso popolare, negli anni ?60, con la diffusione dei fogli di caratteri trasferibili ?Letraset?, che contenevano passaggi del Lorem Ipsum, e più recentemente da software di impaginazione come Aldus PageMaker, che includeva versioni del Lorem Ipsum.  ', NULL, NULL, NULL, 'sviluppatore'),
(2, 'virtualmoney17@gmail.com', '1234', 'ciao', 'mancini', '', NULL, NULL, NULL, 'sviluppatore'),
(3, 'lucador@live.it', '123', 'luca', 'd\' orazio', 'Studente triennale in informatica presso l\'universita di l\'Aquila. ', NULL, NULL, '1994-02-08', 'admin'),
(4, 'scor@live.it', '123', 'stefano', 'corsetti', '', NULL, NULL, NULL, 'sviluppatore'),
(12, 'pippo@pippo.live', '123', 'pippo1', 'pippo', '', NULL, NULL, '2011-12-21', 'sviluppatore'),
(13, 'llll@lll', 'llll', 'lu', 'okk', '', NULL, NULL, '2008-03-13', 'sviluppatore'),
(14, 'eugenio@mancini.it', '1234', 'eugenio', 'mancini', '', NULL, NULL, '1992-12-18', 'sviluppatore');

-- --------------------------------------------------------

--
-- Struttura della tabella `sviluppatore_task`
--

CREATE TABLE `sviluppatore_task` (
  `id_sviluppatore_task` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `id_sviluppatore` int(11) NOT NULL,
  `voto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sviluppatore_task`
--

INSERT INTO `sviluppatore_task` (`id_sviluppatore_task`, `id_task`, `id_sviluppatore`, `voto`) VALUES
(1, 1, 14, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `task`
--

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `descrizione` text NOT NULL,
  `stato` tinyint(1) NOT NULL DEFAULT '1',
  `dataInizio` date DEFAULT NULL,
  `dataFine` date DEFAULT NULL,
  `numCollaboratori` int(11) NOT NULL DEFAULT '1',
  `id_progetto` int(11) NOT NULL,
  `nome_task` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `task`
--

INSERT INTO `task` (`id`, `id_task`, `descrizione`, `stato`, `dataInizio`, `dataFine`, `numCollaboratori`, `id_progetto`, `nome_task`) VALUES
(1, 2, ' sdfghj', 0, '2019-02-02', '2020-02-02', 1, 1, 'nome task'),
(2, 1, ' sdfghj', 0, '2020-02-02', '2022-02-02', 4, 1, 'nome'),
(3, 2, ' cvbnm', 0, '2020-02-02', '2022-04-03', 3, 2, ''),
(4, 3, ' sdfgh', 0, '2017-12-02', '2018-12-31', 7, 3, ''),
(5, 1, ' fghjk', 0, '2019-02-02', '2021-03-02', 5, 4, ''),
(6, 2, ' dfghjk', 0, '2018-01-01', '2019-02-02', 4, 6, 'grafica task'),
(7, 1, ' dfghjk', 0, '2019-02-02', '2019-03-02', 4, 7, 'nkjbjscbk');

-- --------------------------------------------------------

--
-- Struttura della tabella `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tasks`
--

INSERT INTO `tasks` (`id`, `nome`) VALUES
(2, 'Grafica'),
(4, 'prova'),
(3, 'Sicurezza'),
(1, 'Sviluppo Web');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `messaggio`
--
ALTER TABLE `messaggio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_project_idx` (`id_progetto`),
  ADD KEY `fk_dev_idx` (`id_sviluppatore`);

--
-- Indici per le tabelle `partecipazione`
--
ALTER TABLE `partecipazione`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `progetto`
--
ALTER TABLE `progetto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Sviluppatore` (`id_coordinatore`);

--
-- Indici per le tabelle `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `skillreltask`
--
ALTER TABLE `skillreltask`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `skillsviluppatore`
--
ALTER TABLE `skillsviluppatore`
  ADD KEY `id_sviluppatore` (`id_sviluppatore`);

--
-- Indici per le tabelle `skilltask`
--
ALTER TABLE `skilltask`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_skill` (`id_skill`),
  ADD KEY `id_task` (`id_task`);

--
-- Indici per le tabelle `sviluppatore`
--
ALTER TABLE `sviluppatore`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indici per le tabelle `sviluppatore_task`
--
ALTER TABLE `sviluppatore_task`
  ADD PRIMARY KEY (`id_sviluppatore_task`);

--
-- Indici per le tabelle `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `file`
--
ALTER TABLE `file`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `messaggio`
--
ALTER TABLE `messaggio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `partecipazione`
--
ALTER TABLE `partecipazione`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `progetto`
--
ALTER TABLE `progetto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `skill`
--
ALTER TABLE `skill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `skillreltask`
--
ALTER TABLE `skillreltask`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `skilltask`
--
ALTER TABLE `skilltask`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `sviluppatore`
--
ALTER TABLE `sviluppatore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `sviluppatore_task`
--
ALTER TABLE `sviluppatore_task`
  MODIFY `id_sviluppatore_task` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `messaggio`
--
ALTER TABLE `messaggio`
  ADD CONSTRAINT `fk_dev` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_project` FOREIGN KEY (`id_progetto`) REFERENCES `progetto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `progetto`
--
ALTER TABLE `progetto`
  ADD CONSTRAINT `progetto_ibfk_1` FOREIGN KEY (`id_coordinatore`) REFERENCES `sviluppatore` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `skillsviluppatore`
--
ALTER TABLE `skillsviluppatore`
  ADD CONSTRAINT `skillsviluppatore_ibfk_1` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`id`),
  ADD CONSTRAINT `skillsviluppatore_ibfk_2` FOREIGN KEY (`id_sviluppatore`) REFERENCES `sviluppatore` (`id`);

--
-- Limiti per la tabella `skilltask`
--
ALTER TABLE `skilltask`
  ADD CONSTRAINT `skilltask_ibfk_1` FOREIGN KEY (`id_skill`) REFERENCES `skill` (`id`),
  ADD CONSTRAINT `skilltask_ibfk_2` FOREIGN KEY (`id_task`) REFERENCES `task` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
