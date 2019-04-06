-- -----------------------------------------------------
-- Schema audiospot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audiospot` DEFAULT CHARACTER SET utf8 ;
USE `audiospot` ;

-- -----------------------------------------------------
-- Table `audiospot`.`artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`artist` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `country` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`album` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(32) NOT NULL,
  `photo` VARCHAR(42) NULL DEFAULT NULL,
  `artistId` BIGINT NOT NULL,
  `releaseYear` INTEGER NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Album_Author1_idx` (`artistId` ASC),
  CONSTRAINT `fk_Album_Author1`
    FOREIGN KEY (`artistId`)
    REFERENCES `audiospot`.`artist` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL DEFAULT NULL,
  `surname` VARCHAR(32) NULL DEFAULT NULL,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role` VARCHAR(32) NOT NULL,
  `discount` FLOAT NULL DEFAULT '0',
  `active` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`audio_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audio_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `userId` BIGINT NOT NULL,
  `date` DATE NOT NULL,
  `paid` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_Order_User_idx` (`userId` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`userId`)
    REFERENCES `audiospot`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 62
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`audioset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audioset` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `photo` VARCHAR(42) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`audiotrack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`audiotrack` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(42) NOT NULL,
  `path` VARCHAR(42) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `albumId` BIGINT NULL DEFAULT NULL,
  `artistId` BIGINT NOT NULL,
  `genre` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Audio Track_Album1_idx` (`albumId` ASC),
  INDEX `fk_Audio Track_Author1_idx` (`artistId` ASC),
  CONSTRAINT `fk_Audio Track_Album1`
    FOREIGN KEY (`albumId`)
    REFERENCES `audiospot`.`album` (`id`),
  CONSTRAINT `fk_Audio Track_Author1`
    FOREIGN KEY (`artistId`)
    REFERENCES `audiospot`.`artist` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 70
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(100) NOT NULL,
  `dateTime` DATETIME NOT NULL,
  `userId` BIGINT NOT NULL,
  `trackId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Comment_User1_idx` (`userId` ASC),
  INDEX `fk_comment_audiotrack1_idx` (`trackId` ASC),
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `audiospot`.`user` (`id`),
  CONSTRAINT `fk_comment_audiotrack1`
    FOREIGN KEY (`trackId`)
    REFERENCES `audiospot`.`audiotrack` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`ordered_track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`ordered_track` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `audioOrderId` BIGINT NOT NULL,
  `audiotrackId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Ordered Tracks_Audio Track1_idx` (`audiotrackId` ASC),
  INDEX `fk_Ordered Tracks_Order1` (`audioOrderId` ASC),
  CONSTRAINT `fk_Ordered Tracks_Audio Track1`
    FOREIGN KEY (`audiotrackId`)
    REFERENCES `audiospot`.`audiotrack` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ordered Tracks_Order1`
    FOREIGN KEY (`audioOrderId`)
    REFERENCES `audiospot`.`audio_order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 198
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `audiospot`.`track_at_audioset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiospot`.`track_at_audioset` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `audiosetId` BIGINT NOT NULL,
  `audiotrackId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Audio at playlist_Playlist1_idx` (`audiosetId` ASC),
  INDEX `fk_track_at_playlist_audiotrack1_idx` (`audiotrackId` ASC),
  CONSTRAINT `fk_Audio at audioset_Playlist1`
    FOREIGN KEY (`audiosetId`)
    REFERENCES `audiospot`.`audioset` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_track_at_audioset_audiotrack1`
    FOREIGN KEY (`audiotrackId`)
    REFERENCES `audiospot`.`audiotrack` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8;

insert into artist(id,name,country)
values (42,'John Doe','Canada');

insert into album (id,title,photo,artistId,releaseYear)
values (42,'Cool test album','testPhoto.jpg',42,2019);

insert into user (id,name,surname,login,password,role,discount,active)
values (42,'John','Doe','testLogin','testPass','CLIENT','2.75',1);

insert into audio_order (id,userId,date,paid)
values (42,42,STR_TO_DATE('6-04-2019', '%d-%m-%Y'),0);

insert into audioset (id,title,description,photo)
values (42,'Cool test audioSet','Really cool audioSet','testPhoto.jpg');

insert into audiotrack(id,title,path,price,artistId,genre)
values (42,'Test','localhost:test.mp3',3.22,42,'ROCK');

insert into comment(id,text,dateTime,userId,trackId)
values (42,'I liked it',STR_TO_DATE('4/6/2019 21:52','%m/%d/%Y %H:%i'),42,42);