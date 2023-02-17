-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 08 Lut 2023, 15:16
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `playhunt`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bettings`
--

CREATE TABLE `bettings` (
  `id` tinyint(4) NOT NULL,
  `betting_creator` tinyint(4) NOT NULL,
  `group_first_id` tinyint(4) NOT NULL,
  `group_second_id` tinyint(4) NOT NULL,
  `when_end` date NOT NULL,
  `activate` varchar(1) DEFAULT 'T',
  `first_group_votes` int(11) NOT NULL DEFAULT 0,
  `second_group_votes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `bettings`
--

INSERT INTO `bettings` (`id`, `betting_creator`, `group_first_id`, `group_second_id`, `when_end`, `activate`, `first_group_votes`, `second_group_votes`) VALUES
(1, 1, 1, 2, '2022-06-08', 'T', 68, 32),
(2, 1, 3, 4, '2022-07-21', 'T', 37, 42),
(3, 1, 5, 6, '2022-10-07', 'T', 15, 19),
(4, 1, 8, 7, '2022-06-08', 'T', 5, 13);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE `groups` (
  `id` tinyint(11) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL,
  `activate` varchar(1) DEFAULT 'T'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`id`, `name`, `activate`) VALUES
(1, 'BloodHunt', 'T'),
(2, 'KillerPonies', 'T'),
(3, 'Nightmare', 'T'),
(4, 'Ninjas', 'T'),
(5, 'Sharkshooters', 'T'),
(6, 'TinyLights', 'T'),
(7, 'BloodHunt', 'T'),
(8, 'RedHunt', 'T'),
(9, 'Rocks', 'T'),
(10, 'ShootingFanatics', 'F'),
(11, 'BlackBunnies', 'T'),
(12, 'ShadowFog', 'F'),
(13, 'Legendary', 'T');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `idRole` tinyint(4) NOT NULL,
  `name` varchar(15) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `activate` varchar(1) COLLATE utf8mb4_polish_ci DEFAULT 'T',
  `roleDate` date DEFAULT NULL,
  `finishDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`idRole`, `name`, `activate`, `roleDate`, `finishDate`) VALUES
(1, 'user', 'T', NULL, NULL),
(2, 'operator', 'T', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `idUser` tinyint(4) NOT NULL,
  `login` varchar(15) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `password` varchar(15) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `wins` tinyint(4) DEFAULT 0,
  `loses` tinyint(4) DEFAULT 0,
  `activate` varchar(1) CHARACTER SET cp1250 COLLATE cp1250_polish_ci NOT NULL DEFAULT 'T'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`idUser`, `login`, `password`, `email`, `wins`, `loses`, `activate`) VALUES
(1, 'Ania', 'hasłoani', 'Ania@mail.com', 0, 0, 'T'),
(2, 'Ula', 'hasłouli', 'Ula@mail.com', 4, 2, 'T'),
(3, 'Olek', 'hasłoolka', 'Olek@gmail.com', 0, 0, 'T'),
(4, 'Łukasz', 'hasłołukasza', 'Łukasz@gmail.com', 7, 0, 'T'),
(5, 'Marcin', 'hasłomarcina', 'Marcin@gmail.com', 0, 0, 'T');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_roles`
--

CREATE TABLE `user_roles` (
  `id` tinyint(4) NOT NULL,
  `whoEdit` varchar(15) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `idUser` tinyint(4) NOT NULL,
  `idRole` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `user_roles`
--

INSERT INTO `user_roles` (`id`, `whoEdit`, `idUser`, `idRole`) VALUES
(19, NULL, 1, 1),
(20, 'Marcin', 5, 2),
(21, NULL, 2, 1),
(22, NULL, 4, 1),
(23, NULL, 3, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `votes`
--

CREATE TABLE `votes` (
  `id` tinyint(4) NOT NULL,
  `id_user` tinyint(4) NOT NULL,
  `id_group` tinyint(4) NOT NULL,
  `id_betting` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `votes`
--

INSERT INTO `votes` (`id`, `id_user`, `id_group`, `id_betting`) VALUES
(11, 1, 1, 1),
(24, 1, 1, 1),
(25, 1, 1, 1),
(26, 1, 1, 1),
(27, 1, 1, 1),
(28, 1, 1, 1),
(29, 1, 1, 1),
(30, 1, 1, 1),
(31, 1, 1, 1),
(38, 1, 1, 1),
(39, 1, 1, 1),
(40, 1, 1, 1),
(21, 1, 4, 2),
(35, 1, 4, 2),
(14, 1, 5, 3),
(32, 1, 5, 3),
(33, 1, 6, 3),
(34, 1, 6, 3),
(36, 1, 6, 3),
(41, 1, 6, 3),
(15, 1, 7, 4),
(42, 1, 7, 4),
(2, 2, 1, 2),
(4, 2, 6, 3),
(37, 5, 1, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `bettings`
--
ALTER TABLE `bettings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `betting_creator` (`betting_creator`),
  ADD KEY `group_first_id` (`group_first_id`),
  ADD KEY `group_second_id` (`group_second_id`);

--
-- Indeksy dla tabeli `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idRole`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`);

--
-- Indeksy dla tabeli `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idRole` (`idRole`);

--
-- Indeksy dla tabeli `votes`
--
ALTER TABLE `votes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`,`id_group`,`id_betting`),
  ADD KEY `id_betting` (`id_betting`),
  ADD KEY `id_group` (`id_group`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `bettings`
--
ALTER TABLE `bettings`
  MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `groups`
--
ALTER TABLE `groups`
  MODIFY `id` tinyint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `idRole` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `idUser` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT dla tabeli `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT dla tabeli `votes`
--
ALTER TABLE `votes`
  MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `bettings`
--
ALTER TABLE `bettings`
  ADD CONSTRAINT `bettings_ibfk_1` FOREIGN KEY (`betting_creator`) REFERENCES `users` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `bettings_ibfk_2` FOREIGN KEY (`group_first_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `bettings_ibfk_3` FOREIGN KEY (`group_second_id`) REFERENCES `groups` (`id`);

--
-- Ograniczenia dla tabeli `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`idRole`) REFERENCES `roles` (`idRole`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `votes`
--
ALTER TABLE `votes`
  ADD CONSTRAINT `votes_ibfk_1` FOREIGN KEY (`id_betting`) REFERENCES `bettings` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `votes_ibfk_2` FOREIGN KEY (`id_group`) REFERENCES `groups` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `votes_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `users` (`idUser`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
