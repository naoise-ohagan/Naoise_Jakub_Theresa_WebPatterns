package MusicLibrary.DAO;

import MusicLibrary.dtos.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDAO {
        List<Artist> getAllArtists();
    }
