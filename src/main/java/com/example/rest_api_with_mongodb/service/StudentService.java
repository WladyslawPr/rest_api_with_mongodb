package com.example.rest_api_with_mongodb.service;

import com.example.rest_api_with_mongodb.model.Student;
import com.example.rest_api_with_mongodb.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents () {
        return studentRepository.findAll();
    }
}
