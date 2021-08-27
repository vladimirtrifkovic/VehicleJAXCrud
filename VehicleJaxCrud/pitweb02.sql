-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2021 at 11:14 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pitweb02`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategorije`
--

CREATE TABLE `kategorije` (
  `kategorija` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `opis` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trajanje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `kategorije`
--

INSERT INTO `kategorije` (`kategorija`, `opis`, `trajanje`) VALUES
('A', 'motocikl', 2),
('B', 'motorno vozilo', 10),
('C', 'kamion', 5);

-- --------------------------------------------------------

--
-- Table structure for table `proizvodjaci`
--

CREATE TABLE `proizvodjaci` (
  `proizvodjac` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `zemljaPorekla` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `proizvodjaci`
--

INSERT INTO `proizvodjaci` (`proizvodjac`, `zemljaPorekla`) VALUES
('Audi', 'Nemacka'),
('BMW', 'Nemacka'),
('Ferari', 'Italija'),
('Fiat', 'Italija'),
('Mercedes', 'Nemacka'),
('Nisan', 'Japan'),
('Skoda', 'Ceska'),
('Toyota', 'Japan');

-- --------------------------------------------------------

--
-- Table structure for table `vozaci`
--

CREATE TABLE `vozaci` (
  `id_vozac` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `godiste` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vozaci`
--

INSERT INTO `vozaci` (`id_vozac`, `ime`, `prezime`, `godiste`) VALUES
(1, 'Petar', 'Petrovic', 1983),
(2, 'Ivan', 'Ivanovic', 1995);

-- --------------------------------------------------------

--
-- Table structure for table `vozac_vozilo`
--

CREATE TABLE `vozac_vozilo` (
  `id_vozac` int(11) NOT NULL,
  `id_vozila` int(11) NOT NULL,
  `vremeDodele` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vozac_vozilo`
--

INSERT INTO `vozac_vozilo` (`id_vozac`, `id_vozila`, `vremeDodele`) VALUES
(1, 3, '2018-06-23 10:51:13'),
(1, 4, '2018-06-23 10:50:40'),
(2, 3, '2018-06-23 10:53:08');

-- --------------------------------------------------------

--
-- Table structure for table `vozila`
--

CREATE TABLE `vozila` (
  `id_vozila` int(11) NOT NULL,
  `proizvodjac` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kategorija` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `godiste` int(11) NOT NULL,
  `kubikaza` int(11) NOT NULL,
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vozila`
--

INSERT INTO `vozila` (`id_vozila`, `proizvodjac`, `kategorija`, `godiste`, `kubikaza`, `cena`) VALUES
(3, 'Toyota', 'C', 2015, 1400, 5500),
(4, 'Mercedes', 'C', 2014, 8000, 85000),
(5, 'Skoda', 'B', 2015, 1900, 8000),
(6, 'Ferari', 'C', 2015, 2000, 8600),
(9, 'Audi', 'B', 2015, 1900, 6000),
(10, 'Nisan', 'C', 2001, 1600, 5555),
(11, 'Skoda', 'A', 2005, 1700, 3000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategorije`
--
ALTER TABLE `kategorije`
  ADD PRIMARY KEY (`kategorija`);

--
-- Indexes for table `proizvodjaci`
--
ALTER TABLE `proizvodjaci`
  ADD PRIMARY KEY (`proizvodjac`);

--
-- Indexes for table `vozaci`
--
ALTER TABLE `vozaci`
  ADD PRIMARY KEY (`id_vozac`);

--
-- Indexes for table `vozac_vozilo`
--
ALTER TABLE `vozac_vozilo`
  ADD PRIMARY KEY (`id_vozac`,`id_vozila`),
  ADD KEY `id_vozac` (`id_vozac`),
  ADD KEY `id_vozila` (`id_vozila`);

--
-- Indexes for table `vozila`
--
ALTER TABLE `vozila`
  ADD PRIMARY KEY (`id_vozila`),
  ADD KEY `proizvodjac` (`proizvodjac`),
  ADD KEY `kategorija` (`kategorija`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `vozaci`
--
ALTER TABLE `vozaci`
  MODIFY `id_vozac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `vozila`
--
ALTER TABLE `vozila`
  MODIFY `id_vozila` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `vozac_vozilo`
--
ALTER TABLE `vozac_vozilo`
  ADD CONSTRAINT `vozac_vozilo_ibfk_1` FOREIGN KEY (`id_vozila`) REFERENCES `vozila` (`id_vozila`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vozac_vozilo_ibfk_2` FOREIGN KEY (`id_vozac`) REFERENCES `vozaci` (`id_vozac`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vozila`
--
ALTER TABLE `vozila`
  ADD CONSTRAINT `vozila_ibfk_1` FOREIGN KEY (`proizvodjac`) REFERENCES `proizvodjaci` (`proizvodjac`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vozila_ibfk_2` FOREIGN KEY (`kategorija`) REFERENCES `kategorije` (`kategorija`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
