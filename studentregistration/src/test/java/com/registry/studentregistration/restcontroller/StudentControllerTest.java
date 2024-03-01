package com.registry.studentregistration.restcontroller;

import com.registry.studentregistration.entity.Course;
import com.registry.studentregistration.entity.Student;
import com.registry.studentregistration.entity.dto.StudentDto;
import com.registry.studentregistration.utils.CourseSubject;
import com.registry.studentregistration.utils.CourseType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest extends AbstractControllerTest {



    @Test
    void fetchStudentDataById() throws Exception {
        Student student=new Student();
        student.setFirstName("Eric");
        student.setLastName("Mark");
        student.setStudyYear(3);
       //////Declare course
        Course course=new Course();
        course.setCourseName("Criminal Law");
        course.setCourseType(CourseType.BACHELOR);
        course.lstOfStudents.add(student);

        when(studentService.fetchListOfCoursesRegisteredByStudent(student.getId())).thenReturn((List<Course>) course);
        mockMvc.perform(get("/std/1").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON)).andExpect(jsonPath("$.courseName").value("Criminal Law"));

    }

    @Test
    void return201WhenRegisterCourseToStudent() throws Exception {
        Student student=new Student();
        student.setFirstName("Eric");
        student.setLastName("Mark");
        student.setStudyYear(3);
        Course course=new Course();
        course.setCourseName("Information System");
        course.setCourseType(CourseType.BACHELOR);
        student.registerCourse(course);
        course.addStudent(student);
        StudentDto studentDTO=new StudentDto("Eric","Mark",course.getCourseName());
       when(studentService.register(anyInt(),any(Course.class))).thenReturn(studentDTO);
        String stdBody = "{\"firstName\":\"Eric\", \"secondName\":\"Mark\",\"registeredCourse\":\"'"+course.getCourseName()+"'\"}";//,\"registeredCourse\":\"Information System\"}";

        mockMvc.perform(post("/std/1/register").contentType(MediaType.APPLICATION_JSON).content(stdBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON))

              // .andExpect(jsonPath("$.secondName").value("Mark1"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Eric"))
               .andExpect(jsonPath("$.registeredCourse").value("Information System"))
        ;

    }
}