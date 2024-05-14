package com.example.hiringhouseapplication.dto.request;

import com.example.hiringhouseapplication.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequest {
    private int currentNumberElectrics;
    private int currentNumberWater;
    private String roomName;
}
