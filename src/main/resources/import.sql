/* Creamos algunos usuarios con sus roles */
INSERT INTO users (date_created, password, email, isactive, last_login, name, token, uuid, date_modified ) VALUES ('2023-10-01','$2a$10$m5Brzg5FIZFXG.HPb81ZGuWdQQo9sKc1iufPr9Kjzr6JvvjGOSAs6', 'jeparejo@gmail.com', 'true', '2023-10-01', 'jesus parejo', '12312', '60a4d72b-9008-4758-ac51-3c14b1dbcd77' , '2023-10-14' );
INSERT INTO users (date_created, password, email, isactive, last_login, name, token, uuid, date_modified ) VALUES ('2023-10-01','$2a$10$bALZMpaW9xCvZ00ZTdt6Ju3Ex9aunWQHa4XSLLMXBhbgks4tfr8qS', 'japarejo@gmail.com', 'true', '2023-10-01', 'jaime parejo', '12312', '70a4d72b-9008-4758-ac51-3c14b1dbcd76' , '2023-10-14' );

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);

INSERT INTO USERS_PHONE (NUMBER_PHONE, CITY_CODE, COUNTRY_CODE, user_ID) VALUES ('123', '2', '123', 1);
INSERT INTO USERS_PHONE (NUMBER_PHONE, CITY_CODE, COUNTRY_CODE, user_ID) VALUES ('231', '3', '231', 2);




