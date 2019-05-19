package com.jccd.monitoringsystem.ui.water_level

import android.util.Log
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.WaterLevel
import com.jccd.monitoringsystem.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaterLevelPresenter(private val view: IWaterLevelMVP.view) : IWaterLevelMVP.presenter {

    override fun loadLastWaterLevelField() {
        MonitoringSystem.sInstance.service.getWaterLevelLastField(Constants.API_KEY_THING_SPEAK).enqueue(object :
            Callback<WaterLevel> {
            override fun onFailure(call: Call<WaterLevel>, t: Throwable) {
                Log.i("RESPUESTA", "BADD")
            }

            override fun onResponse(call: Call<WaterLevel>, response: Response<WaterLevel>) {
                val waterLevel = response.body()
                Log.i("RESPUESTA", Gson().toJson(waterLevel))
                view.setDataWaterLevel(waterLevel!!)
            }
        })
    }
}
