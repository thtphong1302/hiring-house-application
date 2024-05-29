package com.example.house_manager.Model


data class RoomtypeResponse(
    val code: Int,
    val result: List<Room_type>
)
data class Room_type(
    val typeName: String,
    val price: Int,
    val feeService: Int
)


