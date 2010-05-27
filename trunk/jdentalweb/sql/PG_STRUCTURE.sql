CREATE SCHEMA "JDENTAL"
  AUTHORIZATION jdental;
COMMENT ON SCHEMA "JDENTAL" IS 'JDENTAL SCHEMA';

create table "JDENTAL".USER_T (
USER_ID SERIAL NOT NULL PRIMARY KEY, 
USER_LOGIN VARCHAR(255) not null unique,
USER_EMAIL VARCHAR(255) not null unique,
USER_PASSWD VARCHAR(255) not null unique,
USER_APPVD INT,
USER_TYPE INT,
USER_REG_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "JDENTAL".CLIENT_T (
CLIENT_ID SERIAL NOT NULL PRIMARY KEY, 
CLIENT_NAME VARCHAR(255) not null unique,
CLIENT_EMAIL VARCHAR(255) not null unique,
CLIENT_ADDR VARCHAR(255) not null ,
CLIENT_PHONE VARCHAR(255) not null ,
CLIENT_MOBILE VARCHAR(255) not null ,
CLIENT_CITY VARCHAR(255) not null ,
CLIENT_STATE VARCHAR(255) not null ,
CLIENT_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "JDENTAL".SCHEDULE_T (
SCHEDULE_ID 			SERIAL 			NOT NULL 	PRIMARY KEY, 
SCHEDULE_EVENT 			VARCHAR(255) 	NOT NULL,
SCHEDULE_USER_ID 		SERIAL 			NOT NULL	REFERENCES "JDENTAL".USER_T(USER_ID),
SCHEDULE_CLIENT_ID 		SERIAL		 	NOT NULL	REFERENCES "JDENTAL".CLIENT_T(CLIENT_ID),
SCHEDULE_INITIAL_DATE 	TIMESTAMP		NOT NULL,
SCHEDULE_END_DATE 		TIMESTAMP		NOT NULL,
SCHEDULE_DETAILS 		TEXT		 	NOT NULL,
SCHEDULE_TIMESTAMP 		TIMESTAMP 		NOT NULL	DEFAULT CURRENT_TIMESTAMP);

