package com.example.house_manager.Model

data class Contract(
    val contractName: String,
    val startDay: String,
    val endDay: String,
    val description: String,
    val numberPersons: Int,
    val roomName: String
)

