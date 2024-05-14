package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.RoomRequest;
import com.example.hiringhouseapplication.dto.response.DepartmentResponse;
import com.example.hiringhouseapplication.dto.response.ResidentRegisterResponse;
import com.example.hiringhouseapplication.dto.response.RoomResponse;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.entity.Department;
import com.example.hiringhouseapplication.entity.Resident;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.entity.RoomType;
import com.example.hiringhouseapplication.enums.Status;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.DepartmentMapper;
import com.example.hiringhouseapplication.mapper.RoomMapper;
import com.example.hiringhouseapplication.mapper.RoomTypeMapper;
import com.example.hiringhouseapplication.repository.DepartmentRepository;
import com.example.hiringhouseapplication.repository.RoomRepository;
import com.example.hiringhouseapplication.repository.RoomTypeRepository;
import com.example.hiringhouseapplication.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final DepartmentMapper departmentMapper;
    private final RoomTypeMapper roomTypeMapper;
    private final RoomTypeRepository roomTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(roomMapper::toRoomResponse).toList();
    }

    @Override
    public List<RoomResponse> getAllRoomsInDepartment(String departmentName) {
        return roomRepository.getAllRoomsInDepartment(departmentName)
                .stream().map(roomMapper::toRoomResponse)
                .toList();
    }

    @Override
    public RoomResponse createRoom(RoomRequest request) {
        Department department = departmentRepository.findByDepartmentName(request.getDepartmentName())
                .orElseThrow(() -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST));

        DepartmentResponse departmentResponse = departmentMapper.toDepartmentResponse(department);

        RoomType roomType = roomTypeRepository.findByTypeName(request.getRoomType()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        RoomTypeResponse roomTypeResponse = roomTypeMapper.toRoomTypeResponse(roomType);

        var room = Room.builder()
                .roomName(request.getRoomName())
                .roomType(roomType)
                .status(Status.EMPTY)
                .department(department)
                .build()
                ;
        roomRepository.save(room);

        return RoomResponse.builder()
                .roomName(request.getRoomName())
                .roomTypeResponse(roomTypeResponse)
                .department(departmentResponse)
                .status(Status.EMPTY)
                .build();
    }

    @Override
    public RoomResponse updateRoom(String roomName, RoomRequest request) {
        Room room = roomRepository.findByRoomName(roomName).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        return null;
    }

    @Override
    public void deleteRoom(String roomName) {
        if(!roomRepository.existsByRoomName(roomName))
            throw new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST);

        roomRepository.deleteById(roomName);
    }

    @Override
    public List<RoomResponse> getAllRoomsEmptyInDepartment(String departmentName) {
        return roomRepository.getStatusRoomsInDepartment(departmentName,Status.EMPTY)
                .stream().map(roomMapper::toRoomResponse)
                .toList();
    }

    @Override
    public List<RoomResponse> getAllRoomsNotEmptyInDepartment(String departmentName) {
        return roomRepository.getStatusRoomsInDepartment(departmentName,Status.DONE)
                .stream().map(roomMapper::toRoomResponse)
                .toList();
    }

    @Override
    public RoomResponse getRoom(String roomName) {
        Room room = roomRepository.findByRoomName(roomName).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        var residents = roomRepository.getAllResidentsInRoom(roomName)
                .stream()
                .map(rs -> ResidentRegisterResponse.builder()
                        .residentName(rs.getResidentName())
                        .phoneNumber(rs.getPhoneNumber())
                        .gender(rs.getGender().name())
                        .build()
                )
                .toList();

        return RoomResponse.builder()
                .roomName(room.getRoomName())
                .status(room.getStatus())
                .department(departmentMapper.toDepartmentResponse(room.getDepartment()))
                .roomTypeResponse(roomTypeMapper.toRoomTypeResponse(room.getRoomType()))
                .residents(residents)
                .build()
                ;
    }
}
