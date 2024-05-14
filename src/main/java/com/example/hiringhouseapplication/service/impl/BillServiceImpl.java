package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.BillRequest;
import com.example.hiringhouseapplication.dto.response.BillResponse;
import com.example.hiringhouseapplication.entity.Bill;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.BillMapper;
import com.example.hiringhouseapplication.repository.BillRepository;
import com.example.hiringhouseapplication.repository.RoomRepository;
import com.example.hiringhouseapplication.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final RoomRepository roomRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    @Override
    public List<BillResponse> getAllBills() {
        return billRepository.findAll().stream()
                .map(billMapper::toBillResponse)
                .toList();
    }

    @Override
    public BillResponse createBill(BillRequest request) {
        Integer numberElectricBefore = billRepository.getNumberElectricBeforeMonth(request.getRoomName());
        Integer numberWaterBefore = billRepository.getNumberWaterBeforeMonth(request.getRoomName());

        if(numberElectricBefore == null)
            numberElectricBefore = 0;

        if (numberWaterBefore == null)
            numberWaterBefore = 0;

        Room room = roomRepository.findByRoomName(request.getRoomName()).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );

        var bill = Bill.builder()
                .numberElectricBefore(numberElectricBefore)
                .currentNumberElectrics(request.getCurrentNumberElectrics())
                .numberWaterBefore(numberWaterBefore)
                .currentNumberWater(request.getCurrentNumberWater())
                .room(room)
                .build();

        int feeService = room.getRoomType().getFeeService();
        int priceRoom = room.getRoomType().getPrice();

        int priceElectric = room.getDepartment().getElectricPrice();
        int priceWater = room.getDepartment().getWaterPrice();

        int total = (request.getCurrentNumberWater() - numberWaterBefore) * priceWater +
                (request.getCurrentNumberElectrics() - numberElectricBefore) * priceElectric +
                feeService + priceRoom;

        var billResponse = BillResponse.builder()
                .roomName(bill.getRoom().getRoomName())
                .numberElectricBefore(numberElectricBefore)
                .currentNumberElectrics(bill.getCurrentNumberElectrics())
                .numberWaterBefore(numberWaterBefore)
                .currentNumberWater(bill.getCurrentNumberWater())
                .feeService(feeService)
                .price(priceRoom)
                .total(total)
                .build();

        billRepository.save(bill);

        return billResponse;
    }

    @Override
    public BillResponse updateBill(Integer billID, BillRequest request) {
        return null;
    }
}
