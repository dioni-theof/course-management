package org.sonja.course_management.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CourseRequestAndResponse(int numberCourse,
                                       String nameCourse,
                                       Integer semester,
                                       Date registrationDate) {


    public CourseRequestAndResponse(int numberCourse, String nameCourse) {
        this(numberCourse, nameCourse, null, null);
    }


}
