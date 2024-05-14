package com.example.hiringhouseapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billID;
    private int numberElectricBefore;
    private int currentNumberElectrics;
    private int numberWaterBefore;
    private int currentNumberWater;
    @CreatedDate
    private LocalDate creatAt;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
