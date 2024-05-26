package com.example.house_manager.Network

import com.example.house_manager.Model.Apartment
import retrofit2.http.*

interface ApartmentService {
    @GET("/apartments")
    suspend fun getApartments(): List<Apartment>

    @POST("/apartments")
    suspend fun createApartment(@Body apartment: Apartment): Apartment

    @PUT("/apartments/{name}")
    suspend fun updateApartment(@Path("name") name: String, @Body apartment: Apartment): Apartment

    @DELETE("/apartments/{name}")
    suspend fun deleteApartment(@Path("name") name: String): Unit
}