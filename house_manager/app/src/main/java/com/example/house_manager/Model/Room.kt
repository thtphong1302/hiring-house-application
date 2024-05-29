package com.example.house_manager.Model

data class RoomResponse(
    val code: Int,
    val result: ArrayList<Room>
)

data class Room(
    val roomName: String,
    val roomType: String,
    val departmentName: String,
    val status: RoomStatus
)
enum class RoomStatus {
    EMPTY,
    DONE
}