package com.jccd.monitoringsystem

import android.app.Application
import android.content.Context
import com.jccd.monitoringsystem.db.network.services.GetData
import com.jccd.monitoringsystem.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MonitoringSystem : Application() {
    private var sContext: Context? = null
    lateinit var service: GetData

    companion object {
        lateinit var sInstance: MonitoringSystem
        fun getInstance() = sInstance

    }

    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
        sInstance = this
        buildRetrofit()
    }

    fun getContext(): Context? {
        return sContext
    }


    fun setAppContext(context: Context) {
        sContext = context
    }

    fun buildRetrofit() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = requestInterface.create<GetData>(GetData::class.java)
    }

    fun getServices() = service
}
