
package extraclassapp.controller;

/**
 *
 * @author kagiso aphane
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import extraclassapp.dao.PaymentDAO;
import extraclassapp.dao.StudentDAO;
import extraclassapp.model.Student;
import extraclassapp.model.Payment;
import extraclassapp.util.DatabaseConnection;
import extraclassapp.view.ConsoleView;

public class StudentPaymentController {
    private StudentDAO studentDAO;
    private PaymentDAO paymentDAO;
    private ConsoleView view;
    
    public StudentPaymentController() {
        view = new ConsoleView();
        try {
            Connection connection = DatabaseConnection.getConnection();
            studentDAO = new StudentDAO(connection);
            paymentDAO = new PaymentDAO(connection);
            view.displayMessage("Connected to the database.");
        } catch (SQLException e) {
            view.displayMessage("Error connecting to the database: " + e.getMessage());
            System.exit(1);
        }
    }
    
    public void run() {
        while (true) {
            int choice = view.displayMenuAndGetChoice();
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        makePayment();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        displayAllStudents();
                        break;
                    case 5:
                        showPaymentHistory();
                        break;
                    case 6:
                        showTotalAmountInMonth();
                        break;
                    case 7:
                        view.displayMessage("Exiting...");
                        return;
                    default:
                        view.displayMessage("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                view.displayMessage("Database error: " + e.getMessage());
            }
        }
    }
    
    private void addStudent() throws SQLException {
        int studentId = view.getStudentId();
        String initials = view.getInitials();
        String surname = view.getSurname();
        
        Student student = new Student(studentId, initials, surname);
        studentDAO.addStudent(student);
        view.displayMessage("Student added successfully.");
    }
    
    private void makePayment() throws SQLException {
        int studentId = view.getStudentId();
        double amount = view.getPaymentAmount();
        paymentDAO.makePayment(studentId, amount);
        view.displayMessage("Payment made successfully.");
    }
    
    private void deleteStudent() throws SQLException {
        int studentId = view.getStudentId();
        boolean deleted = studentDAO.deleteStudent(studentId);
        if (deleted) {
            view.displayMessage("Student deleted successfully.");
        } else {
            view.displayMessage("Student not found.");
        }
    }
    
    private void displayAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        view.displayStudents(students);
    }
    
    private void showPaymentHistory() throws SQLException {
        int studentId = view.getStudentId();
        List<Payment> payments = paymentDAO.getPaymentHistory(studentId);
        view.displayPayments(payments);
    }
    
    private void showTotalAmountInMonth() throws SQLException {
        int year = view.getYear();
        int month = view.getMonth();
        double total = paymentDAO.getTotalAmountInMonth(year, month);
        view.displayTotalAmount(year, month, total);
    }

}
