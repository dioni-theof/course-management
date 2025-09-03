package org.sonja.course_management.repo;

import org.sonja.course_management.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findCourseByNumberCourse(int numberCourse);

    void deleteCourseByNumberCourse(int numberCourse);
}
