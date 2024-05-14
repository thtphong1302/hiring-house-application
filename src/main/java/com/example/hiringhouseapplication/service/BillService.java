package com.example.hiringhouseapplication.service;

import com.example.hiringhouseapplication.dto.request.BillRequest;
import com.example.hiringhouseapplication.dto.response.BillResponse;
import com.example.hiringhouseapplication.entity.Bill;

import java.util.List;

public interface BillService {

    List<BillResponse> getAllBills();
    BillResponse createBill(BillRequest request);
    BillResponse updateBill(Integer billID, BillRequest request);
}
