package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new ApiException("Student roll number already exists");
        }

        if (request.getYear() < 1 || request.getYear() > 5) {
            throw new ApiException("Invalid year value");
        }

        Student student = Student.builder()
                .rollNumber(request.getRollNumber())
                .name(request.getName())
                .department(request.getDepartment())
                .year(request.getYear())
                .build();

        Student saved = studentRepository.save(student);

        return mapToResponse(saved);
    }

    @Override
    public StudentResponse getStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found"));

        return mapToResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found"));

        student.setName(request.getName());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());

        Student updated = studentRepository.save(student);

        return mapToResponse(updated);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student not found"));

        studentRepository.delete(student);
    }

    @Override
    public StudentResponse getByRollNumber(String rollNumber) {

        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new ApiException("Student not found"));

        return mapToResponse(student);
    }

    private StudentResponse mapToResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .rollNumber(student.getRollNumber())
                .name(student.getName())
                .department(student.getDepartment())
                .year(student.getYear())
                .build();
    }
}