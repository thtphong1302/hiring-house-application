    package com.example.house_manager.Model
    
    data class RoomResponse(
        val code: Int,
        val result: List<RoomEmpty>
    )
    data class RoomEmpty(
        val roomName: String,
        val roomTypeResponse: RoomTypeResponse,
        val department: Apartment,
        val status: String
    )
    data class Room(
        val roomName: String,
        val roomType: String,
        val departmentName: String
    )