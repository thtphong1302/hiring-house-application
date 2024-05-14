package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.RoomTypeRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.service.RoomTypeService;
import com.example.hiringhouseapplication.service.impl.RoomTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-types")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeServiceImpl roomTypeService;

    @GetMapping
    public ApiResponse<List<RoomTypeResponse>> getAllRoomTypes() {
        var result = roomTypeService.getAllRoomTypes();
        return ApiResponse.<List<RoomTypeResponse>>builder().result(result).build();
    }

    @PostMapping
    public ApiResponse<RoomTypeResponse> createRoomType(@RequestBody RoomTypeRequest roomTypeRequest) {
        var result = roomTypeService.createRoomTye(roomTypeRequest);
        return ApiResponse.<RoomTypeResponse>builder().result(result).build();
    }

    @PutMapping()
    public ApiResponse<RoomTypeResponse> updateRoomType(
            @RequestParam String name, @RequestBody RoomTypeRequest roomTypeRequest) {
        var result = roomTypeService.updateRoomType(name, roomTypeRequest);
        return ApiResponse.<RoomTypeResponse>builder().result(result).build();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteRoomType(@RequestParam String name) {
        roomTypeService.deleteRoomType(name);
        return ApiResponse.<Void>builder().message("Delete successfully").build();
    }

}
