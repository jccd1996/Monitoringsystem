package com.jccd.monitoringsystem.ui.adapters

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_important.view.*

class ImportantAdapter(val importantFeed: ImportantFeed): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.item_important
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvAutor.text = importantFeed.autor
        viewHolder.itemView.tvDate.text = importantFeed.createdAt
        viewHolder.itemView.tvValue.text = importantFeed.value
    }
}