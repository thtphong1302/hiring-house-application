package com.example.hiringhouseapplication.dto.response;

import com.example.hiringhouseapplication.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResidentRegisterResponse {
    private String residentName;
    private String phoneNumber;
    private String gender;
    private RoomResponse roomResponse;
}
