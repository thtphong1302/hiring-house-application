import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.house_manager.Fragments.Room_fragment1
import com.example.house_manager.Fragments.Room_fragment2

internal class RoomAdapterFragment(val context: Context, fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Room_fragment1()
            1 -> Room_fragment2()
            else ->  Room_fragment1()// hoặc trả về một fragment mặc định khác nếu cần
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
