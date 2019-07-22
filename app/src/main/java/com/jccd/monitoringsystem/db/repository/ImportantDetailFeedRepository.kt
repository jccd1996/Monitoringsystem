package com.jccd.monitoringsystem.db.repository

import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.jccd.monitoringsystem.ui.importants.detail.ImportantDetailPresenter

class ImportantDetailFeedRepository(private val presenter: ImportantDetailPresenter) : IImportantDetailFeedRepository {

    private lateinit var database: DatabaseReference
    private val context = MonitoringSystem.sInstance.getContext()

    override fun saveImportantFeed(importantFeed: ImportantFeed) {
        database = FirebaseDatabase.getInstance().reference
        val id = importantFeed.entryId

        database.child("importantsFeeds").child(id.toString()).setValue(importantFeed)
            .addOnSuccessListener {
                Toast.makeText(context,context!!.getString(R.string.msg_successfully_updload),Toast.LENGTH_SHORT).show()
                presenter.hideProgressView()
                presenter.finishActivity()

            }
            .addOnFailureListener {
                Toast.makeText(context,context!!.getString(R.string.msg_failed_updload),Toast.LENGTH_SHORT).show()
                presenter.hideProgressView()
            }
    }
}