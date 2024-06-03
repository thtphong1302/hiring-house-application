package com.example.house_manager.Activity

import RoomAdapter
import android.Manifest
import android.app.DatePickerDialog
import android.content.pm.PackageManager
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
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.android.synthetic.main.activity_add_contract.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.layout.element.Text

class AddContractActivity : AppCompatActivity() {
    private val STORAGE_PERMISSION_CODE = 1
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

        // Set up PDF button
        val createPdfButton: ImageView = findViewById(R.id.btnPDF)
        createPdfButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
            } else {
                createPDF()
            }
        }
    }

    private fun createPDF() {
        val contractName = edtContractName.text.toString().replace("[^a-zA-Z0-9_\\-]".toRegex(), "_")

        // Define file path and create file
        val pdfPath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString()
        val file = File(pdfPath, "${contractName}_HopDong.pdf")

        try {
            val outputStream = FileOutputStream(file)
            val writer = PdfWriter(outputStream)
            val pdfDoc = com.itextpdf.kernel.pdf.PdfDocument(writer)
            val document = Document(pdfDoc, PageSize.A4)

            // Define fonts
            val fontRegular = PdfFontFactory.createFont("Helvetica")
            val fontBold = PdfFontFactory.createFont("Helvetica-Bold")

            // Define colors
            val colorBlack = DeviceRgb(0, 0, 0)

            // Add content to PDF
            val title = Paragraph("Thông Tin Hợp Đồng").setFont(fontBold).setFontSize(18f).setFontColor(colorBlack)

            val contractNameText = if (edtContractName.text.isNotEmpty()) edtContractName.text.toString() else ""
            val residentNameText = if (edtResidentName.text.isNotEmpty()) edtResidentName.text.toString() else ""
            val genderText = if (txtGender.text.isNotEmpty()) txtGender.text.toString() else ""
            val startDateText = if (edtStartDate.text.isNotEmpty()) edtStartDate.text.toString() else ""
            val endDateText = if (edtEndDate.text.isNotEmpty()) edtEndDate.text.toString() else ""
            val numberPersonsText = if (edtNumberPersons.text.isNotEmpty()) edtNumberPersons.text.toString() else ""
            val descriptionText = if (edtDescription.text.isNotEmpty()) edtDescription.text.toString() else ""

            val contractInfo = Paragraph()
                .add(Text("Tiền Nhà: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(contractNameText).setFont(fontRegular).setFontColor(colorBlack))

            val residentInfo = Paragraph()
                .add(Text("Tên Người Ở: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(residentNameText).setFont(fontRegular).setFontColor(colorBlack))

            val genderInfo = Paragraph()
                .add(Text("Giới Tính: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(genderText).setFont(fontRegular).setFontColor(colorBlack))

            val startDateInfo = Paragraph()
                .add(Text("Ngày Bắt Đầu: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(startDateText).setFont(fontRegular).setFontColor(colorBlack))

            val endDateInfo = Paragraph()
                .add(Text("Ngày Kết Thúc: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(endDateText).setFont(fontRegular).setFontColor(colorBlack))

            val numberPersonsInfo = Paragraph()
                .add(Text("Số Người Ở: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(numberPersonsText).setFont(fontRegular).setFontColor(colorBlack))

            val descriptionInfo = Paragraph()
                .add(Text("Mô Tả: ").setFont(fontBold).setFontColor(colorBlack))
                .add(Text(descriptionText).setFont(fontRegular).setFontColor(colorBlack))

            document.add(title)
            document.add(contractInfo)
            document.add(residentInfo)
            document.add(genderInfo)
            document.add(startDateInfo)
            document.add(endDateInfo)
            document.add(numberPersonsInfo)
            document.add(descriptionInfo)

            // Close document
            document.close()

            Toast.makeText(this, "PDF được tạo thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error creating PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                createPDF()
            } else {
                Toast.makeText(this, "Quyền bị từ chối!", Toast.LENGTH_SHORT).show()
            }
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDatePickers() {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val dateSetListenerStart = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            startDateData = LocalDate.of(year, month + 1, dayOfMonth)
            edtStartDate.setText(startDateData.format(dateFormat))
        }
        val dateSetListenerEnd = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            endDateData = LocalDate.of(year, month + 1, dayOfMonth)
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
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        edtEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this@AddContractActivity,
                dateSetListenerEnd,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun setupGenderSpinner() {
        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                txtGender.text = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createResidentAndContract() {
        val residentName = edtResidentName.text.toString()
        val endDate = edtEndDate.text.toString()
        val startDate = edtStartDate.text.toString()
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

        if (residentName.isNotEmpty() && contractName.isNotEmpty() && description.isNotEmpty() && roomNameInContract.isNotEmpty()) {
            if (::startDateData.isInitialized && ::endDateData.isInitialized) {
                val resident = Resident(residentName, phoneNumber, gender, roomNameInContract)
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val createResidentCall = RetrofitInstance.residentService.createResident(resident)
                        createResidentCall.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    val contract = Contract(contractName, startDate, endDate, description, numberPersons, roomNameInContract)
                                    val createContractCall = RetrofitInstance.contractService.createContract(contract)
                                    createContractCall.enqueue(object : Callback<Void> {
                                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(this@AddContractActivity, "Contract created successfully", Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(this@AddContractActivity, "Failed to create Contract: ${response.message()}", Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                        override fun onFailure(call: Call<Void>, t: Throwable) {
                                            Toast.makeText(this@AddContractActivity, "Failed to create Contract: ${t.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                } else {
                                    Toast.makeText(this@AddContractActivity, "Failed to create Resident: ${response.message()}", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(this@AddContractActivity, "Failed to create Resident: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } catch (e: Exception) {
                        Toast.makeText(this@AddContractActivity, "Failed to create Resident and Contract: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please select start date and end date", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
