package com.example.house_manager.Network

import com.example.house_manager.Model.RoomType
import com.example.house_manager.Model.RoomTypeResponse
import retrofit2.Call
import retrofit2.http.*

interface RoomTypeService {
    @GET("room-types")
    fun getRoomTypes(): Call<RoomType>

    @POST("type_name")
    fun createRoomType(@Body roomType: RoomTypeResponse): Call<RoomTypeResponse>

    @PUT("room_type/{name}")
    fun updateRoomType(@Path("name") name: String, @Body roomType: RoomTypeResponse): Call<RoomTypeResponse>

    @DELETE("room_type/{name}")
    fun deleteRoomType(@Path("name") name: String): Call<Unit>
}
