package com.example.house_manager.Activity

import RoomAdapter
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.house_manager.Helper.ToolbarHelper
import com.example.house_manager.Model.Contract
import com.example.house_manager.Model.Resident
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.android.synthetic.main.activity_add_contract.*


import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddContractActivity : AppCompatActivity() {
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1
    private lateinit var edtResidentName: EditText
    private lateinit var edtPhoneNumber: EditText
    private lateinit var spinnerGender: Spinner
    private lateinit var txtGender: TextView
    private lateinit var edtContractName: EditText
    private lateinit var edtStartDate: EditText
    private lateinit var edtEndDate: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtNumberPersons: EditText
    private lateinit var edtRoomNameInContract: TextView
    private lateinit var startDateData: LocalDate
    private lateinit var endDateData: LocalDate



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contract)

        // Initialize views
        initViews()

        // Handle create button click event
        findViewById<Button>(R.id.btnSaveContract).setOnClickListener {
            createResidentAndContract()
        }

        // Initialize spinner with gender data
        val genderArray = arrayOf("FEMALE", "MALE")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderArray)
        spinnerGender.adapter = adapter
        ToolbarHelper.setToolbar(this, "TẠO HỢP ĐỒNG")
        // Set up DatePickers and Spinner
        setupDatePickers()
        setupGenderSpinner()
        // xử lý PDF
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
        }

        // Thiết lập sự kiện cho nút xuất PDF
        btnPDF.setOnClickListener {
            createPdf()
        }
    }

    private fun initViews() {
        edtResidentName = findViewById(R.id.editTextTextPersonName)
        edtPhoneNumber = findViewById(R.id.edtSDT)
        spinnerGender = findViewById(R.id.spinerGender)
        txtGender = findViewById(R.id.txtGioiTinh)
        edtContractName = findViewById(R.id.edtNameContract)
        edtStartDate = findViewById(R.id.edtDateStart)
        edtEndDate = findViewById(R.id.edtDateEnd)
        edtDescription = findViewById(R.id.edtDescription)
        edtNumberPersons = findViewById(R.id.edtSumNPerson)
        edtRoomNameInContract = findViewById(R.id.txtTenPhong)

        // Get room name from Intent and set it to edtRoomNameInContract
        val roomName = intent.getStringExtra("ROOM_NAME")
        edtRoomNameInContract.text = roomName
    }

    // Sửa đổi hàm setupDatePickers() trong AddContractActivity
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDatePickers() {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val dateSetListenerStart =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                // Tạo đối tượng LocalDate từ ngày được chọn
                startDateData = LocalDate.of(year, month + 1, dayOfMonth)
                // Định dạng lại ngày và gán vào EditText cho ngày bắt đầu
                edtStartDate.setText(startDateData.format(dateFormat))
            }
        val dateSetListenerEnd = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Tạo đối tượng LocalDate từ ngày được chọn
            endDateData = LocalDate.of(year, month + 1, dayOfMonth)
            // Định dạng lại ngày và gán vào EditText cho ngày kết thúc
            edtEndDate.setText(endDateData.format(dateFormat))
        }

        edtStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this@AddContractActivity,
                dateSetListenerStart,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Thiết lập ngày tối thiểu cho DatePickerDialog là hôm nay
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()

            datePickerDialog.show()
        }

        edtEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this@AddContractActivity,
                dateSetListenerEnd,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupGenderSpinner() {
        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                txtGender.text = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createResidentAndContract() {
        // Get data from input fields
        val residentName = edtResidentName.text.toString()
       val EndDate = edtEndDate.text.toString()
        val StartDate = edtStartDate.text.toString()
        val phoneNumber = try {
            edtPhoneNumber.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
            return
        }
        val gender = spinnerGender.selectedItem.toString()

        val contractName = edtContractName.text.toString()
        val description = edtDescription.text.toString()
        val numberPersons = try {
            edtNumberPersons.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number of persons", Toast.LENGTH_SHORT).show()
            return
        }
        val roomNameInContract = edtRoomNameInContract.text.toString()

        // Check if all fields are filled
        if (residentName.isNotEmpty() &&
            contractName.isNotEmpty() &&
            description.isNotEmpty() &&
            roomNameInContract.isNotEmpty()
        ) {
            // Check if start date and end date are selected
            if (::startDateData.isInitialized && ::endDateData.isInitialized) {
                // Create Resident object from input data
                val resident = Resident(residentName, phoneNumber, gender, roomNameInContract)

                // Send create Resident request to the server
                GlobalScope.launch(
                    Dispatchers.Main
                ) {
                    try {
                        // Send create Resident request
                        val createResidentCall =
                            RetrofitInstance.residentService.createResident(resident)
                        createResidentCall.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    // If create Resident request is successful
                                    // Create Contract object from input data
                                    val contract = Contract(
                                        contractName,
                                        StartDate,
                                        EndDate,
                                        description,
                                        numberPersons,
                                        roomNameInContract
                                    )
                                    // Send create Contract request to the server
                                    val createContractCall =
                                        RetrofitInstance.contractService.createContract(contract)
                                    createContractCall.enqueue(object : Callback<Void> {
                                        override fun onResponse(
                                            call: Call<Void>,
                                            response: Response<Void>
                                        ) {
                                            if (response.isSuccessful) {
                                                // If create Contract request is successful
                                                // Perform any additional actions if needed
                                                Toast.makeText(
                                                    this@AddContractActivity,
                                                    "Contract created successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                // Finish the activity if needed
                                            } else {
                                                // Handle failure when sending create Contract request
                                                Toast.makeText(
                                                    this@AddContractActivity,
                                                    "Failed to create Contract: ${response.message()}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                        override fun onFailure(call: Call<Void>, t: Throwable) {
                                            // Handle failure when sending create Contract request
                                            Toast.makeText(
                                                this@AddContractActivity,
                                                "Failed to create Contract: ${t.message}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    })
                                } else {
                                    // If create Resident request fails
                                    Toast.makeText(
                                        this@AddContractActivity,
                                        "Failed to create Resident: ${response.message()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                // Handle failure when sending create Resident request
                                Toast.makeText(
                                    this@AddContractActivity,
                                    "Failed to create Resident: ${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@AddContractActivity,
                            "Failed to create Resident and Contract: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please select start date and end date", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
    private fun createPdf() {
        val contractView = findViewById<View>(R.id.contractPDF)
        val width = contractView.width
        val height = contractView.height

        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = document.startPage(pageInfo)

        // Vẽ nội dung của layout lên trang PDF
        contractView.draw(page.canvas)

        // Kết thúc trang
        document.finishPage(page)
        val roomName = intent.getStringExtra("ROOM_NAME")
        // Lưu tài liệu PDF vào bộ nhớ thiết bị
        val directoryPath = Environment.getExternalStorageDirectory().path + "/mypdf/contract/"
        val filePath = "$directoryPath$roomName.pdf"
        val file = File(directoryPath)
        if (!file.exists()) {
            file.mkdirs()
        }

        try {
            document.writeTo(FileOutputStream(filePath))
            Toast.makeText(this, "PDF được tạo thành công", Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Lỗi khi tạo PDF: " + e.message, Toast.LENGTH_SHORT).show()
        }

        // Đóng tài liệu PDF
        document.close()

    }
}
