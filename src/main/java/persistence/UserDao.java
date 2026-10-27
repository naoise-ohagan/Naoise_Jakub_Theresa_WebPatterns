package persistence;

import entities.User;

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
     */
    boolean addUser(User user);

    /**
     * Finds a user by their username.
     *
     * @param username username to search for
     * @return the matching user, or null if the user does not exist
     */
    User findByUsername(String username);

    /**
     * Updates the stored password hash for a user.
     *
     * @param userId ID of the user to update
     * @param passwordHash new BCrypt password hash
     * @return true if one user's password was updated, otherwise false
     */
    boolean updatePasswordHash(int userId, String passwordHash);

    /**
     * Closes the database connection used by the DAO.
     */
    void closeConnection();
}
