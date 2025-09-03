package org.sonja.course_management.service;

import org.sonja.course_management.entities.Course;
import org.sonja.course_management.exeptions.CourseNotFoundException;
import org.sonja.course_management.mapping.Mappings;
import org.sonja.course_management.models.CourseRequestAndResponse;
import org.sonja.course_management.models.SuccessResponse;
import org.sonja.course_management.models.UpdateCourseRequest;
import org.sonja.course_management.repo.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseRequestAndResponse createCourse(CourseRequestAndResponse courseRequestAndResponse) {
        Course course = Mappings.mappingCourseRequestAndResponseToCourse(courseRequestAndResponse);
        Course newCourse = courseRepository.save(course);

        return Mappings.mappingCourseToCourseRequestAndResponse(newCourse);
    }

    public Page<Course> findAll(PageRequest pageRequest) {
        return courseRepository.findAll( pageRequest);
    }

    public CourseRequestAndResponse findbyNumber(int numberCourse) {
        Course course = courseRepository.findCourseByNumberCourse(numberCourse).orElseThrow(
                ()-> new CourseNotFoundException(numberCourse)
        );

        return Mappings.mappingCourseToCourseRequestAndResponse(course);
    }

    public CourseRequestAndResponse updateCourse(int numberCourse, UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findCourseByNumberCourse(numberCourse).orElseThrow(
                ()->new CourseNotFoundException(numberCourse)
        );
        String nameCourse = Mappings.mappingUpdateCourseRequestToCourseName(updateCourseRequest);
        course.setNameCourse(nameCourse);
        courseRepository.save(course);

        return Mappings.mappingCourseToCourseRequestAndResponse(course);
    }

    @Transactional
    public SuccessResponse deleteCourse(int numberCourse) {
        Course deletedcourse = courseRepository.findCourseByNumberCourse(numberCourse).orElseThrow(
                ()->new CourseNotFoundException(numberCourse)
        );
        courseRepository.deleteCourseByNumberCourse(deletedcourse.getNumberCourse());

        return new SuccessResponse("The Course " +numberCourse+ "is deleted");
    }
}
