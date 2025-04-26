package payroll.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Runnable onLoginSuccess;
    private Runnable onSignUpClick;

    public LoginPanel(AuthService authService, Runnable onLoginSuccess, Runnable onSignUpClick) {
        this.onLoginSuccess = onLoginSuccess;
        this.onSignUpClick = onSignUpClick;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::performLogin);
        add(loginButton, gbc);

        // Sign Up Button
        gbc.gridy = 3;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> onSignUpClick.run());
        add(signUpButton, gbc);
    }

    private void performLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        AuthService authService = new AuthService();
        User user = authService.login(username, password);

        if (user != null) {
            onLoginSuccess.run();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}