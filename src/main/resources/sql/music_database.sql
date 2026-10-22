DROP DATABASE IF EXISTS music_database;
CREATE DATABASE IF NOT EXISTS music_database;

USE music_database;

DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS albums;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS artists;
DROP TABLE IF EXISTS users;

/*Table structure for table users */
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(30) NOT NULL,
                       first_name VARCHAR(30) NOT NULL,
                       surname VARCHAR(30) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(60) NOT NULL
);

/*Table structure for table artists */
CREATE TABLE artists (
                         artist_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(150) NOT NULL
);

/*Table structure for table genres */
CREATE TABLE genres (
                        genre_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL
);

/*Table structure for table albums */
CREATE TABLE albums (
                        album_id INT AUTO_INCREMENT PRIMARY KEY,
                        artist_id INT NOT NULL,
                        title VARCHAR(150) NOT NULL,
                        release_date DATE NOT NULL,
                        number_of_tracks INT NOT NULL,

                        CONSTRAINT fk_albums_artist
                            FOREIGN KEY (artist_id)
                                REFERENCES artists (artist_id)
                                ON UPDATE CASCADE
                                ON DELETE RESTRICT
);

/*Table structure for table song */
CREATE TABLE songs (
                       song_id INT AUTO_INCREMENT PRIMARY KEY,
                       artist_id INT NOT NULL,
                       album_id INT NULL,
                       genre_id INT NOT NULL,
                       title VARCHAR(150) NOT NULL,

                       CONSTRAINT fk_songs_artist
                           FOREIGN KEY (artist_id)
                               REFERENCES artists (artist_id)
                               ON UPDATE CASCADE
                               ON DELETE RESTRICT,

                       CONSTRAINT fk_songs_album
                           FOREIGN KEY (album_id)
                               REFERENCES albums (album_id)
                               ON UPDATE CASCADE
                               ON DELETE SET NULL,

                       CONSTRAINT fk_songs_genre
                           FOREIGN KEY (genre_id)
                               REFERENCES genres (genre_id)
                               ON UPDATE CASCADE
                               ON DELETE RESTRICT
);

/*Table structure for table ratings */
CREATE TABLE ratings (
                         user_id INT NOT NULL,
                         song_id INT NOT NULL,
                         rating TINYINT  NOT NULL,
                         rating_date DATE NOT NULL DEFAULT (CURRENT_DATE),

                         PRIMARY KEY (user_id, song_id),

                         CONSTRAINT chk_ratings_value
                             CHECK (rating BETWEEN 1 AND 5),

                         CONSTRAINT fk_ratings_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users (user_id)
                                 ON UPDATE CASCADE
                                 ON DELETE CASCADE,

                         CONSTRAINT fk_ratings_song
                             FOREIGN KEY (song_id)
                                 REFERENCES songs (song_id)
                                 ON UPDATE CASCADE
                                 ON DELETE CASCADE
);