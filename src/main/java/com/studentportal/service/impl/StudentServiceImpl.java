package com.studentportal.service.impl;

import com.studentportal.client.request.StudentRequest;
import com.studentportal.exception.StudentNotFoundException;
import com.studentportal.model.Student;
import com.studentportal.repository.StudentRepository;
import com.studentportal.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(StudentRequest request) {
        return studentRepository.save(buildStudent(request));
    }

    @Override
    public Student findStudentByStudentId(String studentId) {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    private Student buildStudent(StudentRequest request) {
        Student newStudent = new Student();
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        request.setStudentId(uuid);
        BeanUtils.copyProperties(request, newStudent);
//        newStudent.setStudentId(uuid);
        return newStudent;
    }
}
