package payroll.database;

import payroll.model.Salary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    public boolean addSalary(Salary salary) throws SQLException {
        String sql = "INSERT INTO salaries (employee_id, month_year, gross_salary, net_salary, payment_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, salary.getEmployeeId());
            pst.setString(2, salary.getMonthYear());
            pst.setDouble(3, salary.getGrossSalary());
            pst.setDouble(4, salary.getNetSalary());
            pst.setString(5, salary.getPaymentDate());

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        salary.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<Salary> getSalariesForEmployee(int employeeId) throws SQLException {
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM salaries WHERE employee_id = ? ORDER BY month_year DESC";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, employeeId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Salary salary = new Salary();
                    salary.setId(rs.getInt("salary_id"));
                    salary.setEmployeeId(rs.getInt("employee_id"));
                    salary.setMonthYear(rs.getString("month_year"));
                    salary.setGrossSalary(rs.getDouble("gross_salary"));
                    salary.setNetSalary(rs.getDouble("net_salary"));
                    salary.setPaymentDate(rs.getString("payment_date"));
                    salaries.add(salary);
                }
            }
        }
        return salaries;
    }

    public Salary getSalaryById(int salaryId) throws SQLException {
        String sql = "SELECT * FROM salaries WHERE salary_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, salaryId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Salary salary = new Salary();
                    salary.setId(rs.getInt("salary_id"));
                    salary.setEmployeeId(rs.getInt("employee_id"));
                    salary.setMonthYear(rs.getString("month_year"));
                    salary.setGrossSalary(rs.getDouble("gross_salary"));
                    salary.setNetSalary(rs.getDouble("net_salary"));
                    salary.setPaymentDate(rs.getString("payment_date"));
                    return salary;
                }
            }
        }
        return null;
    }

    public boolean updateSalary(Salary salary) throws SQLException {
        String sql = "UPDATE salaries SET month_year = ?, gross_salary = ?, net_salary = ?, " +
                "payment_date = ? WHERE salary_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, salary.getMonthYear());
            pst.setDouble(2, salary.getGrossSalary());
            pst.setDouble(3, salary.getNetSalary());
            pst.setString(4, salary.getPaymentDate());
            pst.setInt(5, salary.getId());

            return pst.executeUpdate() > 0;
        }
    }

    public boolean deleteSalary(int salaryId) throws SQLException {
        String sql = "DELETE FROM salaries WHERE salary_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, salaryId);
            return pst.executeUpdate() > 0;
        }
    }

    public double calculateNetSalary(double grossSalary, double tax, double insurance, double otherDeductions) {
        return grossSalary - (tax + insurance + otherDeductions);
    }
}