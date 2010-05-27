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

CREATE TABLE "JDENTAL".client_t
(
  client_id integer NOT NULL DEFAULT nextval('"JDENTAL".client_t_client_id_seq'::regclass),
  client_name character varying(255) NOT NULL,
  client_email character varying(255) NOT NULL,
  client_addr character varying(255) NOT NULL,
  client_phone character varying(255) NOT NULL,
  client_mobile character varying(255) NOT NULL,
  client_city character varying(255) NOT NULL,
  client_state character varying(255) NOT NULL,
  client_timestamp timestamp without time zone NOT NULL DEFAULT now(),
  client_cpf character(255),
  CONSTRAINT client_t_pkey PRIMARY KEY (client_id),
  CONSTRAINT client_t_client_email_key UNIQUE (client_email),
  CONSTRAINT client_t_client_name_key UNIQUE (client_name)
)
WITH (OIDS=FALSE);
ALTER TABLE "JDENTAL".client_t OWNER TO jdental;


create table "JDENTAL".SCHEDULE_T (
SCHEDULE_ID 			SERIAL 			NOT NULL 	PRIMARY KEY, 
SCHEDULE_EVENT 			VARCHAR(255) 	NOT NULL,
SCHEDULE_USER_ID 		SERIAL 			NOT NULL	REFERENCES "JDENTAL".USER_T(USER_ID),
SCHEDULE_CLIENT_ID 		SERIAL		 	NOT NULL	REFERENCES "JDENTAL".CLIENT_T(CLIENT_ID),
SCHEDULE_INITIAL_DATE 	TIMESTAMP		NOT NULL,
SCHEDULE_END_DATE 		TIMESTAMP		NOT NULL,
SCHEDULE_DETAILS 		TEXT		 	NOT NULL,
SCHEDULE_TIMESTAMP 		TIMESTAMP 		NOT NULL	DEFAULT CURRENT_TIMESTAMP);



