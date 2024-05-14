package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.ContractRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.ContractResponse;
import com.example.hiringhouseapplication.entity.Contract;
import com.example.hiringhouseapplication.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/{contractID}")
    public ApiResponse<ContractResponse> getContract(@PathVariable("contractID") String contractID){
        return ApiResponse.<ContractResponse>builder()
                .result(contractService.getContract(contractID)).build();
    }

    @PostMapping
    public ApiResponse<ContractResponse> createContract(@RequestBody ContractRequest contractRequest) {
        var result = contractService.createContract(contractRequest);
        return ApiResponse.<ContractResponse>builder().result(result).build();
    }
    @PutMapping("/{contractID}")
    public ApiResponse<ContractResponse> updateContract(
            @PathVariable("contractID") String contractID, @RequestBody ContractRequest contractRequest) {
        var result = contractService.updateContract(contractID, contractRequest);
        return ApiResponse.<ContractResponse>builder().result(result).build();
    }
    @DeleteMapping("/{contractID}")
    public ApiResponse<Void> deleteContract(@PathVariable("contractID") String contractID) {
        contractService.deleteContract(contractID);
        return ApiResponse.<Void>builder().message("Deleted successfully").build();
    }

}
