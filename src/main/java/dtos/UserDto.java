package dtos;

/**
 * Stores user data transferred from the repository to the application.
 *
 * @param userId unique ID of the user
 * @param username username used to log in
 * @param firstName user's first name
 * @param surname user's surname
 * @param email user's email address
 * @author Jakub
 */
public record UserDto(int userId, String username, String firstName, String surname, String email) {
}
