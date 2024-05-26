package com.example.house_manager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.R
import kotlinx.android.synthetic.main.activity_room.*

class AddRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)
        ToolbarHelper.setToolbar(this, "Thêm Phòng")
    }
}