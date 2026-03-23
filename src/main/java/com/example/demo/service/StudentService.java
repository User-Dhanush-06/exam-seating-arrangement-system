package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudent(Long id);

    List<StudentResponse> getAllStudents();

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    StudentResponse getByRollNumber(String rollNumber);
}