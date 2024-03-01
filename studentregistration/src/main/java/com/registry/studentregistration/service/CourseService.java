package com.registry.studentregistration.service;

import com.registry.studentregistration.entity.Course;
import com.registry.studentregistration.entity.Student;
import com.registry.studentregistration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Student> fetchAllStudentsByCourseId(int courseId)
    {
        Optional<Course> currentCourse=Optional.of(courseRepository.findById(courseId).get());
        if(currentCourse.isPresent())
        {
            return currentCourse.get().getLstOfStudents().stream().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
