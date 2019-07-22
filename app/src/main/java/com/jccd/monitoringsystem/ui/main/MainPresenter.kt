package com.jccd.monitoringsystem.ui.main

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.db.prefs.SessionManager
import com.jccd.monitoringsystem.utils.Constants
import java.io.File

class MainPresenter(private val view: IMainMVP.view) : IMainMVP.presenter {

    private val sessionManager = SessionManager(MonitoringSystem.sInstance.getContext()!!)
    private val context: Context = MonitoringSystem.getInstance().getContext()!!

    override fun closeSession() {
        val builder = AlertDialog.Builder(view.getMainActivity())
        builder.setTitle(R.string.close_session)
        builder.setMessage(R.string.lab_dialog_logout_confirmation_message)

        builder.setPositiveButton(R.string.lab_dialog_confirmation_message) { dialog, which ->
            FirebaseAuth.getInstance().signOut()
            sessionManager.logOut()
            view.finishActivity()
        }

        builder.setNegativeButton(R.string.lab_dialog_cancel_message) { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun validateLogUser() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            SessionManager(MonitoringSystem.sInstance.getContext()!!).logOut()
            view.finishActivity()
        }
    }

    override fun loadDataUser(): User? {
        val user = sessionManager.getUserProfile()
        return user
    }

    override fun downloadAllDataExcel(manager: DownloadManager) {
        val request = DownloadManager.Request(Uri.parse(Constants.URL_DOWNLOAD_ALL_DATA))
        val progress = ProgressDialog.show(
            view.getMainActivity(),
            context.getString(R.string.download_data),
            context.getString(R.string.download_data_description),
            true
        )
        val fileName = context.getString(R.string.download_data_file)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(context.getString(R.string.download_data))
        request.setDescription(context.getString(R.string.download_data_description))
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (!progress.isShowing) {
                    return
                }
                context.unregisterReceiver(this)
                progress.dismiss()
                val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                val c = manager.query(DownloadManager.Query().setFilterById(downloadId))
                if (c.moveToFirst()) {
                    val status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Toast.makeText(context,context.getText(R.string.download_succsessful),Toast.LENGTH_SHORT).show()
                    }
                }
                c.close()
            }
        }
        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        manager.enqueue(request)
    }
}
