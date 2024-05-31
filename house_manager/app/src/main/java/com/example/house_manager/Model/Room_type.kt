package com.example.house_manager.Model

data class RoomType(
    val code: Int,
    val result: List<RoomTypeResponse>

)
data class RoomTypeResponse(
    val typeName: String,
    val price: Int,
    val feeService: Int
)

