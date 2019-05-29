package com.jccd.monitoringsystem.ui.adapters

import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ConvertDate
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_important.view.*

class ImportantAdapter(val importantFeed: ImportantFeed) : Item<ViewHolder>() {

    private val context = MonitoringSystem.sInstance.getContext()!!

    override fun getLayout(): Int {
        return R.layout.item_important
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvAutor.text = importantFeed.autor
        viewHolder.itemView.tvDate.text = ConvertDate(importantFeed.createdAt).convertDateToList()

        when (importantFeed.type) {
            1 -> viewHolder.itemView.tvValue.text =
                importantFeed.value + Constants.EMPTY_SPACE + context.getString(R.string.temperature_symbol)
            2 -> viewHolder.itemView.tvValue.text =
                importantFeed.value + Constants.EMPTY_SPACE + context.getString(R.string.unit_water_level)
            3 -> viewHolder.itemView.tvValue.text = importantFeed.value
        }
    }
}
