package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.ExamSessionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    public ExamSessionResponse createSession(
            @RequestBody ExamSessionRequest request) {

        return examSessionService.createSession(request);
    }

    @GetMapping("/{id}")
    public ExamSessionResponse getSession(@PathVariable Long id) {

        return examSessionService.getSession(id);
    }

    @GetMapping
    public List<ExamSessionResponse> getAllSessions() {

        return examSessionService.getAllSessions();
    }

    @PostMapping("/{sessionId}/students/{studentId}")
    public String addStudentToSession(
            @PathVariable Long sessionId,
            @PathVariable Long studentId) {

        examSessionService.addStudentToSession(sessionId, studentId);

        return "Student added to session";
    }

    @GetMapping("/{sessionId}/students")
    public List<String> getSessionStudents(
            @PathVariable Long sessionId) {

        return examSessionService.getSessionStudents(sessionId);
    }
}