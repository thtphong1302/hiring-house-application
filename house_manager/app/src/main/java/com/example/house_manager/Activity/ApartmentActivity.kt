package com.example.house_manager.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Adapters.ApartmentAdapter
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.ApartmentResponse
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApartmentActivity : AppCompatActivity() {

    private lateinit var apartmentAdapter: ApartmentAdapter
    private var apartmentArrayList: ArrayList<Apartment> = ArrayList()

    companion object {
        const val REQUEST_CODE_ADD_ROOM = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)
        ToolbarHelper.setToolbar(this, "Danh Sách Căn Hộ")
        setupRecyclerView()
        initImgAddApartment()
        getApartments()
        initSearch()
    }

    override fun onResume() {
        super.onResume()
        getApartments()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_ROOM && resultCode == Activity.RESULT_OK) {
            // Đã thêm phòng thành công, cập nhật lại danh sách phòng
            getApartments()
        }
    }

    private fun searchApartments(query: String) {
        val filteredApartments = ArrayList<Apartment>()
        for (apartment in apartmentArrayList) {
            if (apartment.departmentName.contains(query, ignoreCase = true)) {
                filteredApartments.add(apartment)
            }
        }
        apartmentAdapter.setApartments(filteredApartments)
    }

    private fun initSearch() {
        val searchButton = findViewById<Button>(R.id.btnSearch)
        val searchEditText = findViewById<EditText>(R.id.etSearch)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                searchApartments(query)
            } else {
                // If search query is empty, display all apartments
                apartmentAdapter.setApartments(apartmentArrayList)
            }
        }
    }
    private fun getApartments() {
        val call: Call<ApartmentResponse> = RetrofitInstance.apartmentService.getApartments()
        call.enqueue(object : Callback<ApartmentResponse> {
            override fun onResponse(call: Call<ApartmentResponse>, response: Response<ApartmentResponse>) {
                if (response.isSuccessful) {
                    val apartmentList: List<Apartment>? = response.body()?.result
                    if (apartmentList != null && apartmentList.isNotEmpty()) {
                        apartmentArrayList.clear() // Clear the list before adding new items
                        apartmentArrayList.addAll(apartmentList)
                        apartmentAdapter.setApartments(apartmentArrayList)
                        Log.d("API_RESPONSE", "Apartments fetched successfully: $apartmentList")
                    } else {
                        Log.d("API_RESPONSE", "No apartments found.")
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@ApartmentActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApartmentResponse>, t: Throwable) {
                Log.e("API_RESPONSE", "Error: ${t.message}", t)
                Toast.makeText(this@ApartmentActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteApartment(apartment: Apartment) {
        // Create an AlertDialog to confirm deletion
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xoá")
            .setMessage("Bạn có chắc chắn muốn xoá căn hộ này không?")
            .setPositiveButton("Xoá") { dialog, _ ->
                // Perform the deletion if the user confirms
                val call: Call<Void> = RetrofitInstance.apartmentService.deleteApartment(apartment.departmentName)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@ApartmentActivity, "Căn hộ đã được xoá thành công", Toast.LENGTH_SHORT).show()
                            apartmentArrayList.remove(apartment)
                            apartmentAdapter.setApartments(apartmentArrayList)
                        } else {
                            Toast.makeText(this@ApartmentActivity, "Xoá căn hộ thất bại", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@ApartmentActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.dismiss()
            }
            .setNegativeButton("Huỷ") { dialog, _ ->
                // Cancel the dialog if the user declines
                dialog.dismiss()
            }
            .show()
    }

    private fun setupRecyclerView() {
        apartmentAdapter = ApartmentAdapter { apartment -> deleteApartment(apartment) }
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@ApartmentActivity)
            adapter = apartmentAdapter
        }
    }

    private fun initImgAddApartment() {
        val imgAddApartment = findViewById<ImageView>(R.id.imgAdd_apartment)
        imgAddApartment?.setOnClickListener {
            startActivity(Intent(this, AddApartmentActivity::class.java))
        }
    }
}
