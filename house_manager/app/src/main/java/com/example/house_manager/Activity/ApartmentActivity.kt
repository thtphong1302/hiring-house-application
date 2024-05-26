package com.example.house_manager.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Adapters.ApartmentAdapter
import com.example.house_manager.R

class ApartmentActivity : AppCompatActivity() {

    private lateinit var apartmentAdapter: ApartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        // Initialize the UI components
        setupToolbar()
        setupRecyclerView()
        setupButtonListeners()

    }
    // Set up the toolbar with back button
    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
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
    // xử lý nút quay trở lại
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
