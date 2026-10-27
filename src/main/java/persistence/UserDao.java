package persistence;

import entities.User;

import java.sql.SQLException;

/**
 * Provides database operations for users.
 *
 * @author Jakub
 */
public interface UserDao {

    /**
     * Adds a new user to the database.
     *
     * @param user user data to add
     * @return true if one user was added, otherwise false
     * @throws SQLException if the database operation fails
     */
    boolean addUser(User user) throws SQLException;

    /**
     * Finds a user by their username.
     *
     * @param username username to search for
     * @return the matching user, or null if the user does not exist
     * @throws SQLException if the database operation fails
     */
    User findByUsername(String username) throws SQLException;

    /**
     * Updates the stored password hash for a user.
     *
     * @param userId ID of the user to update
     * @param passwordHash new BCrypt password hash
     * @return true if one user's password was updated, otherwise false
     * @throws SQLException if the database operation fails
     */
    boolean updatePasswordHash(int userId, String passwordHash) throws SQLException;

    /**
     * Closes the database connection used by the DAO.
     */
    void closeConnection();
}
