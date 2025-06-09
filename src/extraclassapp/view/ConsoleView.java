
package extraclassapp.view;

/**
 *
 * @author kagiso Aphane
 */

import java.util.List;
import java.util.Scanner;
import extraclassapp.model.Student;
import extraclassapp.model.Payment;

public class ConsoleView {
     private Scanner scanner = new Scanner(System.in);
    
    public int displayMenuAndGetChoice() {
        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("1. Add Student");
        System.out.println("2. Make Payment");
        System.out.println("3. Delete Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Show Payment History");
        System.out.println("6. Show Total Amount Made in a Month");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }
    
    public int getStudentId() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return id;
    }
    
    public String getInitials() {
        System.out.print("Enter initials: ");
        return scanner.nextLine();
    }
    
    public String getSurname() {
        System.out.print("Enter surname: ");
        return scanner.nextLine();
    }
    
    public double getPaymentAmount() {
        System.out.print("Enter payment amount: ");
        double amt = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return amt;
    }
    
    public int getYear() {
        System.out.print("Enter year (YYYY): ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return year;
    }
    
    public int getMonth() {
        System.out.print("Enter month (MM): ");
        int month = scanner.nextInt();
        scanner.nextLine();
        return month;
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayStudents(List<Student> students) {
        System.out.println("Student ID | Initials | Surname");
        System.out.println("-------------------------------");
        for (Student s : students) {
            System.out.printf("%10d | %8s | %s%n", s.getStudentId(), s.getInitials(), s.getSurname());
        }
    }
    
    public void displayPayments(List<Payment> payments) {
        System.out.println("Payment ID | Payment Date  | Amount");
        System.out.println("------------------------------------");
        for (Payment p : payments) {
            System.out.printf("%10d | %12s | %.2f%n", 
                              p.getPaymentId(), 
                              p.getPaymentDate().toString(), 
                              p.getAmount());
        }
    }
    
    public void displayTotalAmount(int year, int month, double total) {
        System.out.printf("Total amount made in %d-%02d: %.2f%n", year, month, total);
    }

}
