package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.RoomRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.RoomResponse;
import com.example.hiringhouseapplication.service.RoomService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return ApiResponse.<List<RoomResponse>>builder().result(roomService.getAllRooms()).build();
    }

    @GetMapping("/department")
    public ApiResponse<List<RoomResponse>> getAllRoomsInDepartment(@RequestParam("department_name") String departmentName) {
        return ApiResponse.<List<RoomResponse>>builder()
                .result(roomService.getAllRoomsInDepartment(departmentName))
                .build();
    }
    @PostMapping
    public ApiResponse<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest) {
        var result = roomService.createRoom(roomRequest);
        return ApiResponse.<RoomResponse>builder().result(result).build();
    }

    @GetMapping("/room-empty")
    public ApiResponse<List<RoomResponse>> getAllRoomsEmptyInDepartment(
            @RequestParam("departmentName") String departmentName) {
        var result = roomService.getAllRoomsEmptyInDepartment(departmentName);
        return ApiResponse.<List<RoomResponse>>builder().result(result).build();
    }

    @GetMapping("/room-done")
    public ApiResponse<List<RoomResponse>> getAllRoomsDoneInDepartment(
            @RequestParam("departmentName") String departmentName) {
        var result = roomService.getAllRoomsNotEmptyInDepartment(departmentName);
        return ApiResponse.<List<RoomResponse>>builder().result(result).build();
    }
    @GetMapping("/{roomName}")
    public ApiResponse<RoomResponse> getRoom(@PathVariable("roomName") String roomName){
        return ApiResponse.<RoomResponse>builder().result(roomService.getRoom(roomName)).build();
    }

    @DeleteMapping("/{roomName}")
    public ApiResponse<Void> deleteRoom(@PathVariable("roomName") String roomName){
        roomService.deleteRoom(roomName);
        return ApiResponse.<Void>builder().message("Delete successfully!").build();
    }
//    @GetMapping("/search")
//    public ApiResponse<List<RoomResponse>> findRoomsByPriceAndArea(
//            @RequestParam("price") Integer price,@RequestParam("area") Integer area){
//        var result = roomService.findRoomByPriceAndArea(price,area);
//        return ApiResponse.<List<RoomResponse>>builder().result(result).build();
//    }
}
