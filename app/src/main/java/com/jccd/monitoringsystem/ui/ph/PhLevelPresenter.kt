package com.jccd.monitoringsystem.ui.ph

import android.util.Log
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.PhLevel
import com.jccd.monitoringsystem.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhLevelPresenter(private val view: IPhLevelMVP.view): IPhLevelMVP.presenter {

    override fun loadLastPhLevelField() {
        MonitoringSystem.sInstance.service.getPhLevelLastField(Constants.API_KEY_THING_SPEAK).enqueue(object :
            Callback<PhLevel> {
            override fun onFailure(call: Call<PhLevel>, t: Throwable) {
                Log.i("RESPUESTA", "BADD")
            }

            override fun onResponse(call: Call<PhLevel>, response: Response<PhLevel>) {
                val phLevel = response.body()
                Log.i("RESPUESTA", Gson().toJson(phLevel))
                view.setDataPhLevel(phLevel!!)
            }
        })
    }
}