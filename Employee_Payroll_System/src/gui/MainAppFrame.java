package payroll.gui;

import payroll.auth.AuthService;
import payroll.auth.LoginPanel;
import payroll.auth.SignUpPanel;

import javax.swing.*;
import java.awt.*;

public class MainAppFrame extends JFrame {
    private AuthService authService;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainAppFrame() {
        setTitle("Employee Payroll System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        authService = new AuthService();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels
        LoginPanel loginPanel = new LoginPanel(
                authService,
                this::showMainApplication,
                this::showSignUpPanel);

        SignUpPanel signUpPanel = new SignUpPanel(
                this::showLoginPanel,
                this::showLoginPanelAfterSignUp);

        // Add panels to card layout
        cardPanel.add(loginPanel, "login");
        cardPanel.add(signUpPanel, "signup");

        add(cardPanel);
        showLoginPanel();
    }

    private void showLoginPanel() {
        cardLayout.show(cardPanel, "login");
    }

    private void showSignUpPanel() {
        cardLayout.show(cardPanel, "signup");
    }

    private void showLoginPanelAfterSignUp() {
        showLoginPanel();
        JOptionPane.showMessageDialog(this, "Please login with your new credentials");
    }

    private void showMainApplication() {
        // Remove card panel and show main application
        remove(cardPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Data Entry", new DataEntryPanel());
        tabbedPane.addTab("Payslip View", new PayslipPanel());

        add(tabbedPane);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainAppFrame frame = new MainAppFrame();
            frame.setVisible(true);
        });
    }
}