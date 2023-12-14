package DaysCare.Immunization;

import DaysCare.Person.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * RegistrationRecord class to manage student's registration details.
 */
public class RegistrationRecord {
    private Student student;
    private LocalDate lastRegistrationDate;
    private LocalDate nextRegistrationDate;
    private String status; // "Registered" or "Not Registered"

    // Constructor
    public RegistrationRecord(Student student, String lastRegDate, String status) {
        this.student = student;
        this.status = status;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        this.lastRegistrationDate = LocalDate.parse(lastRegDate, formatter);
        this.nextRegistrationDate = this.lastRegistrationDate.plusYears(1); // assuming next registration is one year later
    }

    // Method to update the registration status and dates
    public void updateRegistration(String newLastRegDate, String newStatus) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        this.lastRegistrationDate = LocalDate.parse(newLastRegDate, formatter);
        this.nextRegistrationDate = this.lastRegistrationDate.plusYears(1);
        this.status = newStatus;

        // Here you can implement logic to save these changes to a database or file
    }

    // Getters
    public String getLastRegistrationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return this.lastRegistrationDate.format(formatter);
    }

    public String getNextRegistrationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return this.nextRegistrationDate.format(formatter);
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

}
