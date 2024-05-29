import com.example.house_manager.Model.Room
import com.example.house_manager.Model.RoomResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RoomService {
    @GET("rooms/room-empty")
    fun getEmptyRooms(@Query("departmentName") departmentName: String): Call<RoomResponse>

    @GET("rooms/room-done")
    fun getDoneRooms(@Query("departmentName") departmentName: String): Call<RoomResponse>

    @POST("rooms")
    fun createRoom(@Body room: Room): Call<Room>
}
