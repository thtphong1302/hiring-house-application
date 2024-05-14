package com.example.hiringhouseapplication.service;

import com.example.hiringhouseapplication.dto.request.ContractRequest;
import com.example.hiringhouseapplication.dto.response.ContractResponse;

import java.util.List;

public interface ContractService {
    List<ContractResponse> getAllContracts();
    ContractResponse getContract(String contractID);
    ContractResponse createContract(ContractRequest request);
    void deleteContract(String contractID);
    ContractResponse updateContract(String contractID, ContractRequest request);
}
