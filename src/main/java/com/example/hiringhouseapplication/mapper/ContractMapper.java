package com.example.hiringhouseapplication.mapper;

import com.example.hiringhouseapplication.dto.request.ContractRequest;
import com.example.hiringhouseapplication.dto.response.ContractResponse;
import com.example.hiringhouseapplication.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractResponse toContractResponse(Contract contract);
    Contract toContract(ContractRequest contractRequest);

    void updateContract(@MappingTarget Contract contract, ContractRequest contractRequest);
}
