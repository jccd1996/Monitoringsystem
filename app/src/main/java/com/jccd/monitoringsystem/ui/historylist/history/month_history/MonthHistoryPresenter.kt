package com.jccd.monitoringsystem.ui.historylist.history.month_history

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.db.network.response.ThingSpeakResponse
import com.jccd.monitoringsystem.ui.adapters.HistoryAdapter
import com.jccd.monitoringsystem.utils.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonthHistoryPresenter(private val view: IMonthHistoryMVP.view): IMonthHistoryMVP.presenter {

    private val adapter = GroupAdapter<ViewHolder>()
    private val context = MonitoringSystem.sInstance.getContext()
    val listTemperature: ArrayList<Feed> = ArrayList()

    override fun loadMonthFields() {
        MonitoringSystem.sInstance.service.getTemperatureAllField(Constants.API_KEY_THING_SPEAK).enqueue(object :
            Callback<ThingSpeakResponse>{
            override fun onFailure(call: Call<ThingSpeakResponse>, t: Throwable) {
                Log.i("RESPUESTA", t.toString())
            }

            override fun onResponse(call: Call<ThingSpeakResponse>, response: Response<ThingSpeakResponse>) {
                val temperature = response.body()
                Log.i("RESPUESTA", Gson().toJson(temperature!!.feeds))
//                view.setDataTemperature(temperature!!)

                for(feed in temperature.feeds){
                    listTemperature.add(feed)
                    adapter.add(HistoryAdapter(feed))
                }

            }
        })
    }

    override fun loadRecyclerView() {
        view.getRecyclerView().apply {
            layoutManager = LinearLayoutManager(context)
        }
        view.getRecyclerView().adapter = adapter
    }
}