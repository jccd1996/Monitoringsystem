package com.jccd.monitoringsystem.ui.main

import android.app.Activity
import android.app.DownloadManager
import com.jccd.monitoringsystem.db.model.User

interface IMainMVP {
    interface view{
        fun finishActivity()
        fun getMainActivity(): Activity
    }
    interface presenter{
        fun validateLogUser()
        fun closeSession()
        fun loadDataUser(): User
        fun downloadAllDataExcel(manager: DownloadManager)
    }
}