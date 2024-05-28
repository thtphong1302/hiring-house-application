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
        // gọi màn hình apartment
        launchApartmentActivity()
        // gọi màn hình thống kế
        launchStatisticsActivity()

    }

    private fun launchApartmentActivity() {
        val imgManager = findViewById<ImageView>(R.id.imgManager)
        imgManager.setOnClickListener {
            startActivity(Intent(this, ApartmentActivity::class.java))
        }
    }
    private fun launchStatisticsActivity(){
        val imgStatistics = findViewById<ImageView>(R.id.imgStatistics)
        imgStatistics.setOnClickListener {
            startActivity(Intent(this, Statistics_Activity::class.java))}
    }
}
