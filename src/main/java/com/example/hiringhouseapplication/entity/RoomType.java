package com.example.hiringhouseapplication.entity;

import com.example.hiringhouseapplication.enums.Position;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RoomType {
    @Id
    private String typeName;
    private int price;
    private int feeService;

}
