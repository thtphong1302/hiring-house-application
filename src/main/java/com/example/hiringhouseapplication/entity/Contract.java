package com.example.hiringhouseapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String contractID;
    private String contractName;
    private Date start;
    private Date end;
    private String description;
    private int numberPersons;

    @ManyToMany
    private List<Room> rooms;
}
