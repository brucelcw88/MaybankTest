-- DROP DATABASE user_service_db;
CREATE DATABASE user_service_db;


-- DROP SCHEMA user_service;
CREATE SCHEMA user_service AUTHORIZATION postgres;


-- Permissions
GRANT ALL ON SCHEMA user_service TO postgres;


-- DROP TABLE user_service."user";
CREATE TABLE user_service."user" (
	id uuid NOT NULL,
	"name" varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	age int4 NULL,
	address varchar(255) NULL,
	created_at timestamp NULL,
	created_by varchar(255) NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	CONSTRAINT idx_email UNIQUE (email),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);


-- Permissions
ALTER TABLE user_service."user" OWNER TO postgres;
GRANT ALL ON TABLE user_service."user" TO postgres;


-- Data
INSERT INTO user_service."user" (id,"name",email,age,address,created_at,created_by,updated_at,updated_by) VALUES
	 ('6eeec671-a93e-4562-970e-5dd348adc7ca','test','test@email.com',123,NULL,'2024-10-14 03:12:21.825121','SYSTEM','2024-10-14 03:12:21.825121','SYSTEM'),
	 ('3e74e199-de76-44d9-b0a3-d60f1a0435c1','Bruce','abc@email.com',50,NULL,'2024-10-14 22:59:49.6','SYSTEM','2024-10-14 22:59:49.6','SYSTEM'),
	 ('f0eb4b23-0e42-4771-8511-f2d25d2be499','Test for timestamp','abcdef@time.com',50,NULL,'2024-10-15 23:47:25.457','SYSTEM','2024-10-15 23:47:25.457','SYSTEM'),
	 ('7a4cea97-55eb-4c73-8270-a3b0028bef6a','Bruce LCW','abcd@email.com',50,NULL,'2024-10-14 23:01:08.115','SYSTEM','2024-10-16 01:45:43.128212','Bruce'),
	 ('6fffc671-a93e-4562-970e-5dd348adc7ca','Alice Smith','alice.smith@email.com',28,NULL,'2024-10-14 03:12:21.825','SYSTEM','2024-10-14 03:12:21.825','SYSTEM'),
	 ('d7d6cfd2-6b93-4f8b-8c76-1d54e4cb5f44','Bob Johnson','bob.johnson@email.com',34,NULL,'2024-10-14 03:15:00.123','SYSTEM','2024-10-14 03:15:00.123','SYSTEM'),
	 ('a02c7b91-0c87-4c9f-a546-6b4f914ac7d3','Charlie Brown','charlie.brown@email.com',45,NULL,'2024-10-14 03:20:45.456','SYSTEM','2024-10-14 03:20:45.456','SYSTEM'),
	 ('c142c8a7-8f4c-486b-bbe3-9f4085d34c05','David Wilson','david.wilson@email.com',30,NULL,'2024-10-14 03:25:10.789','SYSTEM','2024-10-14 03:25:10.789','SYSTEM'),
	 ('f5f3db6f-9c18-4f18-9129-c44c26b40c59','Eve Davis','eve.davis@email.com',27,NULL,'2024-10-14 03:30:55.321','SYSTEM','2024-10-14 03:30:55.321','SYSTEM'),
	 ('6bfb174b-e37f-4e7d-9b9b-5e1f7614d7a6','Frank Miller','frank.miller@email.com',41,NULL,'2024-10-14 03:35:32.654','SYSTEM','2024-10-14 03:35:32.654','SYSTEM');
INSERT INTO user_service."user" (id,"name",email,age,address,created_at,created_by,updated_at,updated_by) VALUES
	 ('8ae2e7c1-634c-4c7b-b47c-ec15ee7b5a59','Grace Lee','grace.lee@email.com',29,NULL,'2024-10-14 03:40:15.987','SYSTEM','2024-10-14 03:40:15.987','SYSTEM'),
	 ('b0b28d59-e456-4f9c-80b7-3a5943b5a06d','Henry Garcia','henry.garcia@email.com',36,NULL,'2024-10-14 03:45:44.321','SYSTEM','2024-10-14 03:45:44.321','SYSTEM'),
	 ('0cd5c5e4-8dc0-4c92-b3e5-915cdbf5cc16','Isabella Martinez','isabella.martinez@email.com',22,NULL,'2024-10-14 03:50:50.654','SYSTEM','2024-10-14 03:50:50.654','SYSTEM'),
	 ('5bcbf0c7-58ee-44a3-9e39-b1b2c6d1764f','Jack Rodriguez','jack.rodriguez@email.com',39,NULL,'2024-10-14 03:55:32.876','SYSTEM','2024-10-14 03:55:32.876','SYSTEM'),
	 ('a3f3b674-1f63-45b8-85de-fd295d0f80bc','Kathy Anderson','kathy.anderson@email.com',32,NULL,'2024-10-14 04:00:12.345','SYSTEM','2024-10-14 04:00:12.345','SYSTEM'),
	 ('cc0e13e0-d22e-46a3-85b8-8e139fd080f9','Leo Taylor','leo.taylor@email.com',47,NULL,'2024-10-14 04:05:45.678','SYSTEM','2024-10-14 04:05:45.678','SYSTEM'),
	 ('56aabb55-06c1-4670-bb80-ff1d3e3cd5d3','Mia Thomas','mia.thomas@email.com',24,NULL,'2024-10-14 04:10:22.567','SYSTEM','2024-10-14 04:10:22.567','SYSTEM'),
	 ('d1a8432e-42e3-4454-873c-74eb1cd5d1b2','Nina White','nina.white@email.com',26,NULL,'2024-10-14 04:15:33.456','SYSTEM','2024-10-14 04:15:33.456','SYSTEM'),
	 ('fffe4a7b-1b35-4b08-8e31-622fe8f16857','Oliver Harris','oliver.harris@email.com',31,NULL,'2024-10-14 04:20:21.234','SYSTEM','2024-10-14 04:20:21.234','SYSTEM'),
	 ('c9c5b4d7-0b0e-4c65-a227-49f9f16f7851','Paula Clark','paula.clark@email.com',38,NULL,'2024-10-14 04:25:30.678','SYSTEM','2024-10-14 04:25:30.678','SYSTEM'),
	 ('e65d6f48-25a8-4f5c-817b-67322f8ab5d7','Quinn Lewis','quinn.lewis@email.com',29,NULL,'2024-10-14 04:30:10.012','SYSTEM','2024-10-14 04:25:30.608','SYSTEM');
