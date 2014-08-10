SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `gooter` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `gooter` ;

-- -----------------------------------------------------
-- Table `caroll_db`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooter`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nomeUsuario` VARCHAR(45) NOT NULL,
  `loginUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll_db`.`Sentimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooter`.`Sentimento` (
  `idSentimento` INT NOT NULL,
  `valorSentimento` VARCHAR(45) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idSentimento`))
ENGINE = InnoDB;


INSERT INTO `gooter`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (1, 'Neutro');
INSERT INTO `gooter`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (2, 'Negativo');
INSERT INTO `gooter`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (3, 'Positivo');

-- -----------------------------------------------------
-- Table `caroll_db`.`Tweet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooter`.`Tweet` (
  `idTweet` INT NOT NULL AUTO_INCREMENT,
  `postTweet` VARCHAR(140) NOT NULL,
  `dataTweet` VARCHAR (20) NOT NULL,
  `tipoTweet` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Sentimento_idSentimento` INT NOT NULL,
  PRIMARY KEY (`idTweet`, `Usuario_idUsuario`, `Sentimento_idSentimento`),
 
  CONSTRAINT `fk_Tweet_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `caroll_db`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

   CONSTRAINT `fk_Tweet_Sentimento1`
    FOREIGN KEY (`Sentimento_idSentimento`)
    REFERENCES `caroll_db`.`Sentimento` (`idSentimento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
