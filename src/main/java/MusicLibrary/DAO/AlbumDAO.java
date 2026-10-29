package MusicLibrary.DAO;

import MusicLibrary.dtos.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDAO {
    List<Album> getAlbumByArtist(int artistID);
}
