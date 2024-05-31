package techeer.carezoom.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.widget.ZoomControlView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import techeer.carezoom.R
import techeer.carezoom.databinding.ActivityMainBinding
import techeer.carezoom.presentation.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), OnMapReadyCallback {
    private var naverMap: NaverMap? = null
    private lateinit var behavior: BottomSheetBehavior<ConstraintLayout>
    private val viewModel : MainViewModel by viewModels()

    private lateinit var navController: NavController
    override fun initView() {
        initMap()
        initNavigator()
        initBottomSheet()

        binding.writeBtn.setOnClickListener {
            startActivity(WriteActivity.newIntent(this))
        }
    }

    override fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.latLng.collect{
                    behavior.state = it.behavior
                    naverMap?.moveCamera(CameraUpdate.scrollTo(it.latLng))
                }
            }
        }
    }

    private fun initMap() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync { map ->
            naverMap = map
            val current = LatLng(37.34169873425236, 126.732337474823)
            naverMap?.moveCamera(CameraUpdate.scrollTo(current))
            val marker = Marker().apply {
                this.icon = OverlayImage.fromResource(R.drawable.oval)
                this.position = current
                this.map = naverMap
            }
            behavior.expandedOffset = binding.layout.bottom + 20

            val latLngs = listOf(
                LatLng(37.343493, 126.732337),
                LatLng(37.341698, 126.729402),
                LatLng(37.341698, 126.737917),
                LatLng(37.349391, 126.732337),
                LatLng(37.341698, 126.720577),
            )

            val titles = listOf(
                "강아지 초코 산책시켜 주실분 찾아요!",
                "잠깐 집에 있는 뽀삐 밥좀 챙겨주세요",
                "강아지 밍키 화장실 청소 부탁해요",
                "강아지 밤비 산책 부탁드려요",
                "강아지 폼이 먹이 주실분"
            )

            val markers = listOf(Marker(), Marker(), Marker(), Marker(), Marker())
            for (i in markers.indices){
                markers[i].icon = OverlayImage.fromResource(R.drawable.ic_list_icon_resize)
                markers[i].position = latLngs[i]
                markers[i].map = naverMap
                markers[i].captionText = titles[i]
            }
        }
    }

    private fun initNavigator(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initBottomSheet(){
        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        behavior.isFitToContents = false
        behavior.setPeekHeight(200, true)
        behavior.addBottomSheetCallback(object : BottomSheetCallback(){
            override fun onStateChanged(p0: View, p1: Int) {

            }

            override fun onSlide(p0: View, slideOffset: Float) {
                if (slideOffset >= 0.5f){
                    binding.writeBtn.isVisible = false
                    binding.navermapLogo.isVisible = false
                } else {
                    binding.writeBtn.isVisible = true
                    binding.navermapLogo.isVisible = true
                }
            }
        })
    }

    override fun onMapReady(p0: NaverMap) {
        //p0.moveCamera(CameraUpdate.scrollTo(LatLng(37.34169873425236, 126.732337474823)))
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, MainActivity::class.java)
    }
}