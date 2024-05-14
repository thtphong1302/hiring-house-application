package com.example.hiringhouseapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentID;
    private String departmentName;
    private int electricPrice;
    private int waterPrice;

    @OneToMany(mappedBy = "department")
    private List<Room> rooms;

}
