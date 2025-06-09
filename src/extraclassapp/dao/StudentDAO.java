/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extraclassapp.dao;

/**
 *
 * @author kagis
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import extraclassapp.model.Student;

public class StudentDAO {
    private Connection connection;
    
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Students (student_id, initials, surname) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getInitials());
            stmt.setString(3, student.getSurname());
            stmt.executeUpdate();
        }
    }
    
    public boolean deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM Students WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String initials = rs.getString("initials");
                String surname = rs.getString("surname");
                students.add(new Student(id, initials, surname));
            }
        }
        return students;
    }

}
