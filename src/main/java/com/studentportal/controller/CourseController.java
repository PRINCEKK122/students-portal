package com.studentportal.controller;

import com.studentportal.client.request.CourseRequest;
import com.studentportal.model.Course;
import com.studentportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseByCourseId(id);
    }

    @PostMapping
    public Course addCourse(@RequestBody CourseRequest request) {
        return courseService.addCourse(request);
    }
}
