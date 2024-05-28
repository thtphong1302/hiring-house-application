package com.example.hiringhouseapplication.dto.request;

import com.example.hiringhouseapplication.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResidentRegisterRequest {
    private String residentName;
    private String phoneNumber;
    private String gender;
    private String roomName;
}
