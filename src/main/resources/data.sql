INSERT INTO users (name, email, password)
VALUES ('Bloop blorp', 'blorp@gmail.com', '$2a$10$sbnm1oKTKZuujwjfE2YEPeGHTCoALAngP7iB4bjK2UvwsSVREC.CC');

INSERT INTO users (name, email, password)
VALUES ('Snip snop', 'snip@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (name, email, password)
VALUES ('Floop flop', 'floop@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (name, email, password)
VALUES ('Clip Clop', 'clop@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)

VALUES ('AFRICA', 'Egypt', 3, 1, 'Bubulcus ibis ["cattle egret"]', current_timestamp);

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('EUROPE', 'Greece', 1, 2, 'Bubulcus ibis ["cattle egret"]', current_timestamp);

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('AFRICA', 'South Africa', 20,  3, 'Apus apus ["Common swift"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('OCEANIA', 'Australia', 5, 1, 'Osphranter rufus ["red kangaroo"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('OCEANIA', 'Australia', 1, 3, 'Merops ornatus ["Rainbow Bee-eater"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('OCEANIA', 'Australia', 50, 3, 'Trichoglossus moluccanus ["Rainbow Lorikeet"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('NORTH_AMERICA', 'Canada', 1, 4, 'Erethizon dorsatus ["North American Porcupine"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('NORTH_AMERICA', 'Canada', 5, 4, 'Cyanocitta stelleri ["Steller''s Jay"]', current_timestamp );

INSERT INTO sighting (continent, country, quantity, user_id, speciesName, TIMEOFSIGHTING)
VALUES ('EUROPE', 'Belgium', 1, 1, 'Sciurus vulgaris ["Eurasian Red Squirrel"]', current_timestamp );