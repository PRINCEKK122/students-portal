package com.studentportal.service.impl;


import com.studentportal.client.response.StudentCourseResponse;
import com.studentportal.exception.CourseNotFoundException;
import com.studentportal.model.Course;
import com.studentportal.model.CourseRegistration;
import com.studentportal.model.Student;
import com.studentportal.repository.CourseRegistrationRepository;
import com.studentportal.repository.CourseRepository;
import com.studentportal.repository.StudentRepository;
import com.studentportal.service.CourseRegistrationService;
import com.studentportal.service.CourseService;
import com.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseRegistrationImpl implements CourseRegistrationService {
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Override
    public CourseRegistration registerCourse(String courseId, String studentId) {
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);

        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourseId(course.getCourseId());
        courseRegistration.setStudentId(student.getStudentId());

        return courseRegistrationRepository.save(courseRegistration);
    }

    @Override
    public StudentCourseResponse findStudentByIdAndCourses(String studentId) {
        Student student = studentService.findStudentByStudentId(studentId);
        StudentCourseResponse response = new StudentCourseResponse();
        List<Course> registeredCourses = new ArrayList<>();
        response.setStudent(student);
        List<String> studentRegisteredCourseIds = getStudentRegisteredCourseIds(studentId);

        for (String courseId : studentRegisteredCourseIds) {
            Course course = courseService.getCourseByCourseId(courseId);
            registeredCourses.add(course);
        }
        response.setCourses(registeredCourses);
        return response;
    }

    private List<String> getStudentRegisteredCourseIds(String studentId) {
        return courseRegistrationRepository
                .findAll()
                .stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .map(CourseRegistration::getCourseId)
                .toList();

    }

    private Course findCourseById(String id) {
        return courseService.getCourseByCourseId(id);
    }

    private Student findStudentById(String id) {
        return studentService.findStudentByStudentId(id);
    }
}
