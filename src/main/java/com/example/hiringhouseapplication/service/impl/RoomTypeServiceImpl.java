package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.RoomTypeRequest;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.entity.RoomType;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.RoomTypeMapper;
import com.example.hiringhouseapplication.repository.RoomTypeRepository;
import com.example.hiringhouseapplication.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomTypeResponse> getAllRoomTypes() {
        return roomTypeRepository.findAll()
                .stream()
                .map(roomTypeMapper::toRoomTypeResponse)
                .toList();
    }

    @Override
    public RoomTypeResponse createRoomTye(RoomTypeRequest roomTypeRequest) {
        if(roomTypeRepository.existsByTypeName(roomTypeRequest.getTypeName()))
            throw new ApplicationError(ErrorCode.RESOURCES_ALREADY_EXISTS);

        RoomType roomType = roomTypeMapper.toRoomType(roomTypeRequest);
        roomTypeRepository.save(roomType);

        return roomTypeMapper.toRoomTypeResponse(roomType);
    }

    @Override
    public RoomTypeResponse updateRoomType(String name, RoomTypeRequest roomTypeRequest) {
        RoomType roomType = roomTypeRepository.findByTypeName(name).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );

        roomTypeMapper.updateRoomType(roomType, roomTypeRequest);
        roomTypeRepository.save(roomType);
        return roomTypeMapper.toRoomTypeResponse(roomType);
    }

    @Override
    public void deleteRoomType(String name) {
        if(!roomTypeRepository.existsByTypeName(name))
            throw new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST);

        roomTypeRepository.deleteById(name);
    }
}
