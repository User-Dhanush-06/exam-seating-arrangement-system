package com.example.demo.service;

import com.example.demo.dto.ExamRoomRequest;
import com.example.demo.dto.ExamRoomResponse;

import java.util.List;

public interface ExamRoomService {

    ExamRoomResponse createRoom(ExamRoomRequest request);

    ExamRoomResponse getRoom(Long id);

    List<ExamRoomResponse> getAllRooms();

    ExamRoomResponse updateRoom(Long id, ExamRoomRequest request);

    void deleteRoom(Long id);

    Integer getAvailableSeats(Long roomId);
}