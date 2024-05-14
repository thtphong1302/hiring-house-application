package com.example.hiringhouseapplication.dto.response;

import com.example.hiringhouseapplication.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {
    private String roomName;
    private RoomTypeResponse roomTypeResponse;
    private DepartmentResponse department;
    private Status status;
    private List<ResidentRegisterResponse> residents;
}
