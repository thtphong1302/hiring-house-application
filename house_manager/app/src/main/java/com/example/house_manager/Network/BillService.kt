package com.example.house_manager.Network

import com.example.house_manager.Model.Bill
import retrofit2.http.*

interface BillService {
    @GET("/bills")
    suspend fun getBills(): List<Bill>

    @POST("/bills")
    suspend fun createBill(@Body bill: Bill): Bill

    @PUT("/bills/{id}")
    suspend fun updateBill(@Path("id") id: Int, @Body bill: Bill): Bill

    @DELETE("/bills/{id}")
    suspend fun deleteBill(@Path("id") id: Int): Unit
}