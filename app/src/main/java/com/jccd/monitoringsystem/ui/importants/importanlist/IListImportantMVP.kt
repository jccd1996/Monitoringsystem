package com.jccd.monitoringsystem.ui.importants.importanlist

import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.db.model.ImportantFeed

interface IListImportantMVP  {

    interface view{
        fun getRecyclerView(): RecyclerView
        fun goToDetailImportant(importantFeed: ImportantFeed)

    }
    interface presenter{
        fun loadRecyclerView()
        fun loadItems()
    }
}
