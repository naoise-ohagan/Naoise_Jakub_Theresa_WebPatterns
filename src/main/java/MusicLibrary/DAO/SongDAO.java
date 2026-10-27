package MusicLibrary.DAO;

import MusicLibrary.dtos.SongSearchResult;

import java.sql.SQLException;
import java.util.List;

public interface SongDAO {
    List<SongSearchResult> searchSongs(String searchTerm, String searchBy) throws SQLException
}
