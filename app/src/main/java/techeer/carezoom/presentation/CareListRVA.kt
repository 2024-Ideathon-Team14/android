package techeer.carezoom.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naver.maps.geometry.LatLng
import techeer.carezoom.data.CareList
import techeer.carezoom.databinding.ItemCareListBinding

class CareListRVA : ListAdapter<CareList, CareListRVA.CareListViewHolder>(object :
    DiffUtil.ItemCallback<CareList>() {
    override fun areItemsTheSame(oldItem: CareList, newItem: CareList): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CareList, newItem: CareList): Boolean {
        return oldItem == newItem
    }

}) {
    private var rvaClickListener: RVAClickListener? = null
    fun setRVAClickListener(listener: RVAClickListener){
        this.rvaClickListener = listener
    }
    inner class CareListViewHolder(private val binding: ItemCareListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(careList: CareList) {
            with(binding){
                model = careList
                categoryTv.text = careList.category
                infoViewBtn.setOnClickListener {
                    rvaClickListener?.onDetailView(careList)
                }
                mapViewBtn.setOnClickListener {
                    rvaClickListener?.onMapView(careList.latLng)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareListViewHolder {
        return CareListViewHolder(
            ItemCareListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CareListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    interface RVAClickListener{
        fun onDetailView(care: CareList)
        fun onMapView(coord: LatLng)
    }

}