--liquibase formatted sql

--changeset mkobierecki:20191006_001_device_location_table endDelimiter:/

CREATE TABLE DEVICE_LOCATION
(
    ID            NUMBER NOT NULL,
DEVICE_ID NUMBER NOT NULL,
LONGITUDE NUMBER NOT NULL,
LATITUDE NUMBER NOT NULL,
CREATED_AT      TIMESTAMP     NOT NULL,
CONSTRAINT IMEI_PK PRIMARY KEY (ID)
)
/

CREATE SEQUENCE DEVICE_LOCATION_IDX_SEQ
/