package com.example.house_manager.Network

import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.ApartmentResponse
import retrofit2.Call
import retrofit2.http.*

interface ApartmentService {
    @GET("departments")
    fun getApartments(): Call<ApartmentResponse>

    @POST("departments")
    fun createApartment(@Body apartment: Apartment): Call<Apartment>

    @DELETE("departments/{id}")
    fun deleteApartment(@Path("id") apartmentId: String): Call<Void>

    @PUT("departments/{id}")
    fun updateApartment(@Path("id") apartmentId: String, @Body apartment: Apartment): Call<Apartment>
}
