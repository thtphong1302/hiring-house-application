package com.example.house_manager.Model

data class Room(
    val room_name: String,
    val status: RoomStatus,
    val apartment_name: String?,
    val type_name: String?
)
enum class RoomStatus {
    EMPTY,
    DONE
}