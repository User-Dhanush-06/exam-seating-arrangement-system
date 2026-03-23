package com.example.demo.service.impl;

import com.example.demo.dto.SeatLookupResponse;
import com.example.demo.dto.SeatingPlanResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final ExamRoomRepository examRoomRepository;
    private final SeatingPlanRepository seatingPlanRepository;

    public SeatingPlanServiceImpl(
            ExamSessionRepository examSessionRepository,
            ExamRoomRepository examRoomRepository,
            SeatingPlanRepository seatingPlanRepository) {

        this.examSessionRepository = examSessionRepository;
        this.examRoomRepository = examRoomRepository;
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Override
    public SeatingPlanResponse generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<ExamRoom> rooms = examRoomRepository.findAll();

        if (rooms.isEmpty()) {
            throw new ApiException("No rooms available");
        }

        List<Student> students = new ArrayList<>(session.getStudents());

        ExamRoom selectedRoom = rooms.stream()
                .filter(r -> r.getCapacity() >= students.size())
                .findFirst()
                .orElseThrow(() -> new ApiException("No room with enough capacity"));

        Map<String, String> seating = new LinkedHashMap<>();

        int rows = selectedRoom.getRowCount();
        int cols = selectedRoom.getColumnCount();

        int studentIndex = 0;

        for (int r = 1; r <= rows; r++) {

            for (int c = 1; c <= cols; c++) {

                if (studentIndex >= students.size()) break;

                String seat = "R" + r + "C" + c;

                seating.put(seat, students.get(studentIndex).getRollNumber());

                studentIndex++;
            }
        }

        String json = seating.toString();

        SeatingPlan plan = SeatingPlan.builder()
                .examSession(session)
                .room(selectedRoom)
                .arrangementJson(json)
                .build();

        SeatingPlan saved = seatingPlanRepository.save(plan);

        return mapToResponse(saved);
    }

    @Override
    public SeatingPlanResponse getPlan(Long planId) {

        SeatingPlan plan = seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("Plan not found"));

        return mapToResponse(plan);
    }

    @Override
    public List<SeatingPlanResponse> getPlansBySession(Long sessionId) {

        return seatingPlanRepository.findByExamSessionId(sessionId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SeatingPlanResponse mapToResponse(SeatingPlan plan) {

        return SeatingPlanResponse.builder()
                .id(plan.getId())
                .sessionId(plan.getExamSession().getId())
                .roomNumber(plan.getRoom().getRoomNumber())
                .arrangementJson(plan.getArrangementJson())
                .generatedAt(plan.getGeneratedAt().toString())
                .build();
    }

    @Override
    public SeatLookupResponse findSeatByRollNumber(String rollNumber) {

        List<SeatingPlan> plans = seatingPlanRepository.findAll();

        for (SeatingPlan plan : plans) {

            String json = plan.getArrangementJson();

            if (json == null) continue;

            json = json.replace("{", "")
                    .replace("}", "");

            String[] pairs = json.split(",");

            for (String pair : pairs) {

                String[] entry = pair.split("=");

                if (entry.length != 2) continue;

                String seat = entry[0].trim();
                String studentRoll = entry[1].trim();

                if (studentRoll.equalsIgnoreCase(rollNumber)) {

                    return SeatLookupResponse.builder()
                            .rollNumber(rollNumber)
                            .room(plan.getRoom().getRoomNumber())
                            .seat(seat)
                            .courseCode(plan.getExamSession().getCourseCode())
                            .examTime(plan.getExamSession().getExamTime())
                            .build();
                }
            }
        }

        throw new ApiException("Seat not found for roll number");
    }
    
}