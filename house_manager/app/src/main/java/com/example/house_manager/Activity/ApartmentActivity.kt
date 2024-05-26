package com.example.house_manager.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Adapters.ApartmentAdapter
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.R
import kotlinx.android.synthetic.main.activity_apartment.*

class ApartmentActivity : AppCompatActivity() {

    private lateinit var apartmentAdapter: ApartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        // Initialize the UI components
        ToolbarHelper.setToolbar(this, "Danh sách các căn hộ")
        setupRecyclerView()
        setupButtonListeners()
        initImgAddApartment()
    }
    // Set up the RecyclerView with the adapter
    private fun setupRecyclerView() {
        apartmentAdapter = ApartmentAdapter()
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@ApartmentActivity)
            adapter = apartmentAdapter
        }
    }


    // Thiết lập button listen
    private fun setupButtonListeners() {
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            startActivity(Intent(this, Room_Activity::class.java))
        }
    }
    private fun initImgAddApartment(){
        val imgAddApartment = findViewById<ImageView>(R.id.imgAdd_apartment)
        imgAddApartment.setOnClickListener {
            startActivity(Intent(this, AddApartmentActivity::class.java))
        }
    }

}
