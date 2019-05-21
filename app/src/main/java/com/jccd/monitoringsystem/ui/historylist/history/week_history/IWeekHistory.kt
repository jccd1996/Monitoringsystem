package com.jccd.monitoringsystem.ui.historylist.history.week_history

import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.db.model.Feed

interface IWeekHistory {

    interface view{
        fun getRecyclerView(): RecyclerView
        fun goToDetail(type: Int, feed: Feed)
    }
    interface presenter{
        fun loadWeekFields(type: Int)
        fun loadRecyclerView()
    }
}
