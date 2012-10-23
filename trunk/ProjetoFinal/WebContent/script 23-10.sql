SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jogo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `jogo` ;

-- -----------------------------------------------------
-- Table `jogo`.`Usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Usuarios` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `senha` VARCHAR(255) NOT NULL ,
  `tipo` INT(11) NOT NULL ,
  `login` VARCHAR(45) NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `cidade` VARCHAR(45) NULL ,
  `rua` VARCHAR(45) NULL ,
  `bairro` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUsuarios`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Classe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Classe` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Classe` (
  `idClasse` INT NOT NULL AUTO_INCREMENT ,
  `descricao` CHAR(35) NOT NULL ,
  PRIMARY KEY (`idClasse`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Alianca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Alianca` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Alianca` (
  `idAlianca` INT NOT NULL AUTO_INCREMENT ,
  `dataCriacao` DATETIME NOT NULL ,
  `mensagem` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`idAlianca`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Item` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Item` (
  `idItem` INT NOT NULL ,
  `nome` VARCHAR(45) NOT NULL ,
  `valor` FLOAT NOT NULL ,
  `levelNecessario` INT NOT NULL ,
  PRIMARY KEY (`idItem`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Personagens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Personagens` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Personagens` (
  `idPersonagens` INT NOT NULL AUTO_INCREMENT ,
  `nick` VARCHAR(45) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `cash` FLOAT NOT NULL ,
  `mana` INT(11) NOT NULL ,
  `forca` INT(11) NOT NULL ,
  `agilidade` INT(11) NOT NULL ,
  `defesa` INT(11) NOT NULL ,
  `Usuarios_idUsuarios` INT NOT NULL ,
  `Classe_idClasse` INT NOT NULL ,
  `Alianca_idAlianca` INT NOT NULL ,
  `Item_idItem` INT NOT NULL ,
  PRIMARY KEY (`idPersonagens`, `Usuarios_idUsuarios`, `Classe_idClasse`, `Alianca_idAlianca`, `Item_idItem`) ,
  INDEX `fk_Personagens_Usuarios` (`Usuarios_idUsuarios` ASC) ,
  INDEX `fk_Personagens_Classe1` (`Classe_idClasse` ASC) ,
  INDEX `fk_Personagens_Alianca1` (`Alianca_idAlianca` ASC) ,
  INDEX `fk_Personagens_Item1_idx` (`Item_idItem` ASC) ,
  CONSTRAINT `fk_Personagens_Usuarios`
    FOREIGN KEY (`Usuarios_idUsuarios` )
    REFERENCES `jogo`.`Usuarios` (`idUsuarios` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personagens_Classe1`
    FOREIGN KEY (`Classe_idClasse` )
    REFERENCES `jogo`.`Classe` (`idClasse` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personagens_Alianca1`
    FOREIGN KEY (`Alianca_idAlianca` )
    REFERENCES `jogo`.`Alianca` (`idAlianca` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personagens_Item1`
    FOREIGN KEY (`Item_idItem` )
    REFERENCES `jogo`.`Item` (`idItem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Atributos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Atributos` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Atributos` (
  `idAtributos` INT NOT NULL AUTO_INCREMENT ,
  `forca` INT(11) NULL ,
  `agilidade` INT(11) NULL ,
  `defesa` INT(11) NULL ,
  `mana` INT(11) NULL ,
  `Classe_idClasse` INT NOT NULL ,
  PRIMARY KEY (`idAtributos`, `Classe_idClasse`) ,
  INDEX `fk_Atributos_Classe1` (`Classe_idClasse` ASC) ,
  CONSTRAINT `fk_Atributos_Classe1`
    FOREIGN KEY (`Classe_idClasse` )
    REFERENCES `jogo`.`Classe` (`idClasse` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Treino`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Treino` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Treino` (
  `idTreino` INT NOT NULL AUTO_INCREMENT ,
  `pontos` INT NOT NULL ,
  `dataInicial` DATETIME NULL ,
  `dataSaida` DATETIME NULL ,
  `Personagens_idPersonagens` INT NOT NULL ,
  `tempoNecessario` TIME NOT NULL ,
  PRIMARY KEY (`idTreino`, `Personagens_idPersonagens`) ,
  INDEX `fk_Treino_Personagens1` (`Personagens_idPersonagens` ASC) ,
  CONSTRAINT `fk_Treino_Personagens1`
    FOREIGN KEY (`Personagens_idPersonagens` )
    REFERENCES `jogo`.`Personagens` (`idPersonagens` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Desafios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Desafios` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Desafios` (
  `idDesafios` INT NOT NULL AUTO_INCREMENT ,
  `idDesafiante` INT(11) NULL ,
  `idDeafiado` INT(11) NULL ,
  `aposta` FLOAT NULL ,
  `Personagens_idPersonagens` INT NOT NULL ,
  PRIMARY KEY (`idDesafios`) ,
  INDEX `fk_Desafios_Personagens1` (`Personagens_idPersonagens` ASC) ,
  CONSTRAINT `fk_Desafios_Personagens1`
    FOREIGN KEY (`Personagens_idPersonagens` )
    REFERENCES `jogo`.`Personagens` (`idPersonagens` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jogo`.`Resultado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jogo`.`Resultado` ;

CREATE  TABLE IF NOT EXISTS `jogo`.`Resultado` (
  `idResultado` INT NOT NULL AUTO_INCREMENT ,
  `idGanhador` INT(11) NOT NULL ,
  `idPerdedor` INT(11) NOT NULL ,
  `dataDuelo` DATE NOT NULL ,
  `pontosGanhos` INT(11) NOT NULL ,
  `Desafios_idDesafios` INT NOT NULL ,
  PRIMARY KEY (`idResultado`, `Desafios_idDesafios`) ,
  INDEX `fk_Resultado_Desafios1` (`Desafios_idDesafios` ASC) ,
  CONSTRAINT `fk_Resultado_Desafios1`
    FOREIGN KEY (`Desafios_idDesafios` )
    REFERENCES `jogo`.`Desafios` (`idDesafios` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
