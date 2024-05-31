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
import com.example.house_manager.Fragments.ApiUtils.getApartmentNames
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Model.RoomResponse
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Room_fragment2 : Fragment() {
    private lateinit var adapterOccupiedAdapter: RoomOccupiedAdapter
    private val roomsList: ArrayList<RoomEmpty> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room_fragment2, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewOccupied)
        adapterOccupiedAdapter = RoomOccupiedAdapter(roomsList)
        recyclerView.adapter = adapterOccupiedAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val apartmentName = activity?.intent?.getStringExtra("APARTMENT_NAME")
        apartmentName?.let {
            getDoneRoom(listOf(it))
        } ?: ApiUtils.getApartments { apartmentList ->
            val apartmentNames = getApartmentNames(apartmentList)
            getDoneRoom(apartmentNames)
        }

        return view
    }

    private fun getDoneRoom(departmentNames: List<String>) {
        for (departmentName in departmentNames) {
            val call: Call<RoomResponse> = RetrofitInstance.roomService.getDoneRooms(departmentName)
            call.enqueue(object : Callback<RoomResponse> {
                override fun onResponse(call: Call<RoomResponse>, response: Response<RoomResponse>) {
                    if (response.isSuccessful) {
                        val roomResponse: RoomResponse? = response.body()
                        val roomList: List<RoomEmpty>? = roomResponse?.result
                        roomList?.let {
                            roomsList.addAll(it) // Thêm dữ liệu từ mỗi cuộc gọi API vào danh sách
                            adapterOccupiedAdapter.setRooms(roomsList) // Cập nhật dữ liệu cho Adapter
                        }
                    } else {
                        Log.d("API_RESPONSE", "No rooms found")
                    }
                }

                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                    Log.e("API_RESPONSE", "Error: ${t.message}", t)
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}
