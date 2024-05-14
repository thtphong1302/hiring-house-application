package com.example.hiringhouseapplication.entity;

import com.example.hiringhouseapplication.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identityNumber;
    private String residentName;
    private String phoneNumber;
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
