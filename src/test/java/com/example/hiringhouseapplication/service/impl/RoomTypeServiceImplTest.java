package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.RoomTypeRequest;
import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.entity.RoomType;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.repository.RoomTypeRepository;
import com.example.hiringhouseapplication.service.RoomTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class RoomTypeServiceImplTest {
    @Autowired
    private RoomTypeService roomTypeService;

    @MockBean
    private RoomTypeRepository roomTypeRepository;
    private RoomTypeRequest roomTypeRequest;
    private RoomTypeResponse roomTypeResponse;

    @BeforeEach
    void initData(){
        roomTypeRequest = RoomTypeRequest.builder()
                .typeName("BALCONY")
                .price(2400000)
                .feeService(144000)
                .build();
    }
    @Test
    void createRoomType_validRequest_success(){
        //GIVEN
        var roomType = RoomType.builder()
                .typeName("BALCONY").price(2400000).feeService(144000).build();
        Mockito.when(roomTypeRepository.existsByTypeName(anyString())).thenReturn(false);
        Mockito.when(roomTypeRepository.save(any())).thenReturn(roomType);

        var response = roomTypeService.createRoomTye(roomTypeRequest);

        org.assertj.core.api.Assertions.assertThat(response.getTypeName()).isEqualTo("BALCONY");
    }
    @Test
    void createRoomType_invalidRequest_failed(){
        Mockito.when(roomTypeRepository.existsByTypeName(anyString())).thenReturn(true);

        var exception = Assertions.assertThrows(
                ApplicationError.class,() -> roomTypeService.createRoomTye(roomTypeRequest)
        );
        org.assertj.core.api.Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }
}