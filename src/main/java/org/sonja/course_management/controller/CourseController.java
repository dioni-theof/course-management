package org.sonja.course_management.controller;

import org.sonja.course_management.entities.Course;
import org.sonja.course_management.models.CourseRequestAndResponse;
import org.sonja.course_management.models.SuccessResponse;
import org.sonja.course_management.models.UpdateCourseRequest;
import org.sonja.course_management.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/courses", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CourseRequestAndResponse> createCourse(@RequestBody CourseRequestAndResponse courseRequestAndResponse) {
        CourseRequestAndResponse newCourse = courseService.createCourse(courseRequestAndResponse);

        return ResponseEntity.created(URI.create("/courses")).body(newCourse);
    }

    @GetMapping("/courses/all")
    public ResponseEntity<Page<Course>> getAllCourses(@RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Course> result = courseService.findAll(PageRequest.of(page, size));

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "courses/{numberCourse}/findbynumber", produces = "application/json")
    public ResponseEntity<CourseRequestAndResponse> getCourses(@PathVariable int numberCourse) {
        CourseRequestAndResponse course = courseService.findbyNumber(numberCourse);

        return ResponseEntity.ok(course);
    }

    @PutMapping(value = "courses/{numberCourse}/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CourseRequestAndResponse> updateCourse(@PathVariable int numberCourse, @RequestBody UpdateCourseRequest updateCourseRequest) {
        CourseRequestAndResponse course = courseService.updateCourse(numberCourse, updateCourseRequest);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping(value = "courses/{numberCourse}/delete", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteCourse(@PathVariable int numberCourse) {
        SuccessResponse successResponse = courseService.deleteCourse(numberCourse);

        return ResponseEntity.ok(successResponse);
    }
}
