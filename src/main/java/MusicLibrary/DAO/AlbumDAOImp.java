package MusicLibrary.DAO;

import MusicLibrary.dtos.Album;
import persistence.Connector;
import persistence.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImp implements AlbumDAO {
    private Connector connector;

    public AlbumDAOImp(Connector connector){
        this.connector = connector;
    }

    @Override
    public List<Album> getAlbumByArtist(int artistID) {
        return List.of();
    }

    @Override
    public List<Album>getAllAlbums(){
        List<Album> albums = new ArrayList<>();

        Connection conn = Connector.getConnection();

        if(conn == null) {
            System.out.println("No connection available!");
            return null;
        }

        String sql = "SELECT * FROM albums ORDER BY title";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Album album = mapRow(rs);
                    albums.add(album);
                }
            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage());
                System.out.println("Error processing results for getAllAlbums()");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Cannot prepare statement: " + sql);
        }

        return albums;
    }

    private static Album mapRow(ResultSet rs) throws SQLException {

        Album album = new Album(rs.getInt("album_id"), rs.getInt("artistID"), rs.getString("title"), rs.getDate("releaseDate").toLocalDate(), rs.getInt("NumberOfTracks"));
        return album;
    }

    public static void main(String[] args) {

        String propertiesFilename = "db_properties/database.properties";

        // Create connection manager object
        Connector connector = new MySqlConnector(propertiesFilename);

        AlbumDAO albumDao = new AlbumDAOImp(connector);

        List<Album> albums = albumDao.getAllAlbums();

        System.out.println("All Albums:");

        if (albums != null) {
            for (Album album : albums) {
                System.out.println(album);
            }
        }
    }
}
