package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRoomRequest {

    private String roomNumber;
    private Integer rowCount;
    private Integer columnCount;
}