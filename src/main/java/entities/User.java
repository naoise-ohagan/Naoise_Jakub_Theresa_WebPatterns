package entities;

import java.util.Objects;

/**
 * Represents a user stored in the users database table.
 *
 * @param userId unique ID of the user
 * @param username username used to log in
 * @param firstName user's first name
 * @param surname user's surname
 * @param email user's email address
 * @param passwordHash BCrypt hash of the user's password
 * @author Jakub
 */
public record User(int userId, String username, String firstName, String surname, String email, String passwordHash) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}
