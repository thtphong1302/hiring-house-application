package com.example.house_manager.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.house_manager.Adapters.ApartmentAdapter
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import kotlinx.android.synthetic.main.activity_add_apartment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddApartmentActivity : AppCompatActivity() {
    private lateinit var apartmentAdapter: ApartmentAdapter
    private var apartmentArrayList: ArrayList<Apartment> = ArrayList()
    private lateinit var apartmentName: EditText
    private lateinit var electricPrice: EditText
    private lateinit var waterPrice: EditText
    private lateinit var btnSave: Button
    private var isEditing: Boolean = false
    private var originalName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_apartment)
        var toolbarTitle = "Thêm Căn Hộ"
        ToolbarHelper.setToolbar(this, toolbarTitle)
        initViews()
        initData()
        setupSaveButton()
    }

    private fun initViews() {
        apartmentName = findViewById(R.id.edtApartmentName)
        electricPrice = findViewById(R.id.edtElectricPrice)
        waterPrice = findViewById(R.id.edtWaterPrice)
        btnSave = findViewById(R.id.btnSave)
    }

    private fun initData() {
        val intent = intent
        if (intent.hasExtra("apartment_name")) {
            // Editing existing apartment
            isEditing = true
            ToolbarHelper.setToolbar(this,"Cập Nhật Căn Hộ")
            originalName = intent.getStringExtra("apartment_name")
            apartmentName.setText(originalName)
            electricPrice.setText(intent.getIntExtra("electric_price", 0).toString())
            waterPrice.setText(intent.getIntExtra("water_price", 0).toString())
            btnSave.setText("Update")
        } else {
            // Adding new apartment
            isEditing = false
            btnSave.setText("Save")
        }
    }

    private fun setupSaveButton() {
        btnSave.setOnClickListener {
            val name = apartmentName.text.toString()
            val electricPrice = electricPrice.text.toString().toIntOrNull()
            val waterPrice = waterPrice.text.toString().toIntOrNull()

            if (name.isNotEmpty() && electricPrice != null && waterPrice != null) {
                val apartment = Apartment(name, electricPrice, waterPrice)
                if (isEditing) {
                    updateApartment(originalName!!, apartment)
                } else {
                    createApartment(apartment)
                }
            } else {
                Toast.makeText(this, "Please fill all fields with valid data", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun createApartment(apartment: Apartment) {
        val call: Call<Apartment> = RetrofitInstance.apartmentService.createApartment(apartment)
        call.enqueue(object : Callback<Apartment> {
            override fun onResponse(call: Call<Apartment>, response: Response<Apartment>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddApartmentActivity, "Apartment added successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@AddApartmentActivity, "Failed to add apartment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Apartment>, t: Throwable) {
                Toast.makeText(this@AddApartmentActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateApartment(originalName: String, apartment: Apartment) {
        val call: Call<Apartment> = RetrofitInstance.apartmentService.updateApartment(originalName, apartment)
        call.enqueue(object : Callback<Apartment> {
            override fun onResponse(call: Call<Apartment>, response: Response<Apartment>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddApartmentActivity, "Apartment updated successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("API_UPDATE_RESPONSE", "Error: $errorBody")
                    Toast.makeText(this@AddApartmentActivity, "Failed to update apartment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Apartment>, t: Throwable) {
                Log.e("API_UPDATE_FAILURE", "Error: ${t.message}", t)
                Toast.makeText(this@AddApartmentActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
