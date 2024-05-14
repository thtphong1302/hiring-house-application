package com.example.hiringhouseapplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractRequest {
    private String contractName;
    private LocalDate startDay;
    private LocalDate endDay;
    private String description;
    private int numberPersons;
    private String roomName;
}
