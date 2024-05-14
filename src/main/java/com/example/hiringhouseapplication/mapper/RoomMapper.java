package com.example.hiringhouseapplication.mapper;

import com.example.hiringhouseapplication.dto.request.RoomRequest;
import com.example.hiringhouseapplication.dto.response.RoomResponse;
import com.example.hiringhouseapplication.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "roomType",ignore = true)
    Room toRoom(RoomRequest request);
    @Mapping(target = "residents", ignore = true)
    @Mapping(target = "roomTypeResponse", source = "roomType")
    RoomResponse toRoomResponse(Room room);
}
