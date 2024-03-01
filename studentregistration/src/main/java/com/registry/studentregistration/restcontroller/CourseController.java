package com.registry.studentregistration.restcontroller;

import com.registry.studentregistration.entity.Student;
import com.registry.studentregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses-centre")
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}/course")
    public ResponseEntity<List<Student>> fetchStudentsByCourseId(@PathVariable("id")int courseId)
    {
        List<Student> students=courseService.fetchAllStudentsByCourseId(courseId);
        if(students!=null)
        {
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
