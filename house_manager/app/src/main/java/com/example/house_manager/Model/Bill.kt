package com.example.house_manager.Model

import java.util.*

data class Bill(
    val billid: Int,
    val created_at: Date,
    val current_number_electrics: Int,
    val current_number_water: Int,
    val number_electric_before: Int,
    val number_water_before: Int,
    val room_id: String?
)
