package com.jccd.monitoringsystem.ui.adapters

import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.utils.Constants
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_temperature.view.*

class HistoryAdapter(var feed: Feed, val type: Int) : Item<ViewHolder>() {

    val context = MonitoringSystem.sInstance.getContext()

    override fun getLayout(): Int {
        return R.layout.item_temperature
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.tvDate.text = feed.createdAt

        when (type) {
            1 -> viewHolder.itemView.tvValue.text =
                feed.temperature + Constants.EMPTY_SPACE + context!!.getString(R.string.temperature_symbol)
            2 -> viewHolder.itemView.tvValue.text =
                feed.waterLeve + Constants.EMPTY_SPACE + context!!.getString(R.string.unit_water_level)
            3 -> viewHolder.itemView.tvValue.text = feed.phLevel
        }
    }
}
