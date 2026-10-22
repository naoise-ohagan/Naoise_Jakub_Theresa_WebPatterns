USE
music_database;

INSERT INTO users (user_id, username, first_name, surname, email, password_hash)
VALUES (1, 'Jakub87', 'Jakub', 'Frank', 'jakub@example.com', 'test_hash_1'),
       (2, 'Naoise14', 'Naoise', 'O’Hagan', 'Naoise@example.com', 'test_hash_2'),
       (3, 'Theresa25', 'Theresa', 'Manurung', 'Theresa@example.com', 'test_hash_3');

INSERT INTO artists
    (artist_id, name, description)
VALUES (1, 'Queen', 'British rock band.'),
       (2, 'Adele', 'British pop singer and songwriter.'),
       (3, 'Miles Davis', 'American jazz musician.'),
       (4, 'Daft Punk', 'French electronic music duo.'),
       (5, 'Bob Dylan', 'American folk singer and songwriter.');

INSERT INTO genres
    (genre_id, name)
VALUES (1, 'Rock'),
       (2, 'Pop'),
       (3, 'Jazz'),
       (4, 'Electronic'),
       (5, 'Folk');

INSERT INTO albums (album_id, artist_id, title, release_date, number_of_tracks)
VALUES (1, 1, 'A Night at the Opera', '1975-11-21', 12),
       (2, 2, '21', '2011-01-24', 11),
       (3, 3, 'Kind of Blue', '1959-08-17', 5),
       (4, 4, 'Random Access Memories', '2013-05-17', 13),
       (5, 5, 'The Freewheelin'' Bob Dylan', '1963-05-27', 13);

INSERT INTO songs (song_id, artist_id, album_id, genre_id, title)
VALUES (1, 1, 1, 1, 'Bohemian Rhapsody'),
       (2, 2, 2, 2, 'Rolling in the Deep'),
       (3, 3, 3, 3, 'So What'),
       (4, 4, 4, 4, 'Get Lucky'),
       (5, 5, 5, 5, 'Blowin'' in the Wind');

INSERT INTO ratings (user_id, song_id, rating, rating_date)
VALUES (1, 1, 5, '2026-10-22'),
       (2, 2, 4, '2026-10-22'),
       (3, 3, 5, '2026-10-22');