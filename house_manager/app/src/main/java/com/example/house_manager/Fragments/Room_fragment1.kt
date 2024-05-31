package com.example.house_manager.Fragments

import RoomAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Model.RoomResponse
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Room_fragment1 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterEmpty: RoomAdapter
    private var apartmentArrayList: ArrayList<Apartment> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewRooms)
        adapterEmpty = RoomAdapter(requireContext(), mutableListOf()) // Initialize adapter with an empty list
        recyclerView.adapter = adapterEmpty
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Nhận tên căn hộ từ Intent và gọi getRoomEmpty
        val apartmentName = activity?.intent?.getStringExtra("APARTMENT_NAME")
        apartmentName?.let {
            fetchEmptyRooms(listOf(it))
        } ?: ApiUtils.getApartments { apartmentList ->
            fetchEmptyRooms(apartmentList.map { it.departmentName })
        }
    }

    private fun fetchEmptyRooms(departmentNames: List<String>) {
        for (departmentName in departmentNames) {
            val call: Call<RoomResponse> = RetrofitInstance.roomService.getEmptyRooms(departmentName)
            call.enqueue(object : Callback<RoomResponse> {
                override fun onResponse(call: Call<RoomResponse>, response: Response<RoomResponse>) {
                    if (response.isSuccessful) {
                        val roomResponse: RoomResponse? = response.body()
                        val roomList: List<RoomEmpty>? = roomResponse?.result
                        roomList?.let {
                            adapterEmpty.setData(it)
                        }
                    } else {
                        Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                        Toast.makeText(requireContext(), "Error fetching data for $departmentName", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                    Log.e("API_RESPONSE", "Error: ${t.message}", t)
                    Toast.makeText(requireContext(), "Error fetching data for $departmentName", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }


}