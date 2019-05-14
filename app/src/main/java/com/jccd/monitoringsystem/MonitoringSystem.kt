package com.jccd.monitoringsystem

import android.app.Application
import android.content.Context

class MonitoringSystem: Application() {
    private var sContext: Context? = null
    private var sInstance: MonitoringSystem? = null
    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
    }

    fun getContext(): Context? {
        return sContext
    }

    fun getInstance(): MonitoringSystem {
        return sInstance!!
    }

    fun setAppContext(context: Context) {
        sContext = context
    }
}
