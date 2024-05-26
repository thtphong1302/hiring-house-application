package com.example.house_manager.Network

import com.example.house_manager.Model.Apartment
import retrofit2.Call
import retrofit2.http.*

interface ApartmentService {
    @GET("apartments")
    fun getApartments(): Call<List<Apartment>>

    @POST("apartments")
    fun createApartment(@Body apartment: Apartment): Call<Apartment>
    @DELETE("apartments/{apartment_name}")
    fun deleteApartment(@Path("apartment_name") apartmentName: String): Call<Void>
}
