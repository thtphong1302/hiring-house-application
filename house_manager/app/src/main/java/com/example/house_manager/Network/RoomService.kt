package com.example.house_manager.Network

import com.example.house_manager.Model.Room
import retrofit2.Call
import retrofit2.http.*

interface RoomService {
    @GET("room-empty")
    fun getRoomEmptys(@Query("departmentName") departmentName: String): Call<List<Room>>

    @GET("room-done")
    fun getRoomsDone(@Query("departmentName") departmentName: String): Call<List<Room>>

    @POST("rooms")
    fun createRoom(@Body room: Room): Call<Room>
}
