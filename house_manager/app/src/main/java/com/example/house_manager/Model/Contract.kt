package com.example.house_manager.Model

data class Contract(
    val contractName: String,
    val startDay: String,
    val endDay: String,
    val description: String,
    val numberPersons: Int,
    val roomName: String
)

data class ContractResponse(
    val code: Int,
    val result: ContractResult
)

data class ContractResult(
    val contractName: String,
    val startDay: List<Int>,
    val endDay: List<Int>,
    val description: String,
    val numberPersons: Int,
    val roomResponse: InforRoom
)
data class InforRoom(
    val roomName: String,
    val roomTypeResponse: RoomTypeResponse,
    val department: Apartment,
    val status: String,
    val residents: List<Resident>
)