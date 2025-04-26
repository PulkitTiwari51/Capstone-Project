package payroll.database;

import payroll.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public boolean addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (name, position, join_date, basic_salary) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, emp.getName());
            pst.setString(2, emp.getPosition());
            pst.setString(3, emp.getJoinDate());
            pst.setDouble(4, emp.getBasicSalary());

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        emp.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection con = DatabaseConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setPosition(rs.getString("position"));
                emp.setJoinDate(rs.getString("join_date"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                employees.add(emp);
            }
        }
        return employees;
    }
}