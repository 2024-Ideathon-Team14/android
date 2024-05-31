package techeer.carezoom.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import techeer.carezoom.R
import techeer.carezoom.databinding.ActivityWriteBinding
import techeer.carezoom.presentation.base.BaseActivity

@AndroidEntryPoint
class WriteActivity: BaseActivity<ActivityWriteBinding>(R.layout.activity_write) {
    override fun initView() {
        binding.view = this
        binding.doneBtn.setOnClickListener {
            finish()
        }


    }

    override fun initObserver() {

    }

    fun onBackBtnPressed(){
        finish()
    }
    companion object {
        fun newIntent(context: Context) =
            Intent(context, WriteActivity::class.java)
    }
}