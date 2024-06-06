package com.example.hiringhouseapplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeRequest {
    private String typeName;
    private int price;
    private int feeService;
    private int area;
}
