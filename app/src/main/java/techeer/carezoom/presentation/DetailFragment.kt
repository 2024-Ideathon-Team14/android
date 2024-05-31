package techeer.carezoom.presentation

import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import techeer.carezoom.R
import techeer.carezoom.databinding.FragmentDetailBinding
import techeer.carezoom.presentation.base.BaseFragment

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val navigator: NavController by lazy {
        findNavController()
    }
    private val args: DetailFragmentArgs by navArgs()
    override fun initObserver() {

    }

    override fun initView() {
        binding.view = this
        with(args.care) {
            binding.titleTv.text = title
            binding.bodyTv.text = body
            binding.addressTv.text = address
            binding.petCategoryTv.text = petType
            binding.petNameTv.text = petName
            binding.petAgeTv.text = petAge+"세"
        }
    }

    fun onBackPressed(){
        navigator.popBackStack()
    }
}