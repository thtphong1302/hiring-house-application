package com.example.house_manager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.R
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.house_manager.Model.Contract
import com.example.house_manager.Network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddContractActivity : AppCompatActivity() {
//    private lateinit var personName: EditText
//    private lateinit var btnSave: Button
//    private var isEditing: Boolean = false
//    private var originalName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contract)

        ToolbarHelper.setToolbar(this, "Tạo Hợp Đồng")
//        initViews()
////        initData()
////        setupSaveButton()
//    }
//
//    private fun initViews() {
//        personName = findViewById(R.id.editTextTextPersonName)
//        btnSave = findViewById(R.id.button)
//    }

//    private fun initData() {
//        val intent = intent
//        if (intent.hasExtra("person_name")) {
//            isEditing = true
//            ToolbarHelper.setToolbar(this, "Cập Nhật Hợp Đồng")
//            originalName = intent.getStringExtra("person_name")
//            personName.setText(originalName)
//            // Add more fields here if needed
//            btnSave.text = "Update"
//        } else {
//            isEditing = false
//            btnSave.text = "Save"
//        }
//    }

//    private fun setupSaveButton() {
//        btnSave.setOnClickListener {
//            val name = personName.text.toString()
//
//            if (name.isNotEmpty()) {
//                val contract = Contract(name)
//                if (isEditing) {
//                    updateContract(originalName!!, contract)
//                } else {
//                    createContract(contract)
//                }
//            } else {
//                Toast.makeText(this, "Please fill all fields with valid data", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun createContract(contract: Contract) {
//        val call: Call<Contract> = RetrofitInstance.contractService.createContract(contract)
//        call.enqueue(object : Callback<Contract> {
//            override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(this@AddContractActivity, "Contract added successfully!", Toast.LENGTH_SHORT).show()
//                    finish()
//                } else {
//                    Toast.makeText(this@AddContractActivity, "Failed to add contract", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Contract>, t: Throwable) {
//                Toast.makeText(this@AddContractActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

//    private fun updateContract(originalName: String, contract: Contract) {
//        val call: Call<Contract> = RetrofitInstance.contractService.updateContract(originalName, contract)
//        call.enqueue(object : Callback<Contract> {
//            override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(this@AddContractActivity, "Contract updated successfully!", Toast.LENGTH_SHORT).show()
//                    finish()
//                } else {
//                    Toast.makeText(this@AddContractActivity, "Failed to update contract", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Contract>, t: Throwable) {
//                Toast.makeText(this@AddContractActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }}
    }}
