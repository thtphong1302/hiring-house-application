package com.example.house_manager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.R

class Statistics_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        ToolbarHelper.setToolbar(this,"Thống Kê Doanh Số")
    }
}