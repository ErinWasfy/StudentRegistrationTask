package com.registry.studentregistration.service;

import com.registry.studentregistration.entity.Course;
import com.registry.studentregistration.entity.Student;
import com.registry.studentregistration.utils.CourseSubject;
import com.registry.studentregistration.utils.CourseType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


public class StudentServiceTest {

 StudentService studentService=new StudentService();
    /*
     Check num of places in the course. if there is a free place, student will be able to register this course
     */
    @Test
    void shouldReturnNullWhenRegisterWithNoFreeSeatsInCourse() {
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Peter");
        student.setStudyYear(3);
        Course course=new Course();
        course.setCourseName("Information System");
        course.setCourseType(CourseType.BACHELOR);
        course.setCourseSubject(CourseSubject.INFORMATICS);
        course.setMaxNumPlaces(0);
        student.registerCourse(course);
        course.addStudent(student);
       assertEquals(studentService.registerFromMemory(student.getId(),course),null);
    }
    /*
     Students arent allowed to registered to the same coure one more time.
     */
    @Test
    void shouldReturnNullWhenRejoinSameCourse() {
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Peter");
        student.setStudyYear(3);
        Course course=new Course();
        course.setCourseName("Information System");
        course.setCourseType(CourseType.BACHELOR);
        course.setCourseSubject(CourseSubject.INFORMATICS);
        course.setMaxNumPlaces(10);
        student.registerCourse(course);
        course.addStudent(student);
        assertEquals(studentService.registerFromMemory(student.getId(),course),null);
    }

    /*
    Students arent allowed to register to different course subject
    */
    @Test
    void shouldReturnNullWhenJoinDifferentCourseSubject() {
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Peter");
        student.setStudyYear(3);
        Course course=new Course();
        course.setCourseName("Criminal Law");
        course.setCourseType(CourseType.BACHELOR);
        course.setCourseSubject(CourseSubject.LAW);
        course.setMaxNumPlaces(10);
        student.registerCourse(course);
        course.addStudent(student);
        assertEquals(studentService.registerFromMemory(student.getId(),course),null);
    }
}