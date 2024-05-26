package com.example.house_manager.Network

import com.example.house_manager.Model.*
import retrofit2.http.GET

interface ApiService {


    @GET("/apartments")
    suspend fun getApartments(): List<Apartment>

    @GET("/roomtypes")
    suspend fun getRoomTypes(): List<Room_type>

    @GET("/rooms")
    suspend fun getRooms(): List<Room>

    @GET("/bills")
    suspend fun getBills(): List<Bill>

    @GET("/contractrooms")
    suspend fun getContractRooms(): List<ContractRooms>

    @GET("/residents")
    suspend fun getResidents(): List<Resident>
}