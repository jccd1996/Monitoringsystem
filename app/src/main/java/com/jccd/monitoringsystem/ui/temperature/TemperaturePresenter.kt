package com.jccd.monitoringsystem.ui.temperature

import android.util.Log
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.Temperature
import com.jccd.monitoringsystem.ui.temperature.ITemperatureMVP
import com.jccd.monitoringsystem.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TemperaturePresenter(private val view: ITemperatureMVP.view) : ITemperatureMVP.presenter {

    override fun loadLastTemperatureField() {
        MonitoringSystem.sInstance.service.getTemperatureLastField(Constants.API_KEY_THING_SPEAK).enqueue(object :
            Callback<Temperature> {
            override fun onFailure(call: Call<Temperature>, t: Throwable) {
                Log.i("RESPUESTA", "BADD")
            }

            override fun onResponse(call: Call<Temperature>, response: Response<Temperature>) {
                val temperature = response.body()
                Log.i("RESPUESTA", Gson().toJson(temperature))
                view.setDataTemperature(temperature!!)
            }
        })
    }
}