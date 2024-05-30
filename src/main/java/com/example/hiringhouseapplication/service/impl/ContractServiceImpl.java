package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.ContractRequest;
import com.example.hiringhouseapplication.dto.response.ContractResponse;
import com.example.hiringhouseapplication.dto.response.ResidentRegisterResponse;
import com.example.hiringhouseapplication.dto.response.RoomResponse;
import com.example.hiringhouseapplication.entity.Contract;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.enums.Status;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.ContractMapper;
import com.example.hiringhouseapplication.mapper.RoomMapper;
import com.example.hiringhouseapplication.repository.ContractRepository;
import com.example.hiringhouseapplication.repository.RoomRepository;
import com.example.hiringhouseapplication.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractMapper contractMapper;
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final ContractRepository contractRepository;
    @Override
    public List<ContractResponse> getAllContracts() {
        return List.of();
    }

    @Override
    public ContractResponse getContract(String contractID) {
        Contract contract = contractRepository.findByContractID(contractID).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        var resident = roomRepository.getAllResidentsInRoom(contract.getRoom().getRoomName());

        List<ResidentRegisterResponse> residentRegisterResponses = resident.stream().map(
                rs -> ResidentRegisterResponse.builder()
                        .residentName(rs.getResidentName())
                        .gender(rs.getGender().name())
                        .phoneNumber(rs.getPhoneNumber())
                        .build()
        ).toList();
        RoomResponse response = roomMapper.toRoomResponse(contract.getRoom());
        response.setResidents(residentRegisterResponses);

        return ContractResponse.builder()
                .contractName(contract.getContractName())
                .startDay(contract.getStartDay())
                .endDay(contract.getEndDay())
                .description(contract.getDescription())
                .numberPersons(contract.getNumberPersons())
                .roomResponse(response)
                .build();
    }

    @Override
    public ContractResponse createContract(ContractRequest request) {
        Room room = roomRepository.findByRoomName(request.getRoomName()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        RoomResponse response = roomMapper.toRoomResponse(room);

        if(request.getEndDay().isBefore(request.getStartDay()))
            throw new ApplicationError(ErrorCode.INVALID_DAY);

        var contract = Contract.builder()
                .contractName(request.getContractName())
                .startDay(request.getStartDay())
                .endDay(request.getEndDay())
                .description(request.getDescription())
                .numberPersons(request.getNumberPersons())
                .room(room)
                .build();
        contractRepository.save(contract);

        roomRepository.updateStatus(request.getRoomName(), Status.DONE);


        return ContractResponse.builder()
                .contractName(contract.getContractName())
                .startDay(contract.getStartDay())
                .endDay(contract.getEndDay())
                .description(contract.getDescription())
                .numberPersons(contract.getNumberPersons())
                .roomResponse(response)
                .build();
    }

    @Override
    public void deleteContract(String contractID) {
        if(!contractRepository.existsByContractID(contractID))
            throw new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST);

        contractRepository.deleteById(contractID);
    }

    @Override
    public ContractResponse updateContract(String contractID, ContractRequest request) {
        Contract contract = contractRepository.findByContractID(contractID).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        Room room = roomRepository.findByRoomName(request.getRoomName()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );
        RoomResponse roomResponse = roomMapper.toRoomResponse(room);
        contractMapper.updateContract(contract,request);
        contract.setRoom(room);
        contractRepository.save(contract);
        return contractMapper.toContractResponse(contract);
    }
}
