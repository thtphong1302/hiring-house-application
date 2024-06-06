package com.example.house_manager.Network

import com.example.house_manager.Model.Bill
import retrofit2.http.*
// chưa thực hiện được chức năng này

interface BillService {
    @GET("bills")
    fun getBills(): List<Bill>

    @POST("bills")
    fun createBill(@Body bill: Bill): Bill

    @PUT("bills/{id}")
    fun updateBill(@Path("id") id: Int, @Body bill: Bill): Bill

    @DELETE("bills/{id}")
    fun deleteBill(@Path("id") id: Int): Unit
}