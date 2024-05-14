package com.example.hiringhouseapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomID;
    private String roomName;

    @OneToOne
    @JoinColumn(name = "type_name")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "room")
    private List<Resident> residents;

    @OneToMany(mappedBy = "room")
    private List<Bill> bills;
}
