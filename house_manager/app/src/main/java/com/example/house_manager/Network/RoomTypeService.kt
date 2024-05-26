package com.example.house_manager.Network

import com.example.house_manager.Model.Room_type
import retrofit2.http.*


interface RoomTypeService {
    @GET("/room_type")
    suspend fun getRoomTypes(): List<Room_type>

    @POST("/room_type")
    suspend fun createRoomType(@Body roomType: Room_type): Room_type

    @PUT("/room_type/{name}")
    suspend fun updateRoomType(@Path("name") name: String, @Body roomType: Room_type): Room_type

    @DELETE("/room_type/{name}")
    suspend fun deleteRoomType(@Path("name") name: String): Unit
}