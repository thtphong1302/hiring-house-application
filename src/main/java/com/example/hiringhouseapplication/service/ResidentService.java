package com.example.hiringhouseapplication.service;

import com.example.hiringhouseapplication.dto.request.ResidentRegisterRequest;
import com.example.hiringhouseapplication.dto.response.ResidentRegisterResponse;

import java.util.List;

public interface ResidentService {

    List<ResidentRegisterResponse> getAllResidentsInDepartment();
    ResidentRegisterResponse registerResident(ResidentRegisterRequest residentRegisterRequest);
    ResidentRegisterResponse updateResident(String identityNumber, ResidentRegisterRequest residentRegisterRequest);
    void deleteResident(String identityNumber);
}
