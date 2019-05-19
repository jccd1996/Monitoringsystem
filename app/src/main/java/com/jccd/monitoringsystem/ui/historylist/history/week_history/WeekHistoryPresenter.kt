package com.jccd.monitoringsystem.ui.historylist.history.week_history

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.db.network.response.ThingSpeakResponse
import com.jccd.monitoringsystem.ui.adapters.HistoryAdapter
import com.jccd.monitoringsystem.utils.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeekHistoryPresenter(private val view: IWeekHistory.view) : IWeekHistory.presenter {

    private val adapter = GroupAdapter<ViewHolder>()
    val listTemperature: ArrayList<Feed> = ArrayList()

    override fun loadWeekFields() {
        MonitoringSystem.sInstance.service.getDataWithDateFieldTemperature(Constants.API_KEY_THING_SPEAK, 7)
            .enqueue(object :
                Callback<ThingSpeakResponse> {
                override fun onFailure(call: Call<ThingSpeakResponse>, t: Throwable) {
                    Log.i("RESPUESTA", t.toString())
                }

                override fun onResponse(call: Call<ThingSpeakResponse>, response: Response<ThingSpeakResponse>) {
                    val temperature = response.body()
                    response.raw().request().url()
                    Log.i("URL_REQUETS", response.raw().request().url().toString())
                    for (feed in temperature!!.feeds) {
                        listTemperature.add(feed)

                    }
                    for (feed in listTemperature.reversed()) {
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