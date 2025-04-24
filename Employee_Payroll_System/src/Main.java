import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Data Entry", new DataEntryPanel());
        tabbedPane.addTab("Payslip View", new PayslipPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}