/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extraclassapp.model;

/**
 *
 * @author kagiso Aphane
 */
import java.sql.Date;
public class Payment {
    private int paymentId;
    private int studentId;
    private Date paymentDate;
    private double amount;
    
    public Payment(int paymentId, int studentId, Date paymentDate, double amount) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
    
    public int getPaymentId() {
        return paymentId;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public double getAmount() {
        return amount;
    }

}
