package com.example.hiringhouseapplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {
    private int numberElectricsBefore;
    private int currentNumberElectrics;
    private int numberWaterBefore;
    private int currentNumberWaters;
    private int priceRoom;
    private int feeService;
    private long total;
}
