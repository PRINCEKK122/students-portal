package com.studentportal.client.response;

import com.studentportal.model.Course;
import com.studentportal.model.Student;

import java.util.List;

public class StudentCourseResponse {
    private Student student;
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
