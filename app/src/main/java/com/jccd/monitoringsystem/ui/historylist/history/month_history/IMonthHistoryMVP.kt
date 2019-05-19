package com.jccd.monitoringsystem.ui.historylist.history.month_history

import androidx.recyclerview.widget.RecyclerView

interface IMonthHistoryMVP {

    interface view{
        fun getRecyclerView():RecyclerView

    }

    interface presenter{
        fun loadMonthFields(type: Int)
        fun loadRecyclerView()
    }

}