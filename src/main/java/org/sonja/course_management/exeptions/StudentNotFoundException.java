package org.sonja.course_management.exeptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String email) {
        super("Student with email: " + email + " don't found ");
    }
}
