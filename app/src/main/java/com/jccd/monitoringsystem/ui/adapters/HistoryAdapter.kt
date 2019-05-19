package com.jccd.monitoringsystem.ui.adapters

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_temperature.view.*

class HistoryAdapter(var feed: Feed): Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.item_temperature
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvValue.text = feed.temperature
        viewHolder.itemView.tvDate.text = feed.createdAt
    }
}