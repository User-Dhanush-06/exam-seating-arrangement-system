package com.example.demo.service;

import com.example.demo.dto.ExamSessionRequest;
import com.example.demo.dto.ExamSessionResponse;

import java.util.List;

public interface ExamSessionService {

    ExamSessionResponse createSession(ExamSessionRequest request);

    ExamSessionResponse getSession(Long id);

    List<ExamSessionResponse> getAllSessions();

    void addStudentToSession(Long sessionId, Long studentId);

    List<String> getSessionStudents(Long sessionId);
}