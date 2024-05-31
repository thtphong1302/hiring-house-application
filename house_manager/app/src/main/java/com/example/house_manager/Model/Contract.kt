package com.example.house_manager.Model

import java.util.*

data class Contract(
    val contractName: String,
    val startDay: Date,
    val endDay: Date,
    val description: String,
    val numberPersons: Int,
    val roomName: String
)
