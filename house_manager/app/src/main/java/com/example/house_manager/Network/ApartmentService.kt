package com.example.house_manager.Network

import com.example.house_manager.Model.Apartment
import retrofit2.Call
import retrofit2.http.*

interface ApartmentService {
    @GET("/apartment")
    fun getApartments(): Call<List<Apartment>>

    @POST("/apartment")
    fun createApartment(@Body apartment: Apartment): Call<Apartment>

    @PUT("/apartment/{name}")
    suspend fun updateApartment(@Path("name") name: String, @Body apartment: Apartment): Apartment

    @DELETE("/apartment/{name}")
    fun deleteApartment(@Path("name") name: String): Call<Void>
}