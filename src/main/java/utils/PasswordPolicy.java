package utils;

/**
 * Checks whether a password meets the password rules used by the application.
 *
 * @author Jakub
 */
public class PasswordPolicy {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 72;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{};:,.?/";

    private PasswordPolicy() {
    }

    /**
     * Checks that a password has a valid length and contains an uppercase letter,
     * a lowercase letter, a number and a special character.
     *
     * @param password password to check
     * @return true if the password meets all password rules, otherwise false
     */
    public static boolean isValid(String password) {
        String validationError = getValidationError(password);

        return validationError.isEmpty();
    }

    /**
     * Checks a password and returns the first password rule that it does not meet.
     *
     * @param password password to check
     * @return an error message, or an empty string if the password is valid
     */
    public static String getValidationError(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "Password must contain at least 8 characters.";
        }

        if (password.length() > MAX_PASSWORD_LENGTH) {
            return "Password cannot contain more than 72 characters.";
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(character)) {
                hasLowercase = true;
            } else if (Character.isDigit(character)) {
                hasNumber = true;
            } else if (SPECIAL_CHARACTERS.indexOf(character) >= 0) {
                hasSpecialCharacter = true;
            }
        }

        if (!hasUppercase) {
            return "Password must contain an uppercase letter.";
        }

        if (!hasLowercase) {
            return "Password must contain a lowercase letter.";
        }

        if (!hasNumber) {
            return "Password must contain a number.";
        }

        if (!hasSpecialCharacter) {
            return "Password must contain a special character.";
        }

        return "";
    }
}
