package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {

    private Long id;
    private String rollNumber;
    private String name;
    private String department;
    private Integer year;
}