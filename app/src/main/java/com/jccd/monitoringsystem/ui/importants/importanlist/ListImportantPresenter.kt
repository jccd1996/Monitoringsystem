package com.jccd.monitoringsystem.ui.importants.importanlist

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.jccd.monitoringsystem.ui.adapters.ImportantAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class ListImportantPresenter(val view: IListImportantMVP.view): IListImportantMVP.presenter {

    private val context = MonitoringSystem.sInstance.getContext()
    private val adapter = GroupAdapter<ViewHolder>()

    override fun loadItems() {
        val ref= FirebaseDatabase.getInstance().getReference("/importantsFeeds")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    val feed = it.getValue(ImportantFeed::class.java)
                    val importantFeed = feed
                    if (importantFeed != null) {
                        if (importantFeed.status) {
                            adapter.add(ImportantAdapter(importantFeed!!))
                        }
                    }
                    adapter.setOnItemClickListener { item, view ->
                        val importantFeedItem = item as ImportantAdapter
                        val importantFeed = importantFeedItem.importantFeed.autor
                        Toast.makeText(
                            context,
                            "Seleccionaste a ${importantFeed}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                view.getRecyclerView().adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun loadRecyclerView() {
        view.getRecyclerView().apply {
            layoutManager = LinearLayoutManager(context)
        }
        view.getRecyclerView().adapter = adapter
    }
    fun createImportanFeed(serializedData: String): ImportantFeed {
        val gson = Gson()
        return gson.fromJson(serializedData, ImportantFeed::class.java)
    }
}
