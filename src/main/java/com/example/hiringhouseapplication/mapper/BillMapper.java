package com.example.hiringhouseapplication.mapper;

import com.example.hiringhouseapplication.dto.request.BillRequest;
import com.example.hiringhouseapplication.dto.response.BillResponse;
import com.example.hiringhouseapplication.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper {

    Bill toBill(BillRequest request);

    @Mapping(target = "total", ignore = true)
    BillResponse toBillResponse(Bill bill);
}
