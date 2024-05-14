package com.example.hiringhouseapplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractResponse {
    private String contractName;
    private LocalDate startDay;
    private LocalDate endDay;
    private String description;
    private int numberPersons;
    private RoomResponse roomResponse;
}

