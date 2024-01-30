package com.studentportal.service.impl;

import com.studentportal.client.request.CourseRequest;
import com.studentportal.exception.CourseNotFoundException;
import com.studentportal.model.Course;
import com.studentportal.repository.CourseRepository;
import com.studentportal.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(CourseRequest request) {
        if (checkExistingCourseByName(request.getTitle())) {
            throw new RuntimeException("Course already exists!");
        }

        return courseRepository.save(buildCourse(request));
    }

    @Override
    public Course getCourseByCourseId(String courseId) {
        return courseRepository
                .findAll()
                .stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst()
                .orElseThrow(() ->  new CourseNotFoundException("Course not Found!"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    private Course buildCourse(CourseRequest request) {
        Course course = new Course();
        request.setCourseId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, course);
        return course;
    }

    private boolean checkExistingCourseByName(String courseName) {
        Optional<Course> course = courseRepository
                .findAll()
                .stream()
                .filter(c -> c.getTitle().equals(courseName))
                .findFirst();

        return course.isPresent();
    }
}
