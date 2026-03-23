package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String rollNumber;
    private String name;
    private String department;
    private Integer year;
}