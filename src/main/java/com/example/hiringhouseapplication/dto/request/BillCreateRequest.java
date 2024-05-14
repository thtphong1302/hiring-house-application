package com.example.hiringhouseapplication.dto.request;

import com.example.hiringhouseapplication.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillCreateRequest {
    private int currentNumberElectrics;
    private int currentNumberWater;
    private int total;
    private Room room;
}
