DROP TABLE IF EXISTS BEER;

DROP TABLE IF EXISTS MANUFACTURER;

CREATE TABLE MANUFACTURER (
MANUFACTURER_ID  BIGINT NOT NULL AUTO_INCREMENT,
  COMPANY VARCHAR(250) NOT NULL ,
  NATIONALITY VARCHAR(250),
PRIMARY KEY(MANUFACTURER_ID)
);

CREATE TABLE BEER (
BEER_ID  BIGINT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL ,
  BEERTYPE VARCHAR(250),
  GRADUATION VARCHAR(250),
  DESCRIPTION VARCHAR(250),
  MANUFACTURER_ID INT,PRIMARY KEY(BEER_ID)
);

alter table BEER
add constraint MANUFACTURERID_FK  foreign key(MANUFACTURER_ID)references MANUFACTURER;
