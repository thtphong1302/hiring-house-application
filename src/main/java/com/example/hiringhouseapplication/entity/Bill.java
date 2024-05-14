package com.example.hiringhouseapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billID;
    private int currentNumberElectrics;
    private int currentNumberWater;
    @CreatedDate
    private Date creatAt;
    private long total;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
