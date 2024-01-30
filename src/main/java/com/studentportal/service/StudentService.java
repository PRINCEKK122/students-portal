package com.studentportal.service;

import com.studentportal.client.request.StudentRequest;
import com.studentportal.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(StudentRequest student);
    Student findStudentByStudentId(String studentId);

    List<Student> getAllStudents();
}
