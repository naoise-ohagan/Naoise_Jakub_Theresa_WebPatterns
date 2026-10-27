package persistence;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * MySQL implementation of the user data access object.
 * @author Jakub
 */
public class UserDaoImpl implements UserDao {
    private final Connector connector;

    public UserDaoImpl(Connector connector) {
        this.connector = connector;
    }

    /**
     * Closes the database connection used by the DAO.
     */
    @Override
    public void closeConnection() {
        connector.freeConnection();
    }

    /**
     * Adds a new user to the users table.
     *
     * @param user user data to add
     * @return true if one user was added, otherwise false
     */
    @Override
    public boolean addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        Connection connection = connector.getConnection();
        if (connection == null) {
            System.out.println("Could not establish a connection to the database.");
            return false;
        }

        String sql = "INSERT INTO users (username, first_name, surname, email, password_hash) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.username());
            ps.setString(2, user.firstName());
            ps.setString(3, user.surname());
            ps.setString(4, user.email());
            ps.setString(5, user.passwordHash());

            int rowsAdded = ps.executeUpdate();
            return rowsAdded == 1;
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("Username or email is already registered.");
        } catch (SQLException exception) {
            System.out.println("The SQL query could not be prepared or executed.");
        }

        return false;
    }

    /**
     * Finds a user in the users table by username.
     *
     * @param username username to search for
     * @return the matching user, or null if the user does not exist
     */
    @Override
    public User findByUsername(String username) {
        Connection connection = connector.getConnection();
        if (connection == null) {
            System.out.println("Could not establish a connection to the database.");
            return null;
        }

        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("first_name"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("password_hash")
                    );
                }
            }
        } catch (SQLException exception) {
            System.out.println("The SQL query could not be prepared or executed.");
        }

        return null;
    }

    /**
     * Updates the password hash stored for a user.
     *
     * @param userId ID of the user to update
     * @param passwordHash new BCrypt password hash
     * @return true if one user's password was updated, otherwise false
     */
    @Override
    public boolean updatePasswordHash(int userId, String passwordHash) {
        Connection connection = connector.getConnection();
        if (connection == null) {
            System.out.println("Could not establish a connection to the database.");
            return false;
        }

        String sql = "UPDATE users SET password_hash = ? WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, passwordHash);
            ps.setInt(2, userId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException exception) {
            System.out.println("The SQL query could not be prepared or executed.");
        }

        return false;
    }


}
