package com.example.house_manager.Network

import com.example.house_manager.Model.Contract
import retrofit2.Call
import retrofit2.http.*

interface ContractService {
    @POST("contracts")
    fun createContract(@Body contract: Contract): Call<Void>


    @PUT("contracts/{id}")
    fun updateContract(@Path("id") id: String, @Body contract: Contract): Contract

    @DELETE("contracts/{id}")
    fun deleteContract(@Path("id") id: String): Unit
}
