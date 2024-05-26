package com.example.house_manager.Model

data class Resident(
    val identity_number: String,
    val gender: Boolean,
    val phone_number: String,
    val resident_name: String,
    val room_id: String
)
