package com.jccd.monitoringsystem.ui.historylist.history.day_history

import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.jccd.monitoringsystem.db.model.Feed

interface IDayHistoryMVP {

    interface view{
        fun getRecyclerView(): RecyclerView
        fun goToDetail(type: Int, feed: Feed)
    }
    interface presenter{
        fun loadDayFields(type: Int)
        fun loadRecyclerView()
    }
}
