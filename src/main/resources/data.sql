INSERT INTO users (name, email, password)
VALUES ('Bloop blorp', 'blorp@gmail.com', '$2a$10$sbnm1oKTKZuujwjfE2YEPeGHTCoALAngP7iB4bjK2UvwsSVREC.CC');

INSERT INTO users (name, email, password)
VALUES ('Snip snop', 'snip@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (name, email, password)
VALUES ('Floop flop', 'floop@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (name, email, password)
VALUES ('Clip Clop', 'clop@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default, 'AFRICA', 'Egypt', 3, 1, 'Bubulcus ibis ["cattle egret"]', '2021-06-21', '12:13');

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default ,'EUROPE', 'Greece', 1, 2, 'Bubulcus ibis ["cattle egret"]', '2022-01-14', '15:34');

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default, 'AFRICA', 'South Africa', 20,  3, 'Apus apus ["Common swift"]', '2022-02-13', '09:30' );

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default, 'OCEANIA', 'Australia', 5, 1, 'Osphranter rufus ["red kangaroo"]', '2021-12-30', '11:34' );

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default ,'OCEANIA', 'Australia', 1, 3, 'Merops ornatus ["Rainbow Bee-eater"]', '2022-11-17', '10:15');

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default ,'OCEANIA', 'Australia', 50, 3, 'Trichoglossus moluccanus ["Rainbow Lorikeet"]', '2022-04-05', '20:12' );

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default , 'NORTH_AMERICA', 'Canada', 1, 4, 'Erethizon dorsatus ["North American Porcupine"]', '2021-04-05', '18:23' );

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default ,'NORTH_AMERICA', 'Canada', 5, 4, 'Cyanocitta stelleri ["Steller''s Jay"]', '2021-05-05', '16:45' );

INSERT INTO sighting (sightingId, continent, country, quantity, user_id, speciesName, dateOfSighting, timeOfSighting)
VALUES (default ,'EUROPE', 'Belgium', 1, 1, 'Sciurus vulgaris ["Eurasian Red Squirrel"]', '2022-11-06', '09:31' );