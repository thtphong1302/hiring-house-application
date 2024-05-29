package com.example.house_manager.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomAdapter(private val context: Context, private var roomList: List<RoomEmpty>) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtRoomName: TextView = itemView.findViewById(R.id.txtNameRoom)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        val txtFeeRoom: TextView = itemView.findViewById(R.id.txtFeeRoom)
        val txtFeeService: TextView = itemView.findViewById(R.id.txtFeeService)
        val btnContract: Button = itemView.findViewById(R.id.btnContract)
        val imgDel: ImageButton = itemView.findViewById(R.id.imgbtnDelRoom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_roomempty, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentItem = roomList[position]

        holder.txtRoomName.text = currentItem.roomName
        holder.txtStatus.text = currentItem.status
        holder.txtFeeRoom.text = "Giá tiền: ${currentItem.roomTypeResponse.price}"
        holder.txtFeeService.text = "Phí dịch vụ: ${currentItem.roomTypeResponse.feeService}"

        holder.btnContract.setOnClickListener {
            // Handle button click action here
        }
        holder.imgDel.setOnClickListener {
            confirmDeleteRoom(currentItem.roomName, position)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun setData(newRoomList: List<RoomEmpty>) {
        roomList = newRoomList
        notifyDataSetChanged()
    }

    private fun confirmDeleteRoom(roomName: String, position: Int) {
        AlertDialog.Builder(context)
            .setTitle("Xác nhận xoá")
            .setMessage("Bạn có chắc chắn muốn xoá phòng này không?")
            .setPositiveButton("Xoá") { dialog, _ ->
                deleteRoom(roomName, position)
                dialog.dismiss()
            }
            .setNegativeButton("Huỷ") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteRoom(roomName: String, position: Int) {
        val call: Call<Void> = RetrofitInstance.roomService.deleteRoom(roomName)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    (roomList as MutableList).removeAt(position)
                    notifyItemRemoved(position)
                    Toast.makeText(context, "Phòng đã được xoá thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Xoá phòng thất bại", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
