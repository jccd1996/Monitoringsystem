package com.jccd.monitoringsystem.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ConvertDate
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(var feed: Feed, val type: Int) : Item<ViewHolder>() {

    val context = MonitoringSystem.sInstance.getContext()
    var value = 0f
    override fun getLayout(): Int {
        return R.layout.item_history
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.tvDate.text = ConvertDate(feed.createdAt).convertDateToList()

        when (type) {
            1 -> {
                viewHolder.itemView.tvValue.text =
                    feed.temperature + Constants.EMPTY_SPACE + context!!.getString(R.string.temperature_symbol)
                value = feed.temperature.toFloat()
                if (value in 21..44) {
                    viewHolder.itemView.tvValue.setTextColor(context.getColor(R.color.green_succes))
                    viewHolder.itemView.ivWarning.visibility = View.GONE
                } else {
                    viewHolder.itemView.tvValue.setTextColor(Color.RED)
                    viewHolder.itemView.ivWarning.visibility = View.VISIBLE
                }
            }

            2 -> {
                viewHolder.itemView.tvValue.text =
                    feed.waterLeve + Constants.EMPTY_SPACE + context!!.getString(R.string.unit_water_level)
                value = feed.waterLeve.toFloat()
                when {
                    value < 5 -> {
                        viewHolder.itemView.tvValue.setTextColor(context.getColor(R.color.gold))
                        viewHolder.itemView.ivWarning.visibility = View.VISIBLE
                        viewHolder.itemView.ivWarning.setColorFilter(context.getColor(R.color.gold))
                        viewHolder.itemView.tvValue.text =
                            feed.waterLeve + Constants.EMPTY_SPACE + context.getString(R.string.unit_water_level) +
                                    Constants.EMPTY_SPACE + context.getString(R.string.check_tank)
                    }

                    value in 5..100 -> {
                        viewHolder.itemView.tvValue.setTextColor(context.getColor(R.color.green_succes))
                        viewHolder.itemView.ivWarning.visibility = View.GONE
                    }

                    value in 101..200 -> {
                        viewHolder.itemView.tvValue.setTextColor(Color.RED)
                        viewHolder.itemView.ivWarning.visibility = View.VISIBLE
                        viewHolder.itemView.tvValue.text =
                            feed.waterLeve + Constants.EMPTY_SPACE + context.getString(R.string.unit_water_level)
                        viewHolder.itemView.ivWarning.setColorFilter(Color.RED)
                    }

                    value > 201 -> {
                        viewHolder.itemView.tvValue.setTextColor(Color.RED)
                        viewHolder.itemView.ivWarning.visibility = View.VISIBLE
                        viewHolder.itemView.tvValue.text =
                            feed.waterLeve + Constants.EMPTY_SPACE + context.getString(R.string.unit_water_level) +
                                    Constants.EMPTY_SPACE + context.getString(R.string.check_sensor)
                        viewHolder.itemView.ivWarning.setColorFilter(Color.RED)
                    }
                }
            }

            3 -> {
                viewHolder.itemView.tvValue.text = feed.phLevel
                value = feed.phLevel.toFloat()

                when {
                    value in 6.7..7.5 -> {
                        viewHolder.itemView.tvValue.setTextColor(context!!.getColor(R.color.green_succes))
                        viewHolder.itemView.ivWarning.visibility = View.GONE
                    }
                    value > 14 -> {
                        viewHolder.itemView.tvValue.text = feed.phLevel + Constants.EMPTY_SPACE +
                                context!!.getString(R.string.check_sensor)
                    }
                    value !in 6.7..7.5 -> {
                        viewHolder.itemView.tvValue.setTextColor(Color.RED)
                        viewHolder.itemView.ivWarning.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
