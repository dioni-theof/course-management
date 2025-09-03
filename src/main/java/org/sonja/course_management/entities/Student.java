package org.sonja.course_management.entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(name = "email_unique", columnNames = {"email"})

)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String surname;
    private String name;
    private int birthday;

    @Column(unique = true)
    private String email;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<CourseRegistration> registrations;

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<CourseRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<CourseRegistration> registrations) {
        this.registrations = registrations;
    }

}
