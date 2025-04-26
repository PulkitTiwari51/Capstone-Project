package payroll.database;

import payroll.model.Deduction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeductionDAO {
    public boolean addDeduction(Deduction deduction) throws SQLException {
        String sql = "INSERT INTO deductions (salary_id, tax, insurance, other_deductions) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, deduction.getSalaryId());
            pst.setDouble(2, deduction.getTax());
            pst.setDouble(3, deduction.getInsurance());
            pst.setDouble(4, deduction.getOtherDeductions());

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        deduction.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<Deduction> getDeductionsForSalary(int salaryId) throws SQLException {
        List<Deduction> deductions = new ArrayList<>();
        String sql = "SELECT * FROM deductions WHERE salary_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, salaryId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Deduction deduction = new Deduction();
                    deduction.setId(rs.getInt("deduction_id"));
                    deduction.setSalaryId(rs.getInt("salary_id"));
                    deduction.setTax(rs.getDouble("tax"));
                    deduction.setInsurance(rs.getDouble("insurance"));
                    deduction.setOtherDeductions(rs.getDouble("other_deductions"));
                    deductions.add(deduction);
                }
            }
        }
        return deductions;
    }

    public boolean updateDeduction(Deduction deduction) throws SQLException {
        String sql = "UPDATE deductions SET tax = ?, insurance = ?, other_deductions = ? WHERE deduction_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDouble(1, deduction.getTax());
            pst.setDouble(2, deduction.getInsurance());
            pst.setDouble(3, deduction.getOtherDeductions());
            pst.setInt(4, deduction.getId());

            return pst.executeUpdate() > 0;
        }
    }

    public boolean deleteDeduction(int deductionId) throws SQLException {
        String sql = "DELETE FROM deductions WHERE deduction_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, deductionId);
            return pst.executeUpdate() > 0;
        }
    }
}