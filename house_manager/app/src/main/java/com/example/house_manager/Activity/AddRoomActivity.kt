package com.example.house_manager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.*
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRoomActivity : AppCompatActivity() {
    private lateinit var retrofitInstance: RetrofitInstance
    private lateinit var departmentName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)
        ToolbarHelper.setToolbar(this, "Thêm Phòng")
        retrofitInstance = RetrofitInstance

        departmentName = intent.getStringExtra("DEPARTMENT_NAME") ?: ""
        val edtApartmentName = findViewById<EditText>(R.id.edtApartmentName)
        edtApartmentName.setText(departmentName)

        setOnClickBtnSaveRoom()
        setupSpinner()
    }

    private fun setOnClickBtnSaveRoom() {
        val btnSaveRoom = findViewById<Button>(R.id.btnSaveRoom)
        btnSaveRoom.setOnClickListener {
            val nameRoom = findViewById<EditText>(R.id.edtRoomName).text.toString()
            val spinnerRoomType = findViewById<Spinner>(R.id.spinnerRoomType)
            val selectedType = spinnerRoomType.selectedItem?.toString() ?: ""
            val departmentName = findViewById<EditText>(R.id.edtApartmentName).text.toString()
            if (nameRoom.isNotEmpty() && departmentName.isNotEmpty() && selectedType.isNotEmpty()) {
                val room = Room(nameRoom, selectedType, departmentName)
                createRoom(room)
            } else {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin và giá trị hợp lệ.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createRoom(room: Room) {
        val call: Call<Void> = retrofitInstance.roomService.createRoom(room)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddRoomActivity, "Thêm Phòng Thành Công", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // Log the error message
                    Log.e("CREATE_ROOM_ERROR", "Failed to add room: ${response.code()}")
                    Toast.makeText(this@AddRoomActivity, "Failed to add room", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Log the exception message
                Log.e("CREATE_ROOM_ERROR", "An error occurred: ${t.message}", t)
                Toast.makeText(this@AddRoomActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupSpinner() {
        val spinnerRoomType = findViewById<Spinner>(R.id.spinnerRoomType)

        // Call the service to get room types
        retrofitInstance.roomTypeService.getRoomTypes().enqueue(object : Callback<RoomType> {
            override fun onResponse(call: Call<RoomType>, response: Response<RoomType>) {
                if (response.isSuccessful) {
                    val roomType = response.body()

                    // Check if room types are retrieved successfully
                    if (roomType != null && roomType.result.isNotEmpty()) {
                        // Extract type names from the response
                        val typeNames = roomType.result.map { it.typeName }

                        // Create an ArrayAdapter and set it to the spinner
                        val adapter = ArrayAdapter(this@AddRoomActivity, android.R.layout.simple_spinner_item, typeNames)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerRoomType.adapter = adapter
                    } else {
                        Toast.makeText(this@AddRoomActivity, "No room types found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@AddRoomActivity, "Failed to retrieve room types", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RoomType>, t: Throwable) {
                Toast.makeText(this@AddRoomActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
