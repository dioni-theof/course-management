package org.sonja.course_management.mapping;

import org.sonja.course_management.entities.Course;
import org.sonja.course_management.entities.CourseRegistration;
import org.sonja.course_management.entities.Student;
import org.sonja.course_management.models.CourseRequestAndResponse;
import org.sonja.course_management.models.StudentRequest;
import org.sonja.course_management.models.UpdateCourseRequest;

import java.util.Set;

public class Mappings {
    public static Course mappingCourseRequestAndResponseToCourse(CourseRequestAndResponse courseRequestAndResponse) {
        Course course = new Course();
        course.setNumberCourse(courseRequestAndResponse.numberCourse());
        course.setNameCourse(courseRequestAndResponse.nameCourse());

        return course;
    }

    public static CourseRequestAndResponse mappingCourseToCourseRequestAndResponse(Course course) {
        return new CourseRequestAndResponse(course.getNumberCourse(), course.getNameCourse());
    }

    public static CourseRequestAndResponse mappingCourseToCourseRequestAndResponse(Course course, CourseRegistration courseRegistration) {
        return new CourseRequestAndResponse(course.getNumberCourse(), course.getNameCourse(), courseRegistration.getSemester(), courseRegistration.getRegistrationDate());
    }

    public static Student mappingStudentRequestToStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setSurname(studentRequest.surname());
        student.setBirthday(studentRequest.birthday());
        student.setEmail(studentRequest.email());

        return student;
    }

    public static StudentRequest mappingStudentToStudentRequest(Student student, Set<CourseRequestAndResponse> newCourseRequestAndResponse) {
        return new StudentRequest(student.getName(), student.getSurname(), student.getBirthday(), student.getEmail(), newCourseRequestAndResponse);
    }

    public static String mappingUpdateCourseRequestToCourseName(UpdateCourseRequest updateCourseRequest) {
        return updateCourseRequest.nameCourse();
    }

    public static StudentRequest mappingStudentToStudentRequest(Student student) {
        return new StudentRequest(student.getName(), student.getSurname(), student.getBirthday(), student.getEmail());
    }
}
