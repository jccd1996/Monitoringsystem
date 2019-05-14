package com.jccd.monitoringsystem

import android.app.Application
import android.content.Context


class MonitoringSystem: Application() {
    private var sContext: Context? = null


    companion object{
        lateinit var sInstance: MonitoringSystem
        private set

        fun getInstance() = sInstance

    }
    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
        sInstance = this
    }

    fun getContext(): Context? {
        return sContext
    }



    fun setAppContext(context: Context) {
        sContext = context
    }
}
