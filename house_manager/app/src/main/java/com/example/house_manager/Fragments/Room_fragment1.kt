import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.house_manager.Fragments.ApiUtils
import com.example.house_manager.Model.RoomEmpty
import com.example.house_manager.Model.RoomResponse
import com.example.house_manager.Network.RetrofitInstance
import com.example.house_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Room_fragment1 : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterEmpty: RoomAdapter
    private lateinit var seekBarPrice: SeekBar
    private lateinit var seekBarArea: SeekBar
    private lateinit var txtCurrentPrice: TextView
    private lateinit var txtCurrentArea: TextView
    private lateinit var originalRoomList: List<RoomEmpty>

    private val priceOffset = 3000000
    private val areaOffset = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)
        seekBarPrice = view.findViewById(R.id.seekBarPrice)
        seekBarArea = view.findViewById(R.id.seekBarArea)
        txtCurrentPrice = view.findViewById(R.id.txtCurrentPrice)
        txtCurrentArea = view.findViewById(R.id.txtCurrentArea)

        // Initialize RecyclerView
        adapterEmpty = RoomAdapter(requireContext(), mutableListOf())
        recyclerView.adapter = adapterEmpty
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Set SeekBar listeners
        seekBarPrice.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val adjustedPrice = progress + priceOffset
                txtCurrentPrice.text = "Current Price: $adjustedPrice"
                filterRooms()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarArea.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val adjustedArea = progress + areaOffset
                txtCurrentArea.text = "Current Area: $adjustedArea m²"
                filterRooms()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Fetch initial data
        val apartmentName = activity?.intent?.getStringExtra("APARTMENT_NAME")
        apartmentName?.let {
            fetchEmptyRooms(listOf(it))
        } ?: ApiUtils.getApartments { apartmentList ->
            fetchEmptyRooms(apartmentList.map { it.departmentName })
        }
    }

    private fun fetchEmptyRooms(departmentNames: List<String>) {
        for (departmentName in departmentNames) {
            val call: Call<RoomResponse> = RetrofitInstance.roomService.getEmptyRooms(departmentName)
            call.enqueue(object : Callback<RoomResponse> {
                override fun onResponse(call: Call<RoomResponse>, response: Response<RoomResponse>) {
                    if (response.isSuccessful) {
                        val roomResponse: RoomResponse? = response.body()
                        val roomList: List<RoomEmpty>? = roomResponse?.result
                        roomList?.let {
                            originalRoomList = it
                            adapterEmpty.setData(it)
                        }
                    } else {
                        Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                        Toast.makeText(requireContext(), "Error fetching data for $departmentName", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                    Log.e("API_RESPONSE", "Error: ${t.message}", t)
                    Toast.makeText(requireContext(), "Error fetching data for $departmentName", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun filterRooms() {
        val maxPrice = seekBarPrice.progress + priceOffset
        val maxArea = seekBarArea.progress + areaOffset

        val filteredList = originalRoomList.filter { room ->
            val roomPrice = room.roomTypeResponse?.price ?: 0
            val roomArea = room.roomTypeResponse?.area ?: 0
            roomPrice <= maxPrice || roomArea <= maxArea
        }

        adapterEmpty.setData(filteredList)
    }
}
