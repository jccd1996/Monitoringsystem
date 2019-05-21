package com.jccd.monitoringsystem.ui.historylist.history.month_history

import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.db.model.Feed

interface IMonthHistoryMVP {

    interface view{
        fun getRecyclerView():RecyclerView
        fun goToDetail(type: Int, feed: Feed)

    }

    interface presenter{
        fun loadMonthFields(type: Int)
        fun loadRecyclerView()
    }

}
