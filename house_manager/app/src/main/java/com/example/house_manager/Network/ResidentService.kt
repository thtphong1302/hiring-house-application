package com.example.house_manager.Network

import com.example.house_manager.Model.Resident
import retrofit2.Call
import retrofit2.http.*

interface ResidentService {
    @GET("/residents")
    suspend fun getResidents(): List<Resident>

    @POST("residents")
    fun createResident(@Body resident: Resident): Call<Void>

    @PUT("/residents/{identityNumber}")
    suspend fun updateResident(@Path("identityNumber") identityNumber: String, @Body resident: Resident): Resident

    @DELETE("/residents/{identityNumber}")
    suspend fun deleteResident(@Path("identityNumber") identityNumber: String): Unit
}