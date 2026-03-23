package com.example.demo.controller;

import com.example.demo.dto.SeatLookupResponse;
import com.example.demo.dto.SeatingPlanResponse;
import com.example.demo.service.SeatingPlanService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seating-plans")
public class SeatingPlanController {

    private final SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    @PostMapping("/generate/{sessionId}")
    public SeatingPlanResponse generatePlan(@PathVariable Long sessionId) {

        return seatingPlanService.generatePlan(sessionId);
    }

    @GetMapping("/{planId}")
    public SeatingPlanResponse getPlan(@PathVariable Long planId) {

        return seatingPlanService.getPlan(planId);
    }

    @GetMapping("/session/{sessionId}")
    public List<SeatingPlanResponse> getPlansBySession(@PathVariable Long sessionId) {

        return seatingPlanService.getPlansBySession(sessionId);
    }

    @GetMapping("/seat/{rollNumber}")
    public SeatLookupResponse findSeat(
            @PathVariable String rollNumber) {

        return seatingPlanService.findSeatByRollNumber(rollNumber);
    }
}