package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Hashes and verifies user passwords using BCrypt.
 *
 * @author Jakub
 */
public class PasswordHasher {
    private static final int WORKLOAD = 12;

    private PasswordHasher() {
    }

    /**
     * Creates a BCrypt hash from a plaintext password.
     *
     * @param plaintext password to hash
     * @return BCrypt hash of the supplied password
     * @throws IllegalArgumentException if the supplied password is null or empty
     */
    public static String hashPassword(String plaintext) {
        if (plaintext == null || plaintext.isEmpty()) {
            throw new IllegalArgumentException("Cannot hash a null or empty password.");
        }

        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plaintext, salt);
    }

    /**
     * Checks whether a plaintext password matches a stored BCrypt hash.
     *
     * @param plaintext password to verify
     * @param hashedPassword stored BCrypt hash
     * @return true if the password matches the hash, otherwise false
     */
    public static boolean verifyPassword(String plaintext, String hashedPassword) {
        if (plaintext == null || hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }

        return BCrypt.checkpw(plaintext, hashedPassword);
    }
}
