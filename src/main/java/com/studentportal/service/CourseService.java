package com.studentportal.service;

import com.studentportal.client.request.CourseRequest;
import com.studentportal.model.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(CourseRequest request);
    Course getCourseByCourseId(String courseId);
    List<Course> getAllCourses();
}
