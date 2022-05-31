BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS house_details;
DROP SEQUENCE IF EXISTS seq_house_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE SEQUENCE seq_house_id
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 1000
  CACHE 1;

CREATE TABLE house_details (
    house_id int DEFAULT nextval('seq_house_id') NOT NULL,
    foundation_size int NOT NULL,
    region varchar(100) NOT NULL,
    house_name varchar(100) NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_house_id PRIMARY KEY (house_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)

);



COMMIT TRANSACTION;
