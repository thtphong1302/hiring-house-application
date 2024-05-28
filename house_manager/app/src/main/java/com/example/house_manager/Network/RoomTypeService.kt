package com.example.house_manager.Network

import com.example.house_manager.Model.Room_type
import retrofit2.Call
import retrofit2.http.*


interface RoomTypeService {
    @GET("type_name")
    suspend fun getRoomTypes(): List<Room_type>

    @POST("type_name")
    fun createRoomType(@Body roomType: Room_type): Call<Room_type>

    @PUT("/room_type/{name}")
    suspend fun updateRoomType(@Path("name") name: String, @Body roomType: Room_type): Room_type

    @DELETE("/room_type/{name}")
    suspend fun deleteRoomType(@Path("name") name: String): Unit
}