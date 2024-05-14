package com.example.hiringhouseapplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomUpdateRequest {
    private String roomName;
    private String roomType;
    private int departmentID;
    private List<String> residents;
    private List<String> bills;
    private List<String> contracts;
}
