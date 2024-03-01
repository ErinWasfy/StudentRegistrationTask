package com.registry.studentregistration.entity;


import com.registry.studentregistration.utils.CourseSubject;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {

    @jakarta.persistence.Id
    @GeneratedValue
    public int Id;
    @ManyToMany(mappedBy = "lstOfStudents")
    public Set<Course> registeredCourses ;
    @Column(name = "firstname")
    public String firstName;
    @Column(name = "lastname")
    public String lastName;
    @Max(value = 5)
    @Min(value = 1)
    @Column(name = "studyyear")
    public int studyYear;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
    public void registerCourse(Course course)
    {
        if (registeredCourses==null)
            registeredCourses=new HashSet<>();
        registeredCourses.add(course);
        course.addStudent(this);
    }


    public Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(Set<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }
}
