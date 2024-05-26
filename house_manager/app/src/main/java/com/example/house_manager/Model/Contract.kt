package com.example.house_manager.Model

import java.util.*

data class Contract(
    val contractid: String,
    val contract_name: String,
    val description: String,
    val end_day: Date,
    val number_persons: Int,
    val start_day: Date
)
