package com.registry.studentregistration.restcontroller;

import com.registry.studentregistration.entity.Course;
import com.registry.studentregistration.entity.dto.StudentDto;
import com.registry.studentregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/std")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Course>> fetchStudentDataById(@PathVariable("id")int id)
    {
        List<Course> courseList=studentService.fetchListOfCoursesRegisteredByStudent(id);
        return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
    }
    @PostMapping("/{id}/register")
    public ResponseEntity<StudentDto> registerCourseToStudent(@PathVariable("id") int studentId, @RequestBody Course course)
    {
        return new ResponseEntity<StudentDto>(studentService.register(studentId,course),HttpStatus.CREATED);
    }

}
