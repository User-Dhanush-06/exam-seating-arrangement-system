package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ExamSessionService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionServiceImpl(
            ExamSessionRepository examSessionRepository,
            StudentRepository studentRepository) {

        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ExamSessionResponse createSession(ExamSessionRequest request) {

        if (request.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }

        ExamSession session = ExamSession.builder()
                .courseCode(request.getCourseCode())
                .examDate(request.getExamDate())
                .examTime(request.getExamTime())
                .build();

        if (request.getStudentIds() != null) {

            Set<Student> students = request.getStudentIds()
                    .stream()
                    .map(id -> studentRepository.findById(id)
                            .orElseThrow(() -> new ApiException("Student not found")))
                    .collect(Collectors.toSet());

            session.setStudents(students);
        }

        ExamSession saved = examSessionRepository.save(session);

        return mapToResponse(saved);
    }

    @Override
    public ExamSessionResponse getSession(Long id) {

        ExamSession session = examSessionRepository.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));

        return mapToResponse(session);
    }

    @Override
    public List<ExamSessionResponse> getAllSessions() {

        return examSessionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void addStudentToSession(Long sessionId, Long studentId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApiException("Student not found"));

        session.getStudents().add(student);

        examSessionRepository.save(session);
    }

    @Override
    public List<String> getSessionStudents(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        return session.getStudents()
                .stream()
                .map(Student::getRollNumber)
                .collect(Collectors.toList());
    }

    private ExamSessionResponse mapToResponse(ExamSession session) {

        Set<String> rollNumbers = session.getStudents()
                .stream()
                .map(Student::getRollNumber)
                .collect(Collectors.toSet());

        return ExamSessionResponse.builder()
                .id(session.getId())
                .courseCode(session.getCourseCode())
                .examDate(session.getExamDate())
                .examTime(session.getExamTime())
                .studentRollNumbers(rollNumbers)
                .build();
    }
}