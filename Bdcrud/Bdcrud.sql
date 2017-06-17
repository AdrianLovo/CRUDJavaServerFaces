-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bdcrud
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdcrud
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdcrud` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bdcrud` ;

-- -----------------------------------------------------
-- Table `bdcrud`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcrud`.`Persona` (
  `IdPersona` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Apellido` VARCHAR(45) NULL,
  `Edad` INT NULL,
  `Genero` VARCHAR(1) NULL,
  `FechaNac` DATE NULL,
  PRIMARY KEY (`IdPersona`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
