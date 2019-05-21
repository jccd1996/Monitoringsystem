package com.jccd.monitoringsystem.ui.importants.importanlist

import androidx.recyclerview.widget.RecyclerView

interface IListImportantMVP  {

    interface view{
        fun getRecyclerView(): RecyclerView

    }
    interface presenter{
        fun loadRecyclerView()
        fun loadItems()
    }
}
