BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS room_details;
DROP SEQUENCE IF EXISTS seq_room_id;
DROP TABLE IF EXISTS floor;
DROP SEQUENCE IF EXISTS seq_floor_id;
DROP TABLE IF EXISTS house_details;
DROP SEQUENCE IF EXISTS seq_house_id;
DROP TABLE IF EXISTS flooring_tier;

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
INSERT INTO users (username,password_hash,role) VALUES ('bob','$2a$10$4oOJ/QruQg14j7.Bda6hau8PAyX1mtUIFpSUWmSQj/u9OIzpYzHNu','ROLE_HOME_BUYER');


CREATE SEQUENCE seq_house_id
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 1000
  CACHE 1;

CREATE TABLE house_details (
    house_id int DEFAULT nextval('seq_house_id') NOT NULL,
    foundation_size int NOT NULL,
    city varchar(100) NOT NULL,
    state_abbreviation varchar(100) NOT NULL,
    house_name varchar(100) NOT NULL,
    user_id int NOT NULL,
    number_of_floors int DEFAULT(1),
    is_Private boolean NOT NULL,
    CONSTRAINT PK_house_id PRIMARY KEY (house_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)

);

INSERT INTO house_details(house_id, foundation_size,city, state_abbreviation,house_name,user_id,number_of_floors,is_Private)
VALUES(990,3000,'Houston', 'TX', 'Big House', 3, 4, false);
INSERT INTO house_details(house_id, foundation_size,city, state_abbreviation,house_name,user_id,number_of_floors,is_Private)
VALUES(991,1500,'Chicago', 'IL', 'Small House', 2, 1, false);
INSERT INTO house_details(house_id, foundation_size,city, state_abbreviation,house_name,user_id,number_of_floors,is_Private)
VALUES(992,2000,'Cincinnati', 'OH', 'Party House', 3, 3, true);
INSERT INTO house_details(house_id, foundation_size,city, state_abbreviation,house_name,user_id,number_of_floors,is_Private)
VALUES(993,2500,'Austin', 'TX', 'Haunted House', 3, 3, false);




CREATE SEQUENCE seq_floor_id
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 101
  CACHE 1;

CREATE TABLE floor (
    floor_id int DEFAULT nextval('seq_floor_id') NOT NULL,
    house_id int NOT NULL,
    floor_level int NOT NULL,
    CONSTRAINT PK_floor_id PRIMARY KEY (floor_id),
    CONSTRAINT FK_house_id FOREIGN KEY (house_id) REFERENCES house_details (house_id)
);

INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(90,990,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(91,990,2);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(92,990,3);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(93,990,4);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(94,991,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(95,992,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(96,992,2);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(97,992,3);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(98,993,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(99,993,2);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(100,993,3);


CREATE SEQUENCE seq_room_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  START WITH 21
  CACHE 1;


CREATE TABLE flooring_tier (
    flooring_tier_id serial NOT NULL,
    flooring_name varchar (50) NOT NULL,
    tier_level int NOT NULL,
    CONSTRAINT PK_flooring_tier_id PRIMARY KEY (flooring_tier_id)
);

INSERT INTO flooring_tier (flooring_tier_id, flooring_name, tier_level)
VALUES (1, 'Laminate, Carpet, Ceramic Tile', 1);
INSERT INTO flooring_tier (flooring_tier_id, flooring_name, tier_level)
VALUES (2, 'Hardwood, Porcelain, Polished Concrete', 2);
INSERT INTO flooring_tier (flooring_tier_id, flooring_name, tier_level)
VALUES (3, 'Granite, Marble, Slate', 3);
INSERT INTO flooring_tier (flooring_tier_id, flooring_name, tier_level)
VALUES (4, 'Exotic Hardwood, Custom Porcelain, Lux Touch Tile', 4);




CREATE TABLE room_details(
    room_id int DEFAULT nextval('seq_room_id') NOT NULL,
    room_name varchar(50) NOT NULL,
    room_size int NOT NULL,
    floor_id int,
    is_kitchen boolean NOT NULL,
    is_bathroom boolean NOT NULL,
    number_of_windows int,
    flooring_tier_id int DEFAULT(0) NOT NULL,
    CONSTRAINT PK_room_id PRIMARY KEY(room_id),
    CONSTRAINT FK_floor_id FOREIGN KEY (floor_id) REFERENCES floor(floor_id),
    CONSTRAINT FK_flooring_tier_id FOREIGN KEY (flooring_tier_id) REFERENCES flooring_tier (flooring_tier_id)
);


INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (1, 'Bedroom',10, 90,false,false,1,1);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (2, 'Kitchen',7, 90,true,false,2,2);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (3, 'Bathroom',10, 91,false,true,1,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (4, 'Bedroom',10, 92,false,false,1,4);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (5, 'Bedroom',10, 93,false,false,2,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (6, 'Kitchen',10, 94,true,false,1,2);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (7, 'Bedroom',10, 94,false,false,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (8, 'Bathroom',10, 95,false, true,0,1);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (9, 'Bedroom',10, 95,false,false,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (10, 'Bedroom',10, 96,false,false,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (11, 'Bedroom',10, 96,false,false,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (12, 'Bedroom',10, 97,false,false,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (13, 'Bathroom',10, 97,false,true,3,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (14, 'Bedroom',10, 98,false,false,1,1);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (15, 'Kitchen',7, 98,true,false,2,2);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (16, 'Bathroom',10, 98,false,true,1,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (17, 'Bedroom',10, 99,false,false,1,4);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (18, 'Bedroom',10, 99,false,false,2,3);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (19, 'Kitchen',10, 100,true,false,1,2);
INSERT INTO room_details(room_id, room_name, room_size, floor_id,is_kitchen,is_bathroom,number_of_windows,flooring_tier_id)
VALUES (20, 'Secret Room',18, 100,false,false,0,2);







COMMIT TRANSACTION;
