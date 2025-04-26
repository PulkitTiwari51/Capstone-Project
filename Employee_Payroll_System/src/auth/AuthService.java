package payroll.auth;

import payroll.database.UserDAO;
import payroll.model.User;
import java.sql.SQLException;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String username, String password, String email) {
        try {
            if (userDAO.usernameExists(username)) {
                return false;
            }
            User newUser = new User(username, password, email);
            return userDAO.createUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        try {
            return userDAO.authenticate(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}