import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.Room
import com.example.house_manager.R

class RoomEmptyAdapter(private val rooms: List<Room>) : RecyclerView.Adapter<RoomEmptyAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNameTextView: TextView = itemView.findViewById(R.id.txtNameRoom)
        val statusTextView: TextView = itemView.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_roomempty, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoom = rooms[position]
        holder.roomNameTextView.text = currentRoom.room_name
        holder.statusTextView.text = currentRoom.status.toString()
    }

    override fun getItemCount() = rooms.size
}
