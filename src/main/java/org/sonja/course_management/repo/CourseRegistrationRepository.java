package org.sonja.course_management.repo;

import org.sonja.course_management.entities.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    void deleteAllByStudent_Email(String studentEmail);
}

