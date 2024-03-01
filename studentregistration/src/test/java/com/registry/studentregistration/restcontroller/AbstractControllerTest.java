package com.registry.studentregistration.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registry.studentregistration.service.CourseService;
import com.registry.studentregistration.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @MockBean
    CourseService courseService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(studentService,courseService);

    }
}


