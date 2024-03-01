package com.registry.studentregistration.entity;

import com.registry.studentregistration.utils.CourseSubject;
import com.registry.studentregistration.utils.CourseType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    public int courseId;
    public  String courseName;
    public CourseType courseType;
    public String courseTitle;
    public  int maxNumPlaces;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="student_course",joinColumns = {@JoinColumn(name="courseId")},inverseJoinColumns = {@JoinColumn(name = "Id")})
    public Set<Student> lstOfStudents;
    public CourseSubject courseSubject;
    public void addStudent(Student student)
    {
        if(lstOfStudents==null)
            lstOfStudents=new HashSet<>();
        lstOfStudents.add(student);
        student.registeredCourses.add(this);
    }

    public int getCourseId() {
        return courseId;
    }


    public CourseSubject getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(CourseSubject courseSubject) {
        this.courseSubject = courseSubject;
    }

    public Set<Student> getLstOfStudents() {
        return lstOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getMaxNumPlaces() {
        return maxNumPlaces;
    }

    public void setMaxNumPlaces(int maxNumPlaces) {
        this.maxNumPlaces = maxNumPlaces;
    }

}
