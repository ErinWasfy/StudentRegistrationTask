package com.registry.studentregistration.service;

import com.registry.studentregistration.constant.ConstantStudent;
import com.registry.studentregistration.entity.Course;
import com.registry.studentregistration.entity.Student;
import com.registry.studentregistration.entity.dto.StudentDto;
import com.registry.studentregistration.exceptions.ResourceNotFoundException;
import com.registry.studentregistration.repository.CourseRepository;
import com.registry.studentregistration.repository.StudentRepository;
import com.registry.studentregistration.utils.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    StudentRepository studentRepository;
    CourseRepository courseRepository;

    public StudentService(){}
    @Autowired
    public StudentService(StudentRepository studentRepository,CourseRepository courseRepository)
    {
        this.studentRepository=studentRepository;
        this.courseRepository=courseRepository;
    }
    ///Register a course to a student
    public StudentDto register(int std, Course crse) {
      Student st=  courseRepository.findById(crse.getCourseId()).map(course ->
        {
            int student = std;
            if (student != 0l) {
                try {
                    boolean checkExistence=false;
                    Student stdnt = studentRepository.findById(std).orElseThrow(() -> new ResourceNotFoundException("This student is not found"));
                    for (Course c : stdnt.getRegisteredCourses()) {
                        if (c.getCourseName()!=null&&c.getCourseName().equals(crse.getCourseName()) || c.getLstOfStudents().size()>crse.maxNumPlaces ||( !c.getCourseName().contains(crse.courseSubject.name()))) {
                            checkExistence = true;
                        }
                    }
                    if (course != null && isStudentEligibleToJoinCourse(stdnt).equals(course.getCourseType())) {

                        course.addStudent(stdnt);
                        courseRepository.save(course);
                        return stdnt;
                    }
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }

            }
            return null;

        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
      String coursName="";
      for(Course course:st.getRegisteredCourses())
      {
          if(crse.getCourseName().equals(course.getCourseName()))
          {
              coursName=course.getCourseName();break;
          }
      }
      return new StudentDto(st.getFirstName(),st.getLastName(),coursName);
    }
    public StudentDto registerFromMemory(int std, Course crse) {
        boolean checkExistence=false;
        List<Student> student=loadedStudentData();
        Student currStudent=null;
        for(Student ss:student)
        {
            if(ss.getId()==std){
                currStudent=ss;
                break;
            }
        }
      try {
          if(currStudent!=null) {
              for (Course c : currStudent.getRegisteredCourses()) {
                  if (c.getCourseName()!=null&&c.getCourseName().equals(crse.getCourseName()) || c.getLstOfStudents().size()>crse.maxNumPlaces ||( !c.getCourseName().contains(crse.courseSubject.name()))) {
                      checkExistence = true;
                  }
              }
          }
           if ( checkExistence==false &&  isStudentEligibleToJoinCourse(currStudent).equals(crse.getCourseType())) {

                   return new StudentDto(currStudent.getFirstName(),currStudent.getLastName(),crse.getCourseName());
                    }
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }

               return null;
    }
    public List<Student> loadedStudentData()
    {
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Peter");
        student.setStudyYear(3);
        Student student1=new Student();
        student1.setFirstName("Mark");
        student1.setStudyYear(3);
        Student student2=new Student();
        student2.setFirstName("Martin");
        student2.setStudyYear(3);
        Student student3=new Student();
        student3.setFirstName("Elija");
        student3.setStudyYear(3);
        Student student4=new Student();
        student4.setFirstName("Elena");
        student4.setStudyYear(3);
        Course course=new Course();
        course.setCourseType(CourseType.BACHELOR);
        course.setCourseName("Information System");
        course.setMaxNumPlaces(2);
        Course course1=new Course();
        course1.setCourseType(CourseType.BACHELOR);
        course1.setCourseName("Chemistry Science");
        course1.setMaxNumPlaces(10);
        student.registerCourse(course1);
        student1.registerCourse(course);
        student2.registerCourse(course);
        student3.registerCourse(course);
        student4.registerCourse(course);
      course1.addStudent(student);
       // course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        course.addStudent(student4);
        List<Student> lstOfStudents=new ArrayList<>();
        lstOfStudents.add(student);
        lstOfStudents.add(student1);
        lstOfStudents.add(student2);
        lstOfStudents.add(student3);
        lstOfStudents.add(student4);
        return  lstOfStudents;
    }
    public Course loadedData() {
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Peter");
        student.setStudyYear(3);
        Student student1=new Student();
        student1.setFirstName("Mark");
        student1.setStudyYear(3);
        Student student2=new Student();
        student2.setFirstName("Martin");
        student2.setStudyYear(3);
        Student student3=new Student();
        student3.setFirstName("Elija");
        student3.setStudyYear(3);
        Student student4=new Student();
        student4.setFirstName("Elena");
        student4.setStudyYear(3);
        Course course=new Course();
        course.setCourseType(CourseType.BACHELOR);
        course.setCourseName("Information System");
        course.setMaxNumPlaces(10);
        Course course1=new Course();
        course1.setCourseType(CourseType.BACHELOR);
        course1.setCourseName("Chemistry Science");
        course1.setMaxNumPlaces(10);
       student.registerCourse(course1);
        student1.registerCourse(course);
        student2.registerCourse(course);
        student3.registerCourse(course);
        student4.registerCourse(course);
        course1.addStudent(student);
        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        course.addStudent(student4);
        return course;
    }
    public List<Course> fetchListOfCoursesRegisteredByStudent(int id)
    {
        return (List<Course>) studentRepository.findById(id).get().getRegisteredCourses();
    }

      private CourseType isStudentEligibleToJoinCourse(Student student) {
        if(student.getStudyYear()<= ConstantStudent.STUDENT_BACHELOR_MAX_STUDY_YEAR)return CourseType.BACHELOR;
        return CourseType.MASTER;
      }

}
