package techeer.carezoom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {
    private val _latLng = MutableSharedFlow<MapView>()
    val latLng = _latLng.asSharedFlow()

    fun setMapView(mapView: MapView){
        viewModelScope.launch {
            _latLng.emit(mapView)
        }
    }

    data class MapView(
        val behavior: Int,
        val latLng: LatLng
    )
}