package org.sonja.course_management.controller;

import org.sonja.course_management.models.StudentRequest;
import org.sonja.course_management.models.SuccessResponse;
import org.sonja.course_management.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> createStudent(@RequestBody StudentRequest studentRequest) {
        StudentRequest newStudent = studentService.createStudent(studentRequest);

        return ResponseEntity.created(URI.create("/api/students/" + newStudent.email() + "/all-course")).build();
    }

    @GetMapping(value = "/{email}/all-course", produces = "application/json")
    public ResponseEntity<StudentRequest> courseOfStudent(@PathVariable String email) {
        StudentRequest courseOfStudent = studentService.findStudentWithCourses(email);

        return ResponseEntity.ok(courseOfStudent);
    }

    @DeleteMapping(value = "/{email}/delete-student", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteStudent(@PathVariable String email) {
        SuccessResponse deletedStudent = studentService.deleteStudent(email);

        return ResponseEntity.ok(deletedStudent);
    }

    @PutMapping(value = "/{email}/update-student", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentRequest> updateStudent(@PathVariable String email, StudentRequest studentRequest) {
        StudentRequest student = studentService.updateStudent(email, studentRequest);

        return ResponseEntity.ok(student);
    }
}

