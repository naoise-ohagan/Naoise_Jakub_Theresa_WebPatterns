package MusicLibrary.DAO;

import MusicLibrary.dtos.Artist;
import persistence.Connector;

import java.sql.SQLException;
import java.util.List;

public class ArtistDAOImp implements ArtistDAO {
    private Connector connector;

    @Override
    public List<Artist> getAllArtists() throws SQLException {
        return List.of();

    }
}
