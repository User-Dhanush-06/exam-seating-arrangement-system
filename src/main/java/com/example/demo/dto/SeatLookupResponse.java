package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatLookupResponse {

    private String rollNumber;

    private String room;

    private String seat;

    private String courseCode;

    private String examTime;
}