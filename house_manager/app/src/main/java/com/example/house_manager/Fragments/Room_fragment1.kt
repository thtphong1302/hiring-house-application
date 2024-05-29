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
import com.example.house_manager.Adapters.RoomEmptyAdapter
import com.example.house_manager.Model.*
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Room_fragment1 : Fragment() {

    private var roomsList: ArrayList<Room> = ArrayList()
    private var roomTypeList: ArrayList<Room_type> = ArrayList()
    private lateinit var adapter: RoomEmptyAdapter
    private var apartmentArrayList: ArrayList<Apartment> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room_fragment1, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRooms)
        adapter = RoomEmptyAdapter(roomsList, roomTypeList, apartmentArrayList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch initial room types

        // Fetch apartments and then fetch empty rooms
        getApartments()
        return view
    }

    private fun getApartments() {
        val call: Call<ApartmentResponse> = RetrofitInstance.apartmentService.getApartments()
        call.enqueue(object : Callback<ApartmentResponse> {
            override fun onResponse(
                call: Call<ApartmentResponse>,
                response: Response<ApartmentResponse>
            ) {
                if (response.isSuccessful) {
                    val apartmentList: List<Apartment>? = response.body()?.result
                    if (apartmentList != null && apartmentList.isNotEmpty()) {
                        apartmentArrayList.clear()
                        apartmentArrayList.addAll(apartmentList)

                        // Get the list of department names from the apartment list
                        val departmentNames = getApartmentNames(apartmentList)

                        // After having the list of department names, call fetchEmptyRooms
                        fetchEmptyRooms(departmentNames)
                    } else {
                        Log.d("API_RESPONSE", "No apartments found.")
                    }
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApartmentResponse>, t: Throwable) {
                Log.e("API_RESPONSE", "Error: ${t.message}", t)
            }
        })
    }

    private fun fetchEmptyRooms(departmentNames: List<String>) {
        for (departmentName in departmentNames) {
            val call: Call<RoomResponse> =
                RetrofitInstance.roomService.getEmptyRooms(departmentName)
            call.enqueue(object : Callback<RoomResponse> {
                override fun onResponse(
                    call: Call<RoomResponse>,
                    response: Response<RoomResponse>
                ) {
                    if (response.isSuccessful) {
                        val roomResponse: RoomResponse? = response.body()
                        val roomList: List<Room>? = roomResponse?.result

                        roomList?.let {
                            if (it.isNotEmpty()) {
                                roomsList.addAll(it)
                                adapter.setRooms(roomsList)
                                Log.d("API_RESPONSE", "Rooms fetched successfully: $it")
                            } else {
                                Log.d(
                                    "API_RESPONSE",
                                    "No rooms found for department: $departmentName"
                                )
                            }
                        }
                    } else {
                        Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                        Toast.makeText(
                            requireContext(),
                            "Error fetching data for $departmentName",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                    Log.e("API_RESPONSE", "Error: ${t.message}", t)
                    Toast.makeText(
                        requireContext(),
                        "Error fetching data for $departmentName",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    private fun getApartmentNames(apartmentList: List<Apartment>): List<String> {
        val apartmentNames = mutableListOf<String>()
        for (apartment  in apartmentList) {
            apartmentNames.add(apartment.departmentName)
        }
        return apartmentNames
    }
}
