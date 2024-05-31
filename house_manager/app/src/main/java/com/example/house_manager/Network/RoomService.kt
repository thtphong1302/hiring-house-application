package com.example.house_manager.Network

import com.example.house_manager.Model.Room
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Model.RoomResponse
import retrofit2.Call
import retrofit2.http.*

interface RoomService {
    @GET("rooms/room-empty")
    fun getEmptyRooms(@Query("departmentName") departmentName: String): Call<RoomResponse>

    @GET("rooms/room-done")
    fun getDoneRooms(@Query("departmentName") departmentName: String): Call<RoomResponse>

    @POST("rooms")
    fun createRoom(@Body room: Room): Call<Void>

    @DELETE("rooms/{roomName}")
    fun deleteRoom(@Path("roomName") roomName: String): Call<Void>

    // Inside RoomService

    @PUT("rooms/{roomName}")
    fun updateRoom(@Path("roomName") roomName: String, @Body room: Room): Call<Void>

}
