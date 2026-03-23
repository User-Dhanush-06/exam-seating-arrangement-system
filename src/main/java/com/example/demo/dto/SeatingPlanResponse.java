package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatingPlanResponse {

    private Long id;

    private Long sessionId;

    private String roomNumber;

    private String arrangementJson;

    private String generatedAt;
}