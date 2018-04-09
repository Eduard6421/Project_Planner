-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2018 at 11:34 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectplanner`
--

-- --------------------------------------------------------

--
-- Table structure for table `milestones`
--

CREATE TABLE `milestones` (
  `Id` int(11) NOT NULL,
  `ProjectId` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `milestones`
--

INSERT INTO `milestones` (`Id`, `ProjectId`, `Title`, `StartDate`, `EndDate`, `Description`) VALUES
(1, 1, 'M1P1', '2018-01-15 00:00:00', '2018-02-14 00:00:00', 'Descriere milestone 1 de la proiectul 1'),
(2, 1, 'M2P1', '2017-09-12 00:00:00', '2017-11-15 00:00:00', 'Descriere milestone 2 de la proiectul 1'),
(3, 2, 'M1P2', '2018-04-28 00:00:00', '2018-06-21 00:00:00', 'Descriere milestone 1 de la proiectul 2');

-- --------------------------------------------------------

--
-- Table structure for table `priorities`
--

CREATE TABLE `priorities` (
  `Id` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `priorities`
--

INSERT INTO `priorities` (`Id`, `Title`) VALUES
(1, 'High'),
(2, 'Ultra'),
(3, 'Medium'),
(4, 'Low');

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `Id` int(11) NOT NULL,
  `ManagerId` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `ClientName` varchar(255) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Budget` decimal(10,3) NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`Id`, `ManagerId`, `Title`, `ClientName`, `StartDate`, `EndDate`, `Budget`, `Description`) VALUES
(1, 2, 'Proiectul1', 'Client Proiectul1', '2017-11-24 00:00:00', '2018-01-17 00:00:00', '56433.234', 'Proiectul 1 este despre prima chestie care trebuie facuta'),
(2, 2, 'Proiectul2', 'Client Proiectul2', '2018-04-02 00:00:00', '2018-07-20 00:00:00', '10000.000', 'Proiectul 2, in principal, semnifica a doua chestie care trebuie facuta');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `Id` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`Id`, `Title`) VALUES
(1, 'Admin'),
(2, 'Manager'),
(3, 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `Id` int(11) NOT NULL,
  `MilestoneId` int(11) NOT NULL,
  `AssignedToId` int(11) NOT NULL,
  `PriorityId` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`Id`, `MilestoneId`, `AssignedToId`, `PriorityId`, `Title`, `StartDate`, `EndDate`, `Description`) VALUES
(1, 1, 3, 1, 'T1M1P1', '2018-04-10 00:00:00', '2018-06-14 00:00:00', 'Task 1, Milestone 1, Proiect 1'),
(2, 1, 2, 3, 'T2M1P1', '2018-02-12 00:00:00', '2018-08-25 00:00:00', 'Task 2, Milestone 1, Proiect 1'),
(3, 2, 4, 2, 'T1M2P1', '2018-04-10 00:00:00', '2018-04-26 00:00:00', 'Task 1, Mileston 2, Proiect 1'),
(4, 3, 2, 4, 'T1M1P2', '2018-01-15 00:00:00', '2018-04-24 00:00:00', 'Task 1, Milestone 1, Proiect 2');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `RoleId`, `Username`, `Password`, `FirstName`, `LastName`) VALUES
(1, 1, 'Admin', 'Admin', 'Administrator', 'Administrator'),
(2, 2, 'Manager1', 'Manager1', 'Manager1', 'Manager1'),
(3, 3, 'Angajat1', 'Angajat1', 'Angajat1', 'Angajat1'),
(4, 3, 'Angajat2', 'Angajat2', 'Angajat2', 'Angajat2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `milestones`
--
ALTER TABLE `milestones`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ProjectId` (`ProjectId`) USING BTREE;

--
-- Indexes for table `priorities`
--
ALTER TABLE `priorities`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ManagerId` (`ManagerId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PriorityId` (`PriorityId`) USING BTREE,
  ADD KEY `AssignedToId` (`AssignedToId`) USING BTREE,
  ADD KEY `MilestoneId` (`MilestoneId`) USING BTREE;

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `RoleId` (`RoleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `milestones`
--
ALTER TABLE `milestones`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `priorities`
--
ALTER TABLE `priorities`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `milestones`
--
ALTER TABLE `milestones`
  ADD CONSTRAINT `milestones_ibfk_1` FOREIGN KEY (`ProjectId`) REFERENCES `projects` (`Id`);

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`ManagerId`) REFERENCES `users` (`Id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`PriorityId`) REFERENCES `priorities` (`Id`),
  ADD CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`MilestoneId`) REFERENCES `milestones` (`Id`),
  ADD CONSTRAINT `tasks_ibfk_3` FOREIGN KEY (`AssignedToId`) REFERENCES `users` (`Id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
