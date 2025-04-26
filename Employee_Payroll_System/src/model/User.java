package payroll.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    // Constructors
    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "user"; // default role
    }

    // Getters and Setters
    // Add similar to Employee class
}