package com.jccd.monitoringsystem.ui.historylist.history.week_history

import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.jccd.monitoringsystem.db.model.Feed

interface IWeekHistory {

    interface view{
        fun getRecyclerView(): RecyclerView
        fun getLineChart(): LineChart
        fun goToDetail(type: Int, feed: Feed)
    }
    interface presenter{
        fun loadWeekFields(type: Int, isGraphic: Boolean)
        fun loadRecyclerView()
    }
}
