package org.sonja.course_management.exeptions;

public class CourseAllReadyExistException extends RuntimeException {

    public CourseAllReadyExistException(int numberCourse) {
        super("The Lesson  has also created by lesson: " +numberCourse );
    }

}

