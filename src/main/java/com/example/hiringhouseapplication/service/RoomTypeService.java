package com.example.hiringhouseapplication.service;

import com.example.hiringhouseapplication.dto.request.RoomTypeRequest;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeResponse> getAllRoomTypes();
    RoomTypeResponse createRoomTye(RoomTypeRequest roomTypeRequest);
    RoomTypeResponse updateRoomType(String name, RoomTypeRequest roomTypeRequest);
    void deleteRoomType(String name);
}
