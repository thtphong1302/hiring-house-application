package com.example.house_manager.Fragments

import RoomEmptyAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.Room
import com.example.house_manager.Model.RoomStatus
import com.example.house_manager.Model.Room_type
import com.example.house_manager.R

class Room_fragment1 : Fragment() {

    private val roomsList = listOf(
        Room("101", RoomStatus.EMPTY, Apartment("A1", 100, 50), Room_type("Standard", 20, 500)),
        // Thêm các phòng khác vào đây
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room_fragment1, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRooms)
        val adapter = RoomEmptyAdapter(roomsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}
