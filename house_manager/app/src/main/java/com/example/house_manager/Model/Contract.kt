package com.example.house_manager.Model

import java.time.LocalDate

data class Contract(
    val contractName: String,
    val startDay: LocalDate,
    val endDay: LocalDate,
    val description: String,
    val numberPersons: Int,
    val roomName: String
)
