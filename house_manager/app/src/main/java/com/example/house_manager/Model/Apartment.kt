package com.example.house_manager.Model


data class Apartment(

    val departmentName: String,
    val electricPrice: Int?,
    val waterPrice: Int?
)

data class ApartmentResponse(
    val code: Int,
    val result: List<Apartment>
)

