package org.sonja.course_management.service;

import org.sonja.course_management.entities.Course;
import org.sonja.course_management.entities.CourseRegistration;
import org.sonja.course_management.entities.Student;
import org.sonja.course_management.exeptions.CourseNotFoundException;
import org.sonja.course_management.exeptions.StudentAllReadyExistException;
import org.sonja.course_management.exeptions.StudentNotFoundException;
import org.sonja.course_management.mapping.Mappings;
import org.sonja.course_management.models.CourseRequestAndResponse;
import org.sonja.course_management.models.StudentRequest;
import org.sonja.course_management.models.SuccessResponse;
import org.sonja.course_management.repo.CourseRegistrationRepository;
import org.sonja.course_management.repo.CourseRepository;
import org.sonja.course_management.repo.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, CourseRegistrationRepository courseRegistrationRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    public StudentRequest createStudent(StudentRequest studentRequest) {
        Student student = Mappings.mappingStudentRequestToStudent(studentRequest);
        Student newStudent = saveStudent(student);

        if (studentRequest.infoCourses() == null || studentRequest.infoCourses().isEmpty()) {
            return Mappings.mappingStudentToStudentRequest(newStudent);
        }

        Set<CourseRequestAndResponse> newCourseRequestAndResponses = addCourseInStudent(student, studentRequest);

        return Mappings.mappingStudentToStudentRequest(newStudent, newCourseRequestAndResponses);
    }

    public Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception ex) {
            throw new StudentAllReadyExistException(student.getEmail());
        }
    }

    public Set<CourseRequestAndResponse> addCourseInStudent(Student student, StudentRequest studentRequest) {
        Set<CourseRequestAndResponse> newCourseRequestAndResponses = new HashSet<>();

        for (CourseRequestAndResponse infoCourse : studentRequest.infoCourses()) {
            Course course = courseRepository.findCourseByNumberCourse(infoCourse.numberCourse()).orElseThrow(
                    ()->new CourseNotFoundException(infoCourse.numberCourse())
            );
            CourseRegistration newCourseRegistration = new CourseRegistration();
            newCourseRegistration.setCourse(course);
            newCourseRegistration.setStudent(student);
            newCourseRegistration.setSemester(infoCourse.semester());
            CourseRegistration courseRegistration = saveCourseRegistration(newCourseRegistration);
            int numberCourse = courseRegistration.getCourse().getNumberCourse();
            String nameCourse = courseRegistration.getCourse().getNameCourse();
            int semesterCourse = courseRegistration.getSemester();
            Date dateRegistration = courseRegistration.getRegistrationDate();
            CourseRequestAndResponse newcr = new CourseRequestAndResponse(numberCourse, nameCourse, semesterCourse, dateRegistration);
            newCourseRequestAndResponses.add(newcr);
        }

        return newCourseRequestAndResponses;
    }

    public CourseRegistration saveCourseRegistration(CourseRegistration newCourseRegistration) {
        return courseRegistrationRepository.save(newCourseRegistration);
    }

    @Transactional
    public SuccessResponse deleteStudent(String email) {
        Student deletedStudent = studentRepository.findStudentByEmail(email).orElseThrow();
        courseRegistrationRepository.deleteAllByStudent_Email(email);
//        Set<CourseRegistration> deletedCourseRegistration = courseRegistrationRepository.findAllByStudentEmail(deletedStudent.getEmail());
//        for (CourseRegistration courseRegistration : deletedCourseRegistration) {
//            courseRegistrationRepository.deleteCourseRegistrationById(courseRegistration.getId());
//        }

        studentRepository.deleteStudentByEmail(deletedStudent.getEmail());

        return new SuccessResponse("Success deleted" + " " + deletedStudent.getEmail());
    }

    public StudentRequest updateStudent(String email, StudentRequest studentRequest) {
        Student updatedStudent = studentRepository.findStudentByEmail(email).orElseThrow(()->new StudentNotFoundException(email));
        if (studentRequest.name() != null) {
            updatedStudent.setName(studentRequest.name());
        }
        if (studentRequest.surname() != null) {
            updatedStudent.setSurname(studentRequest.surname());
        }
        if (studentRequest.birthday() != 0) {
            updatedStudent.setBirthday(studentRequest.birthday());
        }
        updatedStudent = saveStudent(updatedStudent);

        Set<CourseRequestAndResponse> newCourseRequestAndResponses = addCourseInStudent(updatedStudent, studentRequest);

        return Mappings.mappingStudentToStudentRequest(updatedStudent, newCourseRequestAndResponses);
    }

    public StudentRequest findStudentWithCourses(String email) {
        Student student = studentRepository.findStudentByEmail(email).orElseThrow(()->new StudentNotFoundException(email)

        );

        Set<CourseRegistration> registrations = student.getRegistrations();
        Set<CourseRequestAndResponse> courseRequestAndResponseSet = new HashSet<>();

        for (CourseRegistration courseRegistration : registrations) {
            Course course = courseRegistration.getCourse();
            CourseRequestAndResponse courseMapping = Mappings.mappingCourseToCourseRequestAndResponse(course, courseRegistration);
            courseRequestAndResponseSet.add(courseMapping);
        }

        return Mappings.mappingStudentToStudentRequest(student, courseRequestAndResponseSet);
    }
}
