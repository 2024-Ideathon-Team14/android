package techeer.carezoom.presentation

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint
import techeer.carezoom.R
import techeer.carezoom.data.CareList
import techeer.carezoom.databinding.FragmentListBinding
import techeer.carezoom.presentation.base.BaseFragment

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list),
    CareListRVA.RVAClickListener {
        private val viewModel: MainViewModel by activityViewModels()
    private val navigator: NavController by lazy {
        findNavController()
    }
    private val careListRVA: CareListRVA by lazy {
        CareListRVA()
    }

    override fun initObserver() {

    }

    override fun initView() {
        initRVA()
    }

    private fun initRVA() {
        binding.rv.adapter = careListRVA
        binding.rv.itemAnimator = null
        careListRVA.setRVAClickListener(this)
        val list = listOf(
            CareList(
                "강아지 초코 산책시켜 주실분 찾아요!",
                "지나가는 사람들 보면 짖고 달려들어요, 다른 보행자 분들 물지 않게 조심해주세요.",
                "산책하기",
                "경기도 시흥시 산기대학로 237",
                "2024.06.01",
                0L,
                "카리나",
                LatLng(37.343493, 126.732337), "초코", "강아지", "1"),
            CareList("잠깐 집에 있는 뽀삐 밥좀 챙겨주세요",
                "겁이 많은 성격이라 사료만 챙겨주세요. 도착하면 연락 주세요.",
                "먹이주기",
                "경기도 시흥시 정왕동 1619-3",
                "2024.06.01",
                0L,
                "윈터",
                LatLng(37.341698, 126.729402), "뽀삐", "강아지", "5"),
            CareList("강아지 밍키 화장실 청소 부탁해요",
                "강아지 배변판 청소해주시면 정말 감사하겠습니다. 배변패드는 새로 채워주세요.",
                "대리돌봄",
                "경기도 시흥시 정왕동 1594-7번지",
                "2024.06.01",
                0L,
                "장원영",
                LatLng(37.341698, 126.737917), "밍키", "강아지", "7"),
            CareList("강아지 밤비 산책 부탁드려요",
                "평소 산책시간에 같이 가주실분 찾습니다. 시간 조율 가능합니다.",
                "산책하기",
                "대한민국 시흥시 대우아파트",
                "2024.06.01",
                0L,
                "안유진",
                LatLng(37.349391, 126.732337), "밤비", "강아지", "2"),
            CareList("강아지 폼이 먹이 주실분",
                "우리 폼이에게 하루 두 번 사료 주실 분 구합니다. 3일동안 해외여행을 가서요",
                "먹이주기",
                "경기도 시흥시 목감동 789-10",
                "2024.06.01",
                0L,
                "민지",
                LatLng(37.341698, 126.720577), "폼이", "강아지", "3"),
        )
        careListRVA.submitList(list)
    }

    override fun onDetailView(care: CareList) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(care)
        navigator.navigate(action)
    }

    override fun onMapView(coord: LatLng) {
        viewModel.setMapView(MainViewModel.MapView(BottomSheetBehavior.STATE_HALF_EXPANDED, coord))
    }
}