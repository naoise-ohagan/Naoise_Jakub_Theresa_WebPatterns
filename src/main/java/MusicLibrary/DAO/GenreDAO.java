package MusicLibrary.DAO;

import MusicLibrary.dtos.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {
    List<Genre> getAllGenres() throws SQLException;
}
