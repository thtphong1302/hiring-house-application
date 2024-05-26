package com.example.house_manager.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Adapters.ApartmentAdapter
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApartmentActivity : AppCompatActivity() {

    private lateinit var apartmentAdapter: ApartmentAdapter
    private var apartmentArrayList: ArrayList<Apartment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        setupRecyclerView()
        setupButtonListeners()
        initImgAddApartment()
        getApartments()
    }

    private fun getApartments() {
        val call: Call<List<Apartment>> = RetrofitInstance.apartmentService.getApartments()
        call.enqueue(object : Callback<List<Apartment>> {
            override fun onResponse(call: Call<List<Apartment>>, response: Response<List<Apartment>>) {
                if (response.isSuccessful) {
                    val apartments = response.body()
                    if (apartments != null) {
                        apartmentArrayList.addAll(apartments)
                        apartmentAdapter.setApartments(apartmentArrayList)
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Apartment>>, t: Throwable) {
                Log.e("API_RESPONSE", "Error: ${t.message}", t)
            }
        })
    }

    private fun deleteApartment(apartment: Apartment) {
        val call: Call<Void> = RetrofitInstance.apartmentService.deleteApartment(apartment.apartment_name)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ApartmentActivity, "Apartment deleted successfully!", Toast.LENGTH_SHORT).show()
                    apartmentArrayList.remove(apartment)
                    apartmentAdapter.setApartments(apartmentArrayList)
                } else {
                    Toast.makeText(this@ApartmentActivity, "Failed to delete apartment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ApartmentActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        apartmentAdapter = ApartmentAdapter { apartment -> deleteApartment(apartment as Apartment) }
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@ApartmentActivity)
            adapter = apartmentAdapter
        }
    }

    private fun setupButtonListeners() {
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            startActivity(Intent(this, Room_Activity::class.java))
        }
    }

    private fun initImgAddApartment() {
        val imgAddApartment = findViewById<ImageView>(R.id.imgAdd_apartment)
        imgAddApartment.setOnClickListener {
            startActivity(Intent(this, AddApartmentActivity::class.java))
        }
    }
}
