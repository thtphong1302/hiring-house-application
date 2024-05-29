//package com.example.house_manager.Activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import com.example.house_manager.Helper.ToolbarHelper
//import com.example.house_manager.Model.RoomEmpty
//import com.example.house_manager.Model.RoomStatus
//import com.example.house_manager.Network.RetrofitInstance
//import com.example.house_manager.R
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class AddRoomActivity : AppCompatActivity() {
//    private lateinit var retrofitInstance: RetrofitInstance
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_room)
//        ToolbarHelper.setToolbar(this, "Thêm Phòng")
//        retrofitInstance = RetrofitInstance
//        setOnclickbtnSaveRoom()
//    }
//
//    private fun setOnclickbtnSaveRoom() {
//        val btnSaveRoom = findViewById<Button>(R.id.btnSaveRoom)
//        btnSaveRoom.setOnClickListener {
//            val nameRoom = findViewById<EditText>(R.id.edtRoomName).text.toString()
//            val departmentName = findViewById<EditText>(R.id.edtApartmentName).text.toString()
//            val typeName = findViewById<EditText>(R.id.edtRoomType).text.toString()
//
//            if (nameRoom.isNotEmpty() && departmentName.isNotEmpty() && typeName.isNotEmpty() ) {
//                val room = RoomEmpty(nameRoom,typeName,departmentName,RoomStatus.EMPTY)
//                createRoom(room)
//            } else {
//                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin và giá trị hợp lệ.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun createRoom(room: Room) {
//
//
//        val call: Call<Room> = retrofitInstance.roomService.createRoom(room)
//        call.enqueue(object : Callback<Room> {
//            override fun onResponse(call: Call<Room>, response: Response<Room>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(this@AddRoomActivity, "Room added successfully!", Toast.LENGTH_SHORT).show()
//                    finish() // Close activity after successful submission
//                } else {
//                    Toast.makeText(this@AddRoomActivity, "Failed to add room", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Room>, t: Throwable) {
//                Toast.makeText(this@AddRoomActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
//}
