package org.sonja.course_management.exeptions;

public class CourseNotFoundException extends RuntimeException {


    public CourseNotFoundException(int numberCourse) {
        super("Course not found: " + numberCourse);
    }


}
