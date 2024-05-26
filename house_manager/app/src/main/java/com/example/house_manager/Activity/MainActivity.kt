package com.example.house_manager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.house_manager.R

class MainActivity : AppCompatActivity() {
    private lateinit var imgManager: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Khởi tạo giao diện và thiết lập sự kiện
        initViews()
    }
    // Phương thức này dùng để khởi tạo giao diện và thiết lập sự kiện cho các thành phần giao diện
    private fun initViews() {
        imgManager = findViewById(R.id.imgManager)
        imgManager.setOnClickListener {
            launchApartmentActivity()
        }
    }
    // Phương thức này dùng để khởi động Apartment_Activity
    private fun launchApartmentActivity() {
        val intent = Intent(this, ApartmentActivity::class.java)
        startActivity(intent)
    }
}
