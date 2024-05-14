package com.example.hiringhouseapplication.mapper;

import com.example.hiringhouseapplication.dto.request.RoomTypeRequest;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.entity.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomType toRoomType(RoomTypeRequest roomTypeRequest);
    RoomTypeResponse toRoomTypeResponse(RoomType roomType);
    void updateRoomType(@MappingTarget RoomType roomType, RoomTypeRequest roomTypeRequest);
}
