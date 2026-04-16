public class User implements Comparable<User> {
    private final String username;
    private final String password;

    // Username regex
    private static final String USERNAME_REGEX =
            "^[A-Za-z0-9._\\-+%]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}$";

    // Password regex
    private static final String PASSWORD_ALLOWED_CHARS_REGEX =
            "^[A-Za-z0-9@#!+.,]+$";


    // Constructor for our class, validates the username and password and throws an exception if they do not fulfill the conditions
    public User(String username, String password) {
        validateUsername(username);
        validatePassword(password);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Validating the username using the regex we defined
    // Also throwing an exception if the username is too long
    private static void validateUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }

        if (username.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }

        if (!username.matches(USERNAME_REGEX)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
    }

    // Validating the password using the regex we defined
    // Also throwing an exception if the password is not in the length range we allow
    private static void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Please enter a valid password");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }

        if (password.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }

        if (!password.matches(PASSWORD_ALLOWED_CHARS_REGEX)) {
            throw new IllegalArgumentException("Please enter a valid password");
        }

        if (!containsLetter(password) || !containsDigit(password) || !containsSymbol(password)) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
    }

    // Helper function used to make sure the password contains a letter
    private static boolean containsLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Helper function used to make sure the password contains a digit
    private static boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Helper function used to make suer the password contains a symbol
    private static boolean containsSymbol(String s) {
        String symbols = "@#!+.,";
        for (int i = 0; i < s.length(); i++) {
            if (symbols.indexOf(s.charAt(i)) != -1) {
                return true;
            }
        }
        return false;
    }

    // A function from comparable, used to allow sorting using collections package
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    // To string function to make printing simpler
    @Override
    public String toString() {
        return username + " " + password;
    }
}