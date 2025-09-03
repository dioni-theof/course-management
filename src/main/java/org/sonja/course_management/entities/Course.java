package org.sonja.course_management.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private int numberCourse;
    private String nameCourse;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    Set<CourseRegistration> registrations;

    public Course() {
    }

    public Course(int numberCourse, String nameCourse) {
        this.numberCourse = numberCourse;
        this.nameCourse = nameCourse;
    }

    public int getNumberCourse() {
        return numberCourse;
    }

    public void setNumberCourse(int numberCourse) {
        this.numberCourse = numberCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public Set<CourseRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<CourseRegistration> registrations) {
        this.registrations = registrations;
    }
}
