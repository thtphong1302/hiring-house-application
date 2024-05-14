package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.ResidentRegisterRequest;
import com.example.hiringhouseapplication.dto.response.ResidentRegisterResponse;
import com.example.hiringhouseapplication.dto.response.RoomResponse;
import com.example.hiringhouseapplication.entity.Resident;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.enums.Gender;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.RoomMapper;
import com.example.hiringhouseapplication.repository.ResidentRepository;
import com.example.hiringhouseapplication.repository.RoomRepository;
import com.example.hiringhouseapplication.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final RoomMapper roomMapper;
    private final ResidentRepository residentRepository;
    private final RoomRepository roomRepository;
    @Override
    public List<ResidentRegisterResponse> getAllResidentsInDepartment() {
        return List.of();
    }

    @Override
    public ResidentRegisterResponse registerResident(ResidentRegisterRequest residentRegisterRequest) {
        Room room = roomRepository.findByRoomName(residentRegisterRequest.getRoomName()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );

        RoomResponse roomResponse = roomMapper.toRoomResponse(room);
        var genderName = residentRegisterRequest.getGender();
        var gender = (genderName.equals("MALE")) ? Gender.MALE : Gender.FEMALE;

        var resident = Resident.builder()
                .residentName(residentRegisterRequest.getResidentName())
                .phoneNumber(residentRegisterRequest.getPhoneNumber())
                .gender(gender)
                .room(room)
                .build();

        residentRepository.save(resident);

        return  ResidentRegisterResponse.builder()
                .residentName(resident.getResidentName())
                .gender(resident.getGender().name())
                .phoneNumber(resident.getPhoneNumber())
                .roomResponse(roomResponse)
                .build();

    }

    @Override
    public ResidentRegisterResponse updateResident(String identityNumber,ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = residentRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        Room room = roomRepository.findByRoomName(residentRegisterRequest.getRoomName()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        resident.setResidentName(residentRegisterRequest.getResidentName());
        resident.setPhoneNumber(residentRegisterRequest.getPhoneNumber());
        resident.setRoom(room);
        residentRepository.save(resident);

        return ResidentRegisterResponse.builder()
                .residentName(resident.getResidentName())
                .build();
    }

    @Override
    public void deleteResident(String identityNumber) {
        if(!residentRepository.existsByIdentityNumber(identityNumber))
            throw new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST);

        residentRepository.deleteById(identityNumber);
    }
}
