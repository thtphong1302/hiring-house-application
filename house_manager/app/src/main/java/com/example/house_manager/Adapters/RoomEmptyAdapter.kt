package com.example.house_manager.Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Activity.AddContractActivity
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.Room
import com.example.house_manager.Model.Room_type
import com.example.house_manager.R

class RoomEmptyAdapter(
    private var rooms: List<Room>,
    private var roomTypes: List<Room_type>,
    private var apartment: List<Apartment>
) : RecyclerView.Adapter<RoomEmptyAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNameTextView: TextView = itemView.findViewById(R.id.txtNameRoom)
        val statusTextView: TextView = itemView.findViewById(R.id.txtStatus)
        val feeRoom: TextView = itemView.findViewById(R.id.txtFeeRoom)
        val feeService: TextView = itemView.findViewById(R.id.txtFeeService)
        private val btnContract: Button = itemView.findViewById(R.id.btnContract)

        init {
            btnContract.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, AddContractActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_roomempty, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoom = rooms[position]


                // Hiển thị thông tin của phòng nếu tên bộ phận trùng khớp
                holder.roomNameTextView.text = currentRoom.roomName
                holder.statusTextView.text = currentRoom.status.toString()



            }




//        /*
//        if (currentRoomType != null) {
//            // Hiển thị thông tin phòng và loại phòng nếu loại phòng được tìm thấy
//            holder.roomNameTextView.text = currentRoom.roomName
//            holder.statusTextView.text = currentRoom.status.toString()
//            holder.feeService.text = currentRoomType.feeService.toString()
//            holder.feeRoom.text = currentRoomType.price.toString()
//        } else {
//            // Xử lý trường hợp không tìm thấy loại phòng tương ứng
//            Log.d("Adapter_Debug", "No matching room type found for room: ${currentRoom.roomName}")
//            holder.roomNameTextView.text = currentRoom.roomName
//            holder.statusTextView.text = currentRoom.status.toString()
//            holder.feeService.text = "N/A"
//            holder.feeRoom.text = "N/A"
//        }


    override fun getItemCount() = rooms.size

    fun setRooms(rooms: List<Room>) {
        this.rooms = rooms
        notifyDataSetChanged()
    }

    fun setRoomTypes(roomTypes: List<Room_type>) {
        this.roomTypes = roomTypes
        notifyDataSetChanged()
    }
}
