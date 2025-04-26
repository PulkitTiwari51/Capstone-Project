package payroll.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpPanel extends JPanel {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private Runnable onBackToLogin;
    private Runnable onSignUpSuccess;

    public SignUpPanel(Runnable onBackToLogin, Runnable onSignUpSuccess) {
        this.onBackToLogin = onBackToLogin;
        this.onSignUpSuccess = onSignUpSuccess;

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

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        add(confirmPasswordField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> onBackToLogin.run());
        buttonPanel.add(backButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this::performSignUp);
        buttonPanel.add(signUpButton);

        add(buttonPanel, gbc);
    }

    private void performSignUp(ActionEvent e) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validation
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AuthService authService = new AuthService();
        boolean success = authService.registerUser(username, password, email);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            onSignUpSuccess.run();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Username may already exist.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}