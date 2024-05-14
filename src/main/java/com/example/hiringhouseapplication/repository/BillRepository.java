package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query("SELECT MAX(currentNumberElectrics) FROM Bill WHERE room.roomName = ?1")
    Integer getNumberElectricBeforeMonth(String roomName);

    @Query("SELECT MAX(currentNumberWater) FROM Bill WHERE room.roomName = ?1")
    Integer getNumberWaterBeforeMonth(String roomName);
}
