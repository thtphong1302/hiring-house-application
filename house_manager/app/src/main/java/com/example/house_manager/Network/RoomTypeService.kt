package com.example.house_manager.Network

import com.example.house_manager.Model.Room_type
import com.example.house_manager.Model.RoomtypeResponse
import retrofit2.Call
import retrofit2.http.*

interface RoomTypeService {
    @GET("room-types")
    fun getRoomTypes(): Call<RoomtypeResponse>

    @POST("type_name")
    fun createRoomType(@Body roomType: Room_type): Call<Room_type>

    @PUT("/room_type/{name}")
    fun updateRoomType(@Path("name") name: String, @Body roomType: Room_type): Call<Room_type>

    @DELETE("/room_type/{name}")
    fun deleteRoomType(@Path("name") name: String): Call<Unit>
}
