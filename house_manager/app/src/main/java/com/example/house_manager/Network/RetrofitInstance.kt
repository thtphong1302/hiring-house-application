package com.example.house_manager.Network

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
                //http://192.168.1.9:8080/api/v1/
            .baseUrl("http://192.168.1.4:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apartmentService: ApartmentService by lazy {
        retrofit.create(ApartmentService::class.java)
    }

    val roomTypeService: RoomTypeService by lazy {
        retrofit.create(RoomTypeService::class.java)
    }

    val contractService: ContractService by lazy {
        retrofit.create(ContractService::class.java)
    }

    val roomService: RoomService by lazy {
        retrofit.create(RoomService::class.java)
    }

    val billService: BillService by lazy {
        retrofit.create(BillService::class.java)
    }

    val contractRoomsService: ContractRoomsService by lazy {
        retrofit.create(ContractRoomsService::class.java)
    }

    val residentService: ResidentService by lazy {
        retrofit.create(ResidentService::class.java)
    }
}
