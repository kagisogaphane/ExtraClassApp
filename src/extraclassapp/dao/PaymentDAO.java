/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extraclassapp.dao;

/**
 *
 * @author kagiso
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import extraclassapp.model.Payment;

public class PaymentDAO {
    private Connection connection;
    
    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void makePayment(int studentId, double amount) throws SQLException {
        String sql = "INSERT INTO Payments (student_id, payment_date, amount) VALUES (?, CURDATE(), ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        }
    }
    
    public List<Payment> getPaymentHistory(int studentId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int paymentId = rs.getInt("payment_id");
                    Date paymentDate = rs.getDate("payment_date");
                    double amt = rs.getDouble("amount");
                    payments.add(new Payment(paymentId, studentId, paymentDate, amt));
                }
            }
        }
        return payments;
    }
    
    public double getTotalAmountInMonth(int year, int month) throws SQLException {
        String sql = "SELECT SUM(amount) AS total_amount FROM Payments WHERE YEAR(payment_date) = ? AND MONTH(payment_date) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_amount");
                }
            }
        }
        return 0.0;
    }

}
