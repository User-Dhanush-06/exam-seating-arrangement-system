package com.example.demo.service;

import com.example.demo.dto.SeatLookupResponse;
import com.example.demo.dto.SeatingPlanResponse;

import java.util.List;

public interface SeatingPlanService {

    SeatingPlanResponse generatePlan(Long sessionId);

    SeatingPlanResponse getPlan(Long planId);

    List<SeatingPlanResponse> getPlansBySession(Long sessionId);

    SeatLookupResponse findSeatByRollNumber(String rollNumber);

}