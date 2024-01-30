package com.studentportal.controller;

import com.studentportal.client.request.StudentRequest;
import com.studentportal.model.Student;
import com.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.findStudentByStudentId(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody StudentRequest request) {
        return studentService.addStudent(request);
    }
}
