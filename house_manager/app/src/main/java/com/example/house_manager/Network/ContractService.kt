package com.example.house_manager.Network

import com.example.house_manager.Model.Contract
import com.example.house_manager.Model.ContractResponse
import retrofit2.Call
import retrofit2.http.*

interface ContractService {
    @GET("contracts/{contractID}")
    fun getContract(@Path("contractID") contractID: String): Call<ContractResponse>
    @POST("contracts")
    fun createContract(@Body contract: Contract): Call<Void>


    @PUT("/ontracts/{id}")
    suspend fun updateContract(@Path("id") id: String, @Body contract: Contract): Contract

    @DELETE("/contracts/{id}")
    suspend fun deleteContract(@Path("id") id: String): Unit
}
