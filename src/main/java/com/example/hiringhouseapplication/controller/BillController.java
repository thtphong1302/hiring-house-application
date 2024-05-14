package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.BillRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.BillResponse;
import com.example.hiringhouseapplication.entity.Bill;
import com.example.hiringhouseapplication.repository.BillRepository;
import com.example.hiringhouseapplication.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping
    public ApiResponse<List<BillResponse>> getAllBills(){
        return ApiResponse.<List<BillResponse>>builder()
                .result(billService.getAllBills())
                .build();
    }

    @PostMapping
    public ApiResponse<BillResponse> createBill(@RequestBody BillRequest billRequest){
        return ApiResponse.<BillResponse>builder()
                .result(billService.createBill(billRequest))
                .build();
    }
}
