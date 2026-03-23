package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSessionResponse {

    private Long id;

    private String courseCode;

    private LocalDate examDate;

    private String examTime;

    private Set<String> studentRollNumbers;
}