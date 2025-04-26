package payroll.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DataEntryPanel extends JPanel {
    private JTextField nameField, positionField, salaryField;
    private JButton saveButton;

    public DataEntryPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Position Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Position:"), gbc);

        gbc.gridx = 1;
        positionField = new JTextField(20);
        add(positionField, gbc);

        // Salary Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Basic Salary:"), gbc);

        gbc.gridx = 1;
        salaryField = new JTextField(20);
        add(salaryField, gbc);

        // Save Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        saveButton = new JButton("Save Employee");
        saveButton.addActionListener(this::saveEmployee);
        add(saveButton, gbc);
    }

    private void saveEmployee(ActionEvent e) {
        // Implement save functionality
        JOptionPane.showMessageDialog(this, "Employee saved successfully!");
    }
}