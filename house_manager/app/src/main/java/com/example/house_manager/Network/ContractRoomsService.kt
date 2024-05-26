package com.example.house_manager.Network

import com.example.house_manager.Model.ContractRooms
import retrofit2.http.*

interface ContractRoomsService {
    @GET("/contract_rooms")
    suspend fun getContractRooms(): List<ContractRooms>

    @POST("/contract_rooms")
    suspend fun createContractRoom(@Body contractRooms: ContractRooms): ContractRooms

    @PUT("/contract_rooms/{contractId}/{roomName}")
    suspend fun updateContractRoom(@Path("contractId") contractId: String, @Path("roomName") roomName: String, @Body contractRooms: ContractRooms): ContractRooms

    @DELETE("/contract_rooms/{contractId}/{roomName}")
    suspend fun deleteContractRoom(@Path("contractId") contractId: String, @Path("roomName") roomName: String): Unit
}