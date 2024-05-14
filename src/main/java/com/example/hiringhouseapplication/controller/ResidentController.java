package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.ResidentRegisterRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.ResidentRegisterResponse;
import com.example.hiringhouseapplication.entity.Resident;
import com.example.hiringhouseapplication.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping
    public List<ResidentRegisterResponse> getAll() {
        return null;
    }
    @PostMapping
    public ApiResponse<ResidentRegisterResponse> createResident(
            @RequestBody ResidentRegisterRequest residentRegisterRequest) {
        return ApiResponse.<ResidentRegisterResponse>builder()
                .result(residentService.registerResident(residentRegisterRequest))
                .build();
    }

    @DeleteMapping("/{identityNumber}")
    public ApiResponse<Void> deleteResident(@PathVariable("identityNumber") String identityNumber){
        residentService.deleteResident(identityNumber);
        return ApiResponse.<Void>builder().message("Deleted successfully").build();
    }
}
