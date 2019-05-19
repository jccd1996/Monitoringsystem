package com.jccd.monitoringsystem.ui.historylist.history.week_history

import androidx.recyclerview.widget.RecyclerView

interface IWeekHistory {

    interface view{
        fun getRecyclerView(): RecyclerView
    }
    interface presenter{
        fun loadWeekFields()
        fun loadRecyclerView()
    }
}