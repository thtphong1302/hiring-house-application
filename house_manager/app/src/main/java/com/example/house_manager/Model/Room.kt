package com.example.house_manager.Model

data class Room(
    val room_name: String,
    val status: RoomStatus,
    val apartment_name: Apartment,
    val type_name: Room_type // kiểu dữ liệu đang được đổi để hoàn thiện

)
enum class RoomStatus {
    EMPTY,
    DONE
}