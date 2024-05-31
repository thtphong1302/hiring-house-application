package com.example.house_manager.Fragments

import android.util.Log
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.ApartmentResponse
import com.example.house_manager.Network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiUtils {
    fun getApartments(callback: (List<Apartment>) -> Unit) {
        val call: Call<ApartmentResponse> = RetrofitInstance.apartmentService.getApartments()
        call.enqueue(object : Callback<ApartmentResponse> {
            override fun onResponse(call: Call<ApartmentResponse>, response: Response<ApartmentResponse>) {
                if (response.isSuccessful) {
                    val apartmentList: List<Apartment>? = response.body()?.result
                    if (apartmentList != null && apartmentList.isNotEmpty()) {
                        callback(apartmentList)
                    } else {
                        Log.d("API_RESPONSE", "No apartments found.")
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApartmentResponse>, t: Throwable) {
                Log.e("API_RESPONSE", "Error: ${t.message}", t)
            }
        })
    }

    fun getApartmentNames(apartmentList: List<Apartment>): List<String> {
        val apartmentNames = mutableListOf<String>()
        for (apartment in apartmentList) {
            apartmentNames.add(apartment.departmentName)
        }
        return apartmentNames
    }
}

