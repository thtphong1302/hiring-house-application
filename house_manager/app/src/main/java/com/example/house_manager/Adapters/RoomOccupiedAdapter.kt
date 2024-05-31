package com.example.house_manager.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.R

class RoomOccupiedAdapter(private var rooms: List<RoomEmpty>) : RecyclerView.Adapter<RoomOccupiedAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNameTextView: TextView = itemView.findViewById(R.id.txtNameRoom)
        val statusTextView: TextView = itemView.findViewById(R.id.txtStatus)
        val roomPrice: TextView = itemView.findViewById(R.id.txtFeeRoom)
//        val txtSdt: TextView = itemView.findViewById(R.id.txtSdt)
//        val txtNameResident: TextView = itemView.findViewById(R.id.txtResident)
//        private val txtSemContract: TextView = itemView.findViewById(R.id.txtSeenContract)
        init {
//            txtSemContract.setOnClickListener {
//                val context = itemView.context
//                val intent = Intent(context, com.example.house_manager.Activity.AddContractActivity::class.java)
//                context.startActivity(intent)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_roomoccupied, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoom = rooms[position]
        holder.roomNameTextView.text = currentRoom.roomName
        holder.statusTextView.text = currentRoom.status
        holder.roomPrice.text = currentRoom.roomTypeResponse.price.toString()

    }

    override fun getItemCount() = rooms.size

    fun setRooms(rooms: List<RoomEmpty>) {
        this.rooms = rooms
        notifyDataSetChanged()
    }
}