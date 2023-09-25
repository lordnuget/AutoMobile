--
-- Data Definition Language (DDL) for database AutoMobile
--

DROP DATABASE IF EXISTS `automobile`;
CREATE DATABASE IF NOT EXISTS `automobile`;

USE `automobile`;

-- Create `UserProfile`` table
CREATE TABLE IF NOT EXISTS `UserProfile` (
    `userProfileID` INT NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(255) NOT NULL,
    `lastName` VARCHAR(255) NOT NULL,
    `dateOfBirth` DATE NOT NULL,
    `driversLicenceNumber` BIGINT NOT NULL,
    PRIMARY KEY (userProfileID)
);

-- Create `Account` table
CREATE TABLE IF NOT EXISTS `Account` (
    `accountID` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `userProfileID` INT NOT NULL,
    PRIMARY KEY (accountID),
    FOREIGN KEY (userProfileID) REFERENCES UserProfile(userProfileID)
);

-- Create `Car` table
CREATE TABLE IF NOT EXISTS `Car` (
    `carID` INT NOT NULL AUTO_INCREMENT,
    `licencePlate` VARCHAR(255) NOT NULL,
    `carBrand` VARCHAR(255) NOT NULL,
    `vehicleType` VARCHAR(255) NOT NULL,
    `amountOfPassengers` INT NOT NULL,
    `amountOfDoors` INT NOT NULL,
    `automatic` BOOLEAN NOT NULL DEFAULT 0,
    `gpsAvailable` BOOLEAN NOT NULL DEFAULT 0,
    `carPriceAmount` DOUBLE NOT NULL,
    `carPriceCurrency` VARCHAR(255) NOT NULL DEFAULT "Euro",
    `userProfileID` INT NOT NULL,
    PRIMARY KEY (carID),
    FOREIGN KEY (userProfileID) REFERENCES UserProfile(userProfileID)
);

-- Create `CarAvailability` table
CREATE TABLE IF NOT EXISTS `CarAvailability` (
    `carAvailabilityID` INT NOT NULL AUTO_INCREMENT,
    `dateTimeFrom` DATETIME NOT NULL,
    `dateTimeUntil` DATETIME NOT NULL,
    `carID` INT NOT NULL,
    PRIMARY KEY (carAvailabilityID),
    FOREIGN KEY (carID) REFERENCES Car(carID)
);

-- Create `CarReservation` table
CREATE TABLE IF NOT EXISTS `CarReservation` (
    `carReservationID` INT NOT NULL AUTO_INCREMENT,
    `dateTimeFrom` DATETIME NOT NULL,
    `dateTimeUntil` DATETIME NOT NULL,
    `userProfileID` INT NOT NULL,
    `carID` INT NOT NULL,
    PRIMARY KEY (carReservationID),
    FOREIGN KEY (userProfileID) REFERENCES UserProfile(userProfileID),
    FOREIGN KEY (carID) REFERENCES Car(carID)
);

-- Create `Payment` table
CREATE TABLE IF NOT EXISTS `Payment` (
    `paymentID` INT NOT NULL AUTO_INCREMENT,
    `amount` DOUBLE NOT NULL,
    `currency` VARCHAR(255) NOT NULL DEFAULT "Euro",
    `paymentMethod` VARCHAR(255) NOT NULL,
    `dateTime` DATETIME NOT NULL,
    `carReservationID` INT NOT NULL,
    PRIMARY KEY (paymentID),
    FOREIGN KEY (carReservationID) REFERENCES CarReservation(carReservationID)
);

-- Create `bonusPointsTransaction` table
CREATE TABLE IF NOT EXISTS `BonusPointsTransaction` (
    `bonusPointsTransactionID` INT NOT NULL AUTO_INCREMENT,
    `amount` INT NOT NULL,
    `userProfileID` INT NOT NULL,
    `carReservationID` INT NOT NULL,
    PRIMARY KEY (bonusPointsTransactionID),
    FOREIGN KEY (userProfileID) REFERENCES UserProfile(userProfileID),
    FOREIGN KEY (carReservationID) REFERENCES CarReservation(carReservationID)
);

