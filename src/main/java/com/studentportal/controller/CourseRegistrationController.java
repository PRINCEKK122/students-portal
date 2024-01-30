package com.studentportal.controller;

import com.studentportal.client.response.StudentCourseResponse;
import com.studentportal.model.CourseRegistration;
import com.studentportal.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CourseRegistrationController {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @PostMapping("/registration/{courseId}/{studentId}")
    public CourseRegistration registerCourse(@PathVariable String courseId, @PathVariable String studentId) {
        return courseRegistrationService.registerCourse(courseId, studentId);
    }

    @GetMapping("/students/{studentId}/courses")
    public StudentCourseResponse findStudentByStudentIdAndCourses(@PathVariable String studentId) {
        return courseRegistrationService.findStudentByIdAndCourses(studentId);
    }
}
