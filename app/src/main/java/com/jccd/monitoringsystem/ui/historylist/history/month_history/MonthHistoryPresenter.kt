package com.jccd.monitoringsystem.ui.historylist.history.month_history

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

class MonthHistoryPresenter(private val view: IMonthHistoryMVP.view) : IMonthHistoryMVP.presenter {

    private val adapter = GroupAdapter<ViewHolder>()
    val listFields: ArrayList<Feed> = ArrayList()

    override fun loadMonthFields(type: Int) {

        MonitoringSystem.sInstance.service.getDataWithDateFieldTemperature(type, Constants.API_KEY_THING_SPEAK, 30)
            .enqueue(object :
                Callback<ThingSpeakResponse> {
                override fun onFailure(call: Call<ThingSpeakResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<ThingSpeakResponse>, response: Response<ThingSpeakResponse>) {
                    val fields = response.body()
                    for (feed in fields!!.feeds) {
                        listFields.add(feed)

                    }
                    for (feed in listFields.reversed()) {
                        adapter.add(HistoryAdapter(feed, type))
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