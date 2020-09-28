package com.davidelmn.application.frenzspots.spotlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.Spot

class SpotListAdapter(private val items: List<Spot>) : RecyclerView.Adapter<SpotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spot_item, parent, false)
        return SpotViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        items[position].also {
            holder.apply {
//                spotIcon = it.iconPath
                spotAddress.text = it.address
                spotTitle.text = it.title
                spotCity.text = it.city
            }
        }
    }

    override fun getItemCount(): Int = items.size

}

class SpotViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val spotIcon: ImageView = view.findViewById(R.id.fsSpotIconId)
    val spotTitle: TextView = view.findViewById(R.id.fsSpotTitleTextId)
    val spotAddress: TextView = view.findViewById(R.id.fsSpotAddressTextId)
    val spotCity: TextView = view.findViewById(R.id.fsSpotCityTextId)
}
