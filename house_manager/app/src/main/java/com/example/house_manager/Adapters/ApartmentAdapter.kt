package com.example.house_manager.Adapters

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Activity.AddApartmentActivity
import com.example.house_manager.Activity.Room_Activity
import com.example.house_manager.Model.Apartment
import com.example.house_manager.R

class ApartmentAdapter(private val onDeleteClick: (Apartment) -> Unit) : RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {
    private var apartments: List<Apartment> = emptyList()

    inner class ApartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val apartmentName: TextView = itemView.findViewById(R.id.txtNameRoom)
        val electricPrice: TextView = itemView.findViewById(R.id.txtelectric)
        val waterPrice: TextView = itemView.findViewById(R.id.txtwater)
        val deleteButton: ImageButton = itemView.findViewById(R.id.imgbtndelete)
        val editButton: ImageButton = itemView.findViewById(R.id.imgbtnedit)
        val listRoomButton: TextView = itemView.findViewById(R.id.btnListRoom)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val apartment = apartments[position]
                    onDeleteClick(apartment)
                }
            }

            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val apartment = apartments[position]
                    val context = itemView.context
                    val intent = Intent(context, AddApartmentActivity::class.java).apply {
                        putExtra("apartment_name", apartment.apartment_name)
                        putExtra("electric_price", apartment.electric_price)
                        putExtra("water_price", apartment.water_price)
                    }
                    context.startActivity(intent)
                }
            }

            listRoomButton.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, Room_Activity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apartment, parent, false)
        return ApartmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val apartment = apartments[position]

        holder.apartmentName.text = apartment.apartment_name
        holder.electricPrice.text = apartment.electric_price.toString()
        holder.waterPrice.text = apartment.water_price.toString()
    }

    override fun getItemCount(): Int = apartments.size

    fun setApartments(apartments: List<Apartment>) {
        this.apartments = apartments
        notifyDataSetChanged()
    }
}
