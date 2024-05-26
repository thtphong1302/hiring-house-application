package com.example.house_manager.Network

import com.example.house_manager.Model.Room
import retrofit2.Call
import retrofit2.http.*

interface RoomService {
    @GET("/rooms")
    fun getRooms(): List<Room>

    @POST("/rooms")
    fun createRoom(@Body room: Room): Call<Room>

    @PUT("/rooms/{name}")
    suspend fun updateRoom(@Path("name") name: String, @Body room: Room): Room

    @DELETE("/rooms/{name}")
    suspend fun deleteRoom(@Path("name") name: String): Unit
}