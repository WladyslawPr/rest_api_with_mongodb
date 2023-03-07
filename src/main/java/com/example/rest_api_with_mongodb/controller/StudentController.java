package com.example.rest_api_with_mongodb.controller;

import com.example.rest_api_with_mongodb.model.Student;
import com.example.rest_api_with_mongodb.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }
}
