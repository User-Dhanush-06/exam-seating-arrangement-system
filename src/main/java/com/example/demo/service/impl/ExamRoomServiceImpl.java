package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoomResponse createRoom(ExamRoomRequest request) {

        if (examRoomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new ApiException("Room number already exists");
        }

        if (request.getRowCount() <= 0 || request.getColumnCount() <= 0) {
            throw new ApiException("Rows and columns must be positive");
        }

        ExamRoom room = ExamRoom.builder()
                .roomNumber(request.getRoomNumber())
                .rowCount(request.getRowCount())
                .columnCount(request.getColumnCount())
                .build();

        ExamRoom saved = examRoomRepository.save(room);

        return mapToResponse(saved);
    }

    @Override
    public ExamRoomResponse getRoom(Long id) {

        ExamRoom room = examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));

        return mapToResponse(room);
    }

    @Override
    public List<ExamRoomResponse> getAllRooms() {

        return examRoomRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExamRoomResponse updateRoom(Long id, ExamRoomRequest request) {

        ExamRoom room = examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));

        room.setRoomNumber(request.getRoomNumber());
        room.setRowCount(request.getRowCount());
        room.setColumnCount(request.getColumnCount());

        ExamRoom updated = examRoomRepository.save(room);

        return mapToResponse(updated);
    }

    @Override
    public void deleteRoom(Long id) {

        ExamRoom room = examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("Room not found"));

        examRoomRepository.delete(room);
    }

    @Override
    public Integer getAvailableSeats(Long roomId) {

        ExamRoom room = examRoomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException("Room not found"));

        return room.getCapacity();
    }

    private ExamRoomResponse mapToResponse(ExamRoom room) {

        return ExamRoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .rowCount(room.getRowCount())
                .columnCount(room.getColumnCount())
                .capacity(room.getCapacity())
                .build();
    }
}