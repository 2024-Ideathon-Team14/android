package techeer.carezoom.data

import com.naver.maps.geometry.LatLng
import java.io.Serializable

data class CareList(
    val title: String,
    val body: String,
    val category: String,
    val address: String,
    val date: String,
    val postId: Long = 0L,
    val nickname: String,
    val latLng: LatLng,
    val petName: String,
    val petType: String = "강아지",
    val petAge: String
): Serializable
