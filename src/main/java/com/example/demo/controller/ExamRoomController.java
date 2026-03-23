package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.ExamRoomService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    public ExamRoomResponse createRoom(@RequestBody ExamRoomRequest request) {
        return examRoomService.createRoom(request);
    }

    @GetMapping("/{id}")
    public ExamRoomResponse getRoom(@PathVariable Long id) {
        return examRoomService.getRoom(id);
    }

    @GetMapping
    public List<ExamRoomResponse> getAllRooms() {
        return examRoomService.getAllRooms();
    }

    @PutMapping("/{id}")
    public ExamRoomResponse updateRoom(
            @PathVariable Long id,
            @RequestBody ExamRoomRequest request) {

        return examRoomService.updateRoom(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {

        examRoomService.deleteRoom(id);
        return "Room deleted successfully";
    }

    @GetMapping("/{roomId}/available-seats")
    public Integer getAvailableSeats(@PathVariable Long roomId) {

        return examRoomService.getAvailableSeats(roomId);
    }
}