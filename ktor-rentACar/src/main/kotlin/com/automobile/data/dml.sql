--
-- Data Manipulation Language (DML) for database AutoMobile
--

USE `automobile`;

-- Insert data into `UserProfile` table
INSERT INTO `UserProfile` (`userProfileID`, `firstName`, `lastName`, `dateOfBirth`, `driversLicenceNumber`) VALUES
(1, 'Stephan', 'de Vries', '1976-03-20', 1146419821),
(2, 'Jochem', 'Janssen', '1965-07-02', 6022182635),
(3, 'Paula', 'Vermeer', '1998-08-23', 8006750144);

-- Insert data into `Car` table
INSERT INTO `Car` (`licencePlate`, `carBrand`, `vehicleType`, `amountOfPassengers`, `amountOfDoors`, `automatic`, `gpsAvailable`, `carPriceAmount`, `carPriceCurrency`, `userProfileID`) VALUES
('44-SB-ZZ', 'Citroën', 'C4', 5, 5, false, false, 12.99, 'Euro', 1),
('RV-483-R', 'Renault', 'Megane', 5, 5, false, true, 10.85, 'Euro', 2),
('T-742-VX', 'BMW', '3-Serie', 5, 5, true, true, 15.00, 'Euro', 3);