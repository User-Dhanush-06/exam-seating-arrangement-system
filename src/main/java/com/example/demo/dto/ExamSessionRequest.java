package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamSessionRequest {

    private String courseCode;
    private LocalDate examDate;
    private String examTime;

    private Set<Long> studentIds;
}