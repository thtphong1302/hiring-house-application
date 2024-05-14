package com.example.hiringhouseapplication.service;

import com.example.hiringhouseapplication.dto.request.RoomRequest;
import com.example.hiringhouseapplication.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAllRooms();
    List<RoomResponse> getAllRoomsInDepartment(String departmentName);
    RoomResponse createRoom(RoomRequest request);
    RoomResponse updateRoom(String roomName,RoomRequest request);
    void deleteRoom(String roomName);
    List<RoomResponse> getAllRoomsEmptyInDepartment(String departmentName);
    List<RoomResponse> getAllRoomsNotEmptyInDepartment(String departmentName);
    RoomResponse getRoom(String roomName);
}
