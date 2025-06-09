/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extraclassapp.model;

/**
 *
 * @author kagis
 */
public class Student {
    private int studentId;
    private String initials;
    private String surname;
    
    public Student(int studentId, String initials, String surname) {
        this.studentId = studentId;
        this.initials = initials;
        this.surname = surname;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public String getInitials() {
        return initials;
    }
    
    public String getSurname() {
        return surname;
    }

}
