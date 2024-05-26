package com.example.house_manager.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddApartmentActivity : AppCompatActivity() {
    private lateinit var retrofitInstance: RetrofitInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_apartment)
        ToolbarHelper.setToolbar(this, "Thêm mới căn hộ")
        retrofitInstance = RetrofitInstance

        setupSaveButtonListener()
    }

    private fun setupSaveButtonListener() {
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            val name = findViewById<EditText>(R.id.edtApartmentName).text.toString()
            val electricPrice = findViewById<EditText>(R.id.edtElectricPrice).text.toString().toIntOrNull()
            val waterPrice = findViewById<EditText>(R.id.edtWaterPrice).text.toString().toIntOrNull()

            if (name.isNotEmpty() && electricPrice != null && waterPrice != null) {
                val apartment = Apartment(name, electricPrice, waterPrice)
                createApartment(apartment)
            } else {
                Toast.makeText(this, "Please fill all fields with valid data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createApartment(apartment: Apartment) {
        val call: Call<Apartment> = retrofitInstance.apartmentService.createApartment(apartment)
        call.enqueue(object : Callback<Apartment> {
            override fun onResponse(call: Call<Apartment>, response: Response<Apartment>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddApartmentActivity, "Apartment added successfully!", Toast.LENGTH_SHORT).show()
                    finish() // Close activity after successful submission
                } else {
                    Toast.makeText(this@AddApartmentActivity, "Failed to add apartment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Apartment>, t: Throwable) {
                Toast.makeText(this@AddApartmentActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
