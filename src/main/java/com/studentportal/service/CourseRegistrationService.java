package com.studentportal.service;

import com.studentportal.client.response.StudentCourseResponse;
import com.studentportal.model.CourseRegistration;

public interface CourseRegistrationService {
    CourseRegistration registerCourse(String courseId, String studentId);
    StudentCourseResponse findStudentByIdAndCourses(String studentId);
}
