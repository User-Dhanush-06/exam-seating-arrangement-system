package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamRoomResponse {

    private Long id;
    private String roomNumber;
    private Integer rowCount;
    private Integer columnCount;
    private Integer capacity;
}