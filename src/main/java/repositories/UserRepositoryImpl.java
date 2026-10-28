package repositories;

import dtos.UserDto;
import entities.User;
import persistence.UserDao;
import utils.PasswordHasher;
import utils.PasswordPolicy;

import java.util.Optional;

/**
 * Implements the user account use-cases for the application.
 *
 * @author Jakub
 */
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Registers a new user after validating and hashing their password.
     *
     * @param username username used to log in
     * @param firstName users first name
     * @param surname users surname
     * @param email users email address
     * @param password plaintext password supplied by the user
     * @return true if the user was registered, otherwise false
     * @throws IllegalArgumentException if the password does not meet the password policy
     */
    @Override
    public boolean register(String username, String firstName, String surname, String email, String password) {
        String validationError = PasswordPolicy.getValidationError(password);
        if (!validationError.isEmpty()) {
            throw new IllegalArgumentException(validationError);
        }

        String passwordHash = PasswordHasher.hashPassword(password);
        return userDao.addUser(username, firstName, surname, email, passwordHash);
    }

    /**
     * Logs a user in using their username and password.
     *
     * @param username username to search for
     * @param password plaintext password supplied by the user
     * @return the logged-in user data, or an empty result if the credentials are invalid
     */
    @Override
    public Optional<UserDto> login(String username, String password) {
        User user = userDao.getUserByUsername(username);

        if (user == null) {
            return Optional.empty();
        }

        boolean passwordMatches = PasswordHasher.verifyPassword(password, user.passwordHash());
        if (!passwordMatches) {
            return Optional.empty();
        }

        UserDto userDto = new UserDto(
                user.userId(),
                user.username(),
                user.firstName(),
                user.surname(),
                user.email()
        );

        return Optional.of(userDto);
    }

    /**
     * Changes a users password after checking their current password and validating the new password.
     *
     * @param username username of the account being updated
     * @param currentPassword account current plaintext password
     * @param newPassword new plaintext password
     * @return true if the password was changed, otherwise false
     * @throws IllegalArgumentException if the new password does not meet the password policy
     */
    @Override
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        User user = userDao.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        boolean currentPasswordMatches = PasswordHasher.verifyPassword(currentPassword, user.passwordHash());
        if (!currentPasswordMatches) {
            return false;
        }

        String validationError = PasswordPolicy.getValidationError(newPassword);
        if (!validationError.isEmpty()) {
            throw new IllegalArgumentException(validationError);
        }

        boolean passwordIsUnchanged = PasswordHasher.verifyPassword(newPassword, user.passwordHash());
        if (passwordIsUnchanged) {
            throw new IllegalArgumentException("New password must be different from the current password.");
        }

        String newPasswordHash = PasswordHasher.hashPassword(newPassword);
        return userDao.updatePasswordHash(user.userId(), newPasswordHash);
    }

    /**
     * Closes the database connection used by the repository.
     */
    @Override
    public void closeConnection() {
        userDao.closeConnection();
    }
}
