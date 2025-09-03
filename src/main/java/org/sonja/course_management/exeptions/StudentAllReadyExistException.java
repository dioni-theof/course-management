package org.sonja.course_management.exeptions;

public class StudentAllReadyExistException extends RuntimeException {


    public StudentAllReadyExistException(String email) {
        super("Student with email: " + email + " is already register! ");
    }
}
