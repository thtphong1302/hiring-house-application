package com.example.house_manager.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Adapters.RoomOccupiedAdapter
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Model.RoomResponse
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Room_fragment2 : Fragment() {
    private lateinit var adapter: RoomOccupiedAdapter
    private val roomsList: ArrayList<RoomEmpty> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room_fragment2, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewOccupied)
        adapter = RoomOccupiedAdapter(roomsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getDoneRoom("NGUYEN DINH GIAP")
        return view
    }

    private fun getDoneRoom(departmentName: String) {
        val call: Call<RoomResponse> = RetrofitInstance.roomService.getDoneRooms(departmentName)
        call.enqueue(object : Callback<RoomResponse> {
            override fun onResponse(call: Call<RoomResponse>, response: Response<RoomResponse>) {
                if (response.isSuccessful) {
                    val roomList: List<RoomEmpty>? = response.body()?.result
                    if (roomList != null && roomList.isNotEmpty()) {
                        roomsList.clear()
                        roomsList.addAll(roomList)
                        adapter.setRooms(roomsList)
                        Log.d("API_RESPONSE", "Rooms fetched successfully: $roomList")
                    } else {
                        Log.d("API_RESPONSE", "No rooms found")
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                Log.e("API_RESPONSE", "Error: ${t.message}", t)
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}