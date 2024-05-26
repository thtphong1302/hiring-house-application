package com.example.house_manager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Model.Apartment
import com.example.house_manager.R

class ApartmentAdapter: RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {
    private var apartments = emptyList<Apartment>()
    inner class ApartmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val apartment_name: TextView = itemView.findViewById(R.id.txtNameRoom)
            val electric_price: TextView = itemView.findViewById(R.id.txtelectric)
            val water_price: TextView = itemView.findViewById(R.id.txtwater)
            val imgbtndelete: ImageButton = itemView.findViewById(R.id.imgbtndelete)
            val imgbtnedit: ImageButton = itemView.findViewById(R.id.imgbtnedit)
            val txtStk: TextView = itemView.findViewById(R.id.txtstk)
            val txtNameNH: TextView = itemView.findViewById(R.id.txtnameNH)
            val txtNameKH: TextView = itemView.findViewById(R.id.txtnameTK)

    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apartment,parent, false)
            return ApartmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        var apartment = apartments[position]
        holder.apartment_name.text =apartment.apartment_name
        holder.electric_price.text = apartment.electric_price.toString()
        holder.water_price.text = apartment.water_price.toString()

        holder.imgbtndelete.setOnClickListener {
            onDeleteClick(apartment)
        }

    }

    private fun onDeleteClick(apartment: Apartment) {

    }

    override fun getItemCount(): Int {
        return apartments.size
    }
    fun setApartments(apartments: List<Apartment>){
            this.apartments = apartments as ArrayList<Apartment>
                notifyDataSetChanged()
    }
}