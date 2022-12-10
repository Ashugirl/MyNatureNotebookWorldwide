INSERT INTO users (firstName, lastName, userName, email, password)
VALUES ('Jo', 'Bakker', 'JoBakker', 'Jo1985@gmail.com', '$2a$10$sbnm1oKTKZuujwjfE2YEPeGHTCoALAngP7iB4bjK2UvwsSVREC.CC');

INSERT INTO users (firstName, lastName, userName, email, password)
VALUES ('Sarah', 'Stephens', 'PNW-NatureLover', 'sarahs@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (firstName, lastName, userName, email, password)
VALUES ('Virginia', 'Matthews', 'VeeMatt', 'veematt@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO users (firstName, lastName, userName, email, password)
VALUES ('Charles', 'Irving', 'CharlieIrv', 'chirv@gmail.com', '$2a$10$brkZMwQsPZCZXWwP5XGdOORhlgy5.k.cRA/izDmRj35kZVoMnphy.');

INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Neophoca cinerea - Australian Sealion', 'OCEANIA', 'Australia', 'King Island', '2021-12-13 16:25',
15, 'MAMMAL', NULL,NULL, 0, 'basking', 0 ,0, 1, 'large group at beach', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'AusSeaLions.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 1, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Arctocephalus pusillus doriferus - Australian fur seal', 'OCEANIA', 'Australia', 'King Island', '2021-12-17 15:43',
5, 'MAMMAL', NULL,NULL, 0, 'basking', 0 ,0, 1, 'hauled out on rocks', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'aus_fur_seal.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 2, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Gymnorhina tibicen - Australian Magpie', 'OCEANIA', 'Australia', 'Sydney Botanical Gardens', '2022-11-17 11:15',
1, 'BIRD', NULL, 'ADULT', 0, '', 0 ,1, 1, '', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'aus_magpie.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 3, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Haliaeetus leucocephalus - Bald Eagle', 'NORTH_AMERICA', 'Canada', 'Vancouver Island', '2022-10-17 10:15',
1, 'BIRD', NULL,'ADULT', 0, 'flying overhead', 0 ,0, 1, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'bald_eagle.jpg',

'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 4, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Cepphus columba - Pigeon Guillemot', 'NORTH_AMERICA', 'United States', 'Puget Sound, WA', '2022-10-12 14:22',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'pigeon_guillemot.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 5, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Danaus plexippus - monarch butterfly', 'NORTH_AMERICA', 'Canada', 'Toronto Waterfront Park', '2022-08-21 12:30',
1, 'INVERTEBRATE', NULL,NULL, 0, 'feeding', 0 ,0, 0, '', 3);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'monarchButterfly.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 6, 3);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Castor canadensis - American Beaver', 'NORTH_AMERICA', 'United States', 'Washington State', '2022-09-11 16:40',
1, 'MAMMAL', NULL,'ADULT', 0, 'swimming', 1 ,0, 1, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'beaver.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 7, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Podilymbus podiceps - Pied-billed Grebe', 'NORTH_AMERICA', 'United States', 'Washington State', '2022-09-11 16:35',
1, 'BIRD', NULL,'JUVENILE', 0, '', 0 ,0, 0, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'piedBilledGrebe.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 8, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Phalacrocorax auritus - Double-crested Cormorant', 'NORTH_AMERICA', 'United States', 'Near Everett, WA', '2022-10-17 10:15',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'd_c_cormorant.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 9, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Archilochus colubris - Ruby-throated Hummingbird', 'NORTH_AMERICA', 'Canada', 'Vancouver Island', '2021-09-21 17:25',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0, '', 2);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'hummingbird.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 10, 2);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Erinaceus europaeus - western European hedgehog', 'EUROPE', 'Belgium', 'East Flanders', '2022-07-13 12:34',
1, 'MAMMAL', NULL,'ADULT', 0, '', 0 ,0, 0, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'hedgehog.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 11, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Podarcis muralis - Common Wall Lizard', 'EUROPE', 'Belgium', 'Dendermonde', '2022-06-28 10:15',
1, 'REPTILE', NULL, 'ADULT', 0, 'basking', 1 ,0, 1, 'near the rail tracks', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'wallLizard.jpeg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 12, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Athene noctua - Little Owl', 'EUROPE', 'Greece', 'Lesbos', '2022-05-15 17:45',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 1, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'little_owl.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 13, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Corvus monedula - Eurasian Jackdaw', 'EUROPE', 'Netherlands', 'Amsterdam', '2021-06-05 18:20',
1, 'BIRD', NULL,'JUVENILE', 0, 'just fledged', 0 ,0, 0, 'fledged onto my windowsill', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'jackdaw_fledgeling.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 14, 4);


INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'Jackdaw_fledge.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 14, 4);

INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Larus ridibundus - Common Black-headed Gull', 'EUROPE', 'Netherlands', 'Amsterdam', '2022-01-17 10:15',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0, 'winter plumage', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'blackHeaded_gull.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 15, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Podiceps cristatus - Great Crested Grebe', 'EUROPE', 'Netherlands', 'Beatrix Park Amsterdam', '2022-05-20 08:30',
1, 'BIRD', NULL,NULL, 0, 'nesting', 1 ,0, 0, 'saw three eggs', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'GCGrebe_nest.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 16, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Podiceps cristatus - Great Crested Grebe', 'EUROPE', 'Netherlands', 'Amsterdam Zuid', '2022-06-21 13:35',
2, 'BIRD', NULL,NULL, 0, '', 0 ,0, 0, 'one adult with young', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'GCGrebe_chick.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 17, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Tropaeolum majus - nasturtium', 'EUROPE', 'Belgium', '', '2022-07-22 15:22',
1, NULL, NULL,NULL, 0, '', 0 ,0, 0, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'nasturtium.jpeg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 18, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Burhinus oedicnemus - Eurasian Stone-curlew', 'EUROPE', 'Greece', 'Lesbos', '2022-05-17 16:15',
1, 'BIRD', NULL, 'ADULT', 0, 'foraging', 0 ,0, 1, 'well-camoflaged on pebble beach', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'stone-curlew.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 19, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Himantopus himantopus - Black-winged Stilt', 'EUROPE', 'Greece', 'Lesbos', '2022-05-17 12:15',
10, 'BIRD', NULL,NULL, 0, 'foraging', 0 ,0, 1, 'small group on inland marsh', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'stilt1.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 20, 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'stilt2.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 20, 4);

INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Ardea cinerea - Grey Heron', 'EUROPE', 'Netherlands', 'Amsterdam Zuid', '2021-01-12 10:15',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0 , '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'grey_heron.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 21, 4);

INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Ixobrychus minutus - Little Bittern', 'EUROPE', 'Greece', 'Lesbos', '2022-05-17 15:10',
1, 'BIRD', NULL,'ADULT', 0, 'fishing', 1 ,0, 1, 'well-hidden in reeds', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'little_bittern.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 22, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Certhia familiaris - Eurasian Treecreeper', 'EUROPE', 'Belgium', 'East Flanders', '2022-09-14 14:21',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 1, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'treecreeper.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 23, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Erycinae - Old World Sand Boas', 'EUROPE', 'Greece', 'Lesbos', '2022-05-17 10:15',
1, 'REPTILE', NULL,NULL, 0, 'basking', 0 ,0, 1, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'sandBoa.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 24, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Columba palumbus - Common Wood Pigeon', 'EUROPE', 'Belgium', 'Gent', '2022-11-17 10:15',
2, 'BIRD', NULL,'ADULT', 0, 'courting', 0 ,0, 0, '', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'woodpigeons.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 25, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Phalacrocorax carbo - Great Cormorant', 'EUROPE', 'Netherlands', 'Amsterdam', '2021-12-11 14:45',
10, 'BIRD', NULL,NULL, 0, 'sunning', 0 ,0, 0, 'large group on Amstel river', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'cormorant.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 26, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Eristalis tenax - drone fly', 'EUROPE', 'Belgium', 'Dendermonde', '2022-06-17 16:23',
1, 'INVERTEBRATE', NULL,NULL, 0, '', 0 ,0, 1, 'in my garden', 4);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'drone_fly.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 27, 4);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Eolophus roseicapilla - Galah', 'OCEANIA', 'Australia', 'Alice Springs', '2021-11-05 09:15',
6, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 0, 'large noisy group', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'galahs.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 28, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Dacelo novaeguineae - Laughing Kookaburra', 'OCEANIA', 'Australia', 'Blue Mountains', '2021-12-24 11:35',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 1, '', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'kookabura.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 29, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Pteropus poliocephalus - Gray-headed Flying Fox', 'OCEANIA', 'Australia', 'Sydney Botanical Gardens', '2022-11-17 12:25',
50, 'MAMMAL', NULL,NULL, 0, '', 0 ,0, 1, 'large group in trees', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'flyingFoxwings.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 30, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Pteropus poliocephalus - Gray-headed Flying Fox', 'OCEANIA', 'Australia', 'Sydney Botanical Gardens', '2022-11-17 12:25',
50, 'MAMMAL', NULL,NULL, 0, '', 0 ,0, 1, 'large group in trees', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'flying_foxes1.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 31, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Merops ornatus - Rainbow Bee-eater', 'OCEANIA', 'Australia', 'Uluru', '2021-11-24 14:45',
1, 'BIRD', NULL,'ADULT', 0, '', 0 ,0, 1, '', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'rainbowBeeEater.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 32, 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'RB_bee-eater.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 32, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Macropus giganteus - Eastern Grey Kangaroo', 'OCEANIA', 'Australia', 'Blue Mountains', '2021-12-24 15:15',
12, 'MAMMAL', NULL,NULL, 0, 'grazing', 0 ,0, 1, 'mixed group of adults and juveniles', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'kangas.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 33, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Phascolarctos cinereus - Koala', 'OCEANIA', 'Australia', 'outside Melbourne', '2021-11-30 10:09',
2, 'MAMMAL', NULL,NULL, 0, 'nursing', 0 ,0, 1, 'mother holding joey', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'koala.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 34, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Thylogale thetis - Red-necked Pademelon', 'OCEANIA', 'Australia', 'Sydney', '2021-11-18 12:15',
1, 'MAMMAL', NULL,'ADULT', 0, 'grazing', 0 ,0, 1, 'on golf course', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'pademelon.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 35, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Intellagama lesueurii - Eastern Water Dragon', 'OCEANIA', 'Australia', 'Brisbane', '2021-12-17 15:12',
1, 'REPTILE', NULL,'ADULT', 0, 'basking', 0 ,0, 1, 'botanical gardens', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'waterDragon.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 36, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Pelecanus conspicillatus - Australian Pelican', 'OCEANIA', 'Australia', 'King Island', '2021-10-12 14:20',
3, 'BIRD', NULL,NULL, 0, '', 0 ,0, 1, 'on beach', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'pelicans.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 37, 1);


INSERT INTO sighting (sightingId, speciesName, continent, country, location, dateOfSighting, quantity, classOfAnimal, sex, lifeStage,
deceased, behaviour, locationHidden, keepPrivate, lifer, notes, user_id)
VALUES (default, 'Pelecanus conspicillatus - Australian Pelican', 'OCEANIA', 'Australia', 'King Island', '2021-10-12 14:20',
2, 'BIRD', NULL,NULL, 0, '', 0 ,0, 1, 'on beach', 1);

INSERT INTO photo (photoId, fileName, path, sighting_sighting_id, user_id)
VALUES (default, 'pelicans2.jpg',
'/Users/avivashuman/IdeaProjects/MyNatureNotebookWorldwide/src/main/resources/static/photos/', 38, 1);

