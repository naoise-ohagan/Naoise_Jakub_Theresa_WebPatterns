package MusicLibrary.DAO;

import MusicLibrary.dtos.Artist;
import persistence.Connector;
import persistence.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOImp implements ArtistDAO {
    private Connector connector;

    public ArtistDAOImp(Connector connector){
        this.connector = connector;
    }

    @Override
    public List<Artist> getAllArtists() {

        List<Artist> artists = new ArrayList<>();

        Connection conn = Connector.getConnection();

        if (conn == null) {
            System.out.println("No connection available!");
            return null;
        }

        String sql = "SELECT * FROM artists ORDER BY name";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Artist artist = mapRow(rs);
                    artists.add(artist);
                }

            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage());
                System.out.println("Error with results..");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Cannot prepare statements: " + sql);
        }

        return artists;
    }

    private static Artist mapRow(ResultSet rs) throws SQLException {
        Artist artist = new Artist(rs.getInt("artist_id"), rs.getString("name"), rs.getString("description"));

        return artist;
    }
    public static void main(String[] args) {

        String propertiesFilename = "db_properties/database.properties";

        Connector connector = new MySqlConnector(propertiesFilename);

        ArtistDAO artistDao = new ArtistDAOImp(connector);

        List<Artist> artists = artistDao.getAllArtists();

        System.out.println("All Artists:");

        if (artists != null) {
            for (Artist artist : artists) {
                System.out.println(artist);
            }
        }
    }
}

