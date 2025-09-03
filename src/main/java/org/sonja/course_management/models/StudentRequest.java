package org.sonja.course_management.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StudentRequest(String name,
                             String surname,
                             int birthday,
                             String email,
                             Set<CourseRequestAndResponse> infoCourses) {

    public StudentRequest(String name, String surname, int birthday, String email) {
        this(name, surname, birthday, email, Set.of());
    }

}
