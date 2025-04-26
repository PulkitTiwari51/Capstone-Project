package payroll.gui;

import javax.swing.*;
import java.awt.*;

public class PayslipPanel extends JPanel {
    private JComboBox<String> employeeCombo;
    private JTextArea payslipArea;

    public PayslipPanel() {
        setLayout(new BorderLayout(5, 5));

        // Employee selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Employee:"));

        employeeCombo = new JComboBox<>();
        employeeCombo.addItem("John Doe");
        employeeCombo.addItem("Jane Smith");
        topPanel.add(employeeCombo);

        JButton generateBtn = new JButton("Generate Payslip");
        generateBtn.addActionListener(e -> generatePayslip());
        topPanel.add(generateBtn);

        add(topPanel, BorderLayout.NORTH);

        // Payslip display
        payslipArea = new JTextArea(15, 40);
        payslipArea.setEditable(false);
        add(new JScrollPane(payslipArea), BorderLayout.CENTER);
    }

    private void generatePayslip() {
        String selectedEmployee = (String) employeeCombo.getSelectedItem();
        payslipArea.setText("Payslip for " + selectedEmployee + "\n\n" +
                "Basic Salary: $5000.00\n" +
                "Tax Deduction: $500.00\n" +
                "Insurance: $200.00\n" +
                "Net Salary: $4300.00\n" +
                "Payment Date: 2023-05-31");
    }
}