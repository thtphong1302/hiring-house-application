package com.example.house_manager.Activity

import RoomAdapterFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.house_manager.R
import com.google.android.material.tabs.TabLayout

class Room_Activity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        // Thiết lập và cài đặt giao diện
        InitViews()
        // Thiết lập tab cho TabLayout
        setUpTabs()
        // Thiết lập ViewPager và gán adapter
        setUpViewPager()
        // Thiết lập action bar và nút back
        setSupportActionBar()
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
    // Hàm này dùng để thiết lập action bar và nút back
    private fun setSupportActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Xử lý sự kiện khi nút back được nhấn
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
