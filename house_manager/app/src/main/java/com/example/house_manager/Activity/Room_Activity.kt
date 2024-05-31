package com.example.house_manager.Activity

import RoomAdapterFragment
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.Apartment
import com.example.house_manager.Model.RoomResponse
import com.example.house_manager.R
import com.google.android.material.tabs.TabLayout

class Room_Activity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var departmentName: String // Khai báo biến để lưu trữ tên của căn hộ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
// Ví dụ: có thể lấy từ Intent hoặc từ nguồn dữ liệu khác
        // Thiết lập và cài đặt giao diện
        InitViews()
        // Thiết lập tab cho TabLayout
        setUpTabs()
        // Thiết lập ViewPager và gán adapter
        setUpViewPager()
        // Thiết lập Toolbar
        ToolbarHelper.setToolbar(this, "Danh sách căn phòng")

        // Thiết lập sự kiện khi nhấn vào ImageView imgAdd_room
        initImgAddRoom()
    }
    // Hàm này dùng để khởi tạo các thành phần giao diện
    private fun InitViews() {
        tabLayout = findViewById(R.id.tabRoom)
        viewPager = findViewById(R.id.pagerRoom)
    }

    // Hàm này dùng để thiết lập tab cho TabLayout
    private fun setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Phòng Trống"))
        tabLayout.addTab(tabLayout.newTab().setText("Phòng Đã Thuê"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }

    // Hàm này dùng để thiết lập ViewPager và gán adapter
    private fun setUpViewPager() {
        val adapter = RoomAdapterFragment(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    // Hàm này dùng để thiết lập sự kiện khi nhấn vào ImageView imgAdd_room
    private fun initImgAddRoom() {
        val imgAddroom = findViewById<ImageView>(R.id.imgAddroom)
        imgAddroom.setOnClickListener {
            // Mở AddRoomActivity để thêm phòng
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivityForResult(intent, ADD_ROOM_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_ROOM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Reload danh sách phòng ở đây
            // Ví dụ: refreshFragment1()
        }
    }

    companion object {
        const val ADD_ROOM_REQUEST_CODE = 1001
    }
}
