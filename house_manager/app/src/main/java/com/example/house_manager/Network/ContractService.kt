package com.example.house_manager.Network

import com.example.house_manager.Model.Contract
import retrofit2.Call
import retrofit2.http.*

interface ContractService {
    @GET("/contracts")
    suspend fun getContracts(): List<Contract>

    @POST("contracts")
    fun createContract(@Body contract: Contract): Call<Void>


    @PUT("/contracts/{id}")
    suspend fun updateContract(@Path("id") id: String, @Body contract: Contract): Contract

    @DELETE("/contracts/{id}")
    suspend fun deleteContract(@Path("id") id: String): Unit
}
