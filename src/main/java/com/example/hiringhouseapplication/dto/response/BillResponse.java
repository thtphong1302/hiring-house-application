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
    private String roomName;
    private int numberElectricBefore;
    private int currentNumberElectrics;
    private int numberWaterBefore;
    private int currentNumberWater;
    private int feeService;
    private int price;
    private int total;
}
