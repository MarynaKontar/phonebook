DROP SCHEMA IF EXISTS `phonebook`;

CREATE SCHEMA IF NOT EXISTS `phonebook`;

USE `phonebook`;

CREATE TABLE IF NOT EXISTS `dep_tbl` (
  `id`      INT         NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(45) NOT NULL,
  `head_id` INT         NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `emp_tbl` (
  `id`         INT         NOT NULL AUTO_INCREMENT,
  `firstName`  VARCHAR(45) NOT NULL,
  `lastName`   VARCHAR(45) NOT NULL,
  `middleName` VARCHAR(45) NULL,
  `birthday`   DATE        NULL,
  `email`      VARCHAR(45) NULL,
  `skype`      VARCHAR(45) NULL,
  `icq`        VARCHAR(45) NULL,
  `addInfo`    VARCHAR(45) NULL,
  `dep_id`     INT         NULL,
  `head_id`    INT         NULL,
  `image_id`   INT         NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `address_tbl` (
  `id`        INT         NOT NULL AUTO_INCREMENT,
  `city`      VARCHAR(45) NOT NULL,
  `street`    VARCHAR(45) NOT NULL,
  `house`     VARCHAR(45) NULL,
  `apartment` VARCHAR(45) NULL,
  `type`      VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `employee_to_address_tbl` (
  `contact_id` INT NOT NULL,
  `emp_id`     INT NOT NULL,
  PRIMARY KEY (`contact_id`, `emp_id`)
);

CREATE TABLE IF NOT EXISTS `phone_tbl` (
  `id`    INT         NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NOT NULL,
  `type`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `employee_to_phone_tbl` (
  `contact_id` INT NOT NULL,
  `emp_id`     INT NOT NULL,
  PRIMARY KEY (`contact_id`, `emp_id`)
);

CREATE TABLE IF NOT EXISTS `image_tbl` (
  `id`    INT      NOT NULL AUTO_INCREMENT,
  `image` LONGTEXT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE dep_tbl ADD CONSTRAINT fk_dep_tbl_emp_tbl FOREIGN KEY (head_id) REFERENCES emp_tbl (id)
  ON DELETE SET NULL;

ALTER TABLE emp_tbl ADD CONSTRAINT fk_emp_tbl_dep_tbl FOREIGN KEY (dep_id) REFERENCES dep_tbl (id)
  ON DELETE SET NULL;
ALTER TABLE emp_tbl ADD CONSTRAINT fk_emp_tbl_emp_tbl FOREIGN KEY (head_id) REFERENCES emp_tbl (id)
  ON DELETE SET NULL;
ALTER TABLE emp_tbl ADD CONSTRAINT fk_emp_tbl_image_tbl FOREIGN KEY (image_id) REFERENCES image_tbl (id)
  ON DELETE CASCADE;

ALTER TABLE employee_to_address_tbl ADD CONSTRAINT fk_emp_to_address_emp_tbl FOREIGN KEY (emp_id) REFERENCES emp_tbl (id);
ALTER TABLE employee_to_address_tbl ADD CONSTRAINT fk_emp_to_ads_ads_tbl FOREIGN KEY (contact_id) REFERENCES address_tbl (id);

ALTER TABLE employee_to_phone_tbl ADD CONSTRAINT fk_emp_to_phone_emp_tbl FOREIGN KEY (emp_id) REFERENCES emp_tbl (id);
ALTER TABLE employee_to_phone_tbl ADD CONSTRAINT Ifk_emp_to_phone_phone_tbl FOREIGN KEY (contact_id) REFERENCES phone_tbl (id);