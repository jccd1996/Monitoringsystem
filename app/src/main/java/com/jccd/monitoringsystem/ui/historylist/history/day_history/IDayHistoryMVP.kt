package com.jccd.monitoringsystem.ui.historylist.history.day_history

import androidx.recyclerview.widget.RecyclerView

interface IDayHistoryMVP {

    interface view{
        fun getRecyclerView(): RecyclerView
    }
    interface presenter{
        fun loadDayFields()
        fun loadRecyclerView()
    }
}
