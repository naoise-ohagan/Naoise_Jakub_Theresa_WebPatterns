package repositories;

import dtos.UserDto;

import java.util.Optional;

/**
 * Provides users use case for the application.
 *
 * @author Jakub
 */
public interface UserRepository {

    /**
     * Registers a new user account.
     *
     * @param username username used to log in
     * @param firstName users first name
     * @param surname users surname
     * @param email users email address
     * @param password plaintext password supplied by the user
     * @return true if the user was registered, otherwise false
     */
    boolean register(String username, String firstName, String surname, String email, String password);


    /**
     * Logs a user in using their username and password.
     *
     * @param username username to search for
     * @param password plaintext password supplied by the user
     * @return the logged-in users data, or an empty result if the credentials are invalid
     */
    Optional<UserDto> login(String username, String password);

    /**
     * Changes the password for a user account.
     *
     * @param username username of the account being updated
     * @param currentPassword accounts current plaintext password
     * @param newPassword new plaintext password
     * @return true if the password was changed, otherwise false
     */
    boolean changePassword(String username, String currentPassword, String newPassword);

    /**
     * Closes the database connection used by the repository.
     */
    void closeConnection();
}
