-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema audiospot
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema audiospot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audiospot` DEFAULT CHARACTER SET utf8 ;
USE `audiospot` ;

-- -----------------------------------------------------
-- Table `audiospot`.`artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`artist` (
  `artist_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL DEFAULT NULL,
  `country` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`artist_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`album` (
  `album_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(32) NULL DEFAULT NULL,
  `author_id` INT(11) NOT NULL,
  `release_year` YEAR(4) NULL DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  INDEX `fk_Album_Author1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_Album_Author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `audiospot`.`artist` (`artist_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NULL DEFAULT NULL,
  `surname` VARCHAR(32) NULL DEFAULT NULL,
  `login` VARCHAR(15) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role` ENUM('admin', 'client') NOT NULL,
  `discount` FLOAT NULL DEFAULT '0',
  `active` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = ascii;


-- -----------------------------------------------------
-- Table `audiospot`.`audio_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audio_order` (
  `audio_order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `paid` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`audio_order_id`),
  INDEX `fk_Order_User_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `audiospot`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 62
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`audioset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audioset` (
  `audioset_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`audioset_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`audiotrack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audiotrack` (
  `audiotrack_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(4,2) NULL DEFAULT NULL,
  `album_id` INT(11) NULL DEFAULT NULL,
  `author_id` INT(11) NULL DEFAULT NULL,
  `genre` ENUM('classic', 'pop', 'rap', 'rock') NULL DEFAULT NULL,
  PRIMARY KEY (`audiotrack_id`),
  INDEX `fk_Audio Track_Album1_idx` (`album_id` ASC) VISIBLE,
  INDEX `fk_Audio Track_Author1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_Audio Track_Album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `audiospot`.`album` (`album_id`),
  CONSTRAINT `fk_Audio Track_Author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `audiospot`.`artist` (`artist_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 70
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`comment` (
  `comment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(100) NULL DEFAULT NULL,
  `date_time` DATETIME NULL DEFAULT NULL,
  `user_id` INT(11) NOT NULL,
  `audiotrack_id` INT(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_Comment_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_comment_audiotrack1_idx` (`audiotrack_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `audiospot`.`user` (`user_id`),
  CONSTRAINT `fk_comment_audiotrack1`
    FOREIGN KEY (`audiotrack_id`)
    REFERENCES `audiospot`.`audiotrack` (`audiotrack_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`ordered_track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`ordered_track` (
  `ordered_track_id` INT(11) NOT NULL AUTO_INCREMENT,
  `audio_order_id` INT(11) NOT NULL,
  `audiotrack_id` INT(11) NOT NULL,
  PRIMARY KEY (`ordered_track_id`),
  INDEX `fk_Ordered Tracks_Audio Track1_idx` (`audiotrack_id` ASC) VISIBLE,
  INDEX `fk_Ordered Tracks_Order1` (`audio_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Ordered Tracks_Audio Track1`
    FOREIGN KEY (`audiotrack_id`)
    REFERENCES `audiospot`.`audiotrack` (`audiotrack_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ordered Tracks_Order1`
    FOREIGN KEY (`audio_order_id`)
    REFERENCES `audiospot`.`audio_order` (`audio_order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 198
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`track_at_audioset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`track_at_audioset` (
  `track_at_audioset_id` INT(11) NOT NULL AUTO_INCREMENT,
  `audioset_id` INT(11) NOT NULL,
  `audiotrack_id` INT(11) NOT NULL,
  PRIMARY KEY (`track_at_audioset_id`),
  INDEX `fk_Audio at playlist_Playlist1_idx` (`audioset_id` ASC) VISIBLE,
  INDEX `fk_track_at_playlist_audiotrack1_idx` (`audiotrack_id` ASC) VISIBLE,
  CONSTRAINT `fk_Audio at audioset_Playlist1`
    FOREIGN KEY (`audioset_id`)
    REFERENCES `audiospot`.`audioset` (`audioset_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_track_at_audioset_audiotrack1`
    FOREIGN KEY (`audiotrack_id`)
    REFERENCES `audiospot`.`audiotrack` (`audiotrack_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
