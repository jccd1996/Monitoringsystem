package com.jccd.monitoringsystem.ui.temperature


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Temperature
import com.jccd.monitoringsystem.db.network.services.GetData
import com.jccd.monitoringsystem.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_temperature.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TemperatureFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLastTemperature()

//        bTemperatura.setOnClickListener {
//            getLastTemperature()
//        }

    }



    fun getLastTemperature(){

        MonitoringSystem.sInstance.service.getTemperatureLastField(Constants.API_KEY_THING_SPEAK).enqueue(object : Callback<Temperature>{
            override fun onFailure(call: Call<Temperature>, t: Throwable) {
                Log.i("RESPUESTA", "BADD")
            }

            override fun onResponse(call: Call<Temperature>, response: Response<Temperature>) {
                val temperature = response.body()
                Log.i("RESPUESTA", Gson().toJson(temperature))
                tvTemperatura.text = temperature?.temperature
            }

        })
    }
}
