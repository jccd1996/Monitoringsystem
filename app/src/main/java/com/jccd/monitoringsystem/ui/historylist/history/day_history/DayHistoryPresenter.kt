package com.jccd.monitoringsystem.ui.historylist.history.day_history

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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

class DayHistoryPresenter(private val view: IDayHistoryMVP.view) : IDayHistoryMVP.presenter {

    private val adapter = GroupAdapter<ViewHolder>()
    val listFields: ArrayList<Feed> = ArrayList()
    val listData: ArrayList<Entry> = ArrayList()
    private lateinit var lineChart: LineChart

    override fun loadDayFields(type: Int, isGraphic: Boolean) {
        MonitoringSystem.sInstance.service.getDataWithDateFieldTemperature(type, Constants.API_KEY_THING_SPEAK, 1)
            .enqueue(object :
                Callback<ThingSpeakResponse> {
                override fun onFailure(call: Call<ThingSpeakResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<ThingSpeakResponse>, response: Response<ThingSpeakResponse>) {
                    val fields = response.body()
                    response.raw().request().url()
                    for (feed in fields!!.feeds) {
                        listFields.add(feed)
                    }
                    if (isGraphic) {
                        for (feed in listFields) {
                            when (type) {
                                1 -> {
                                    if (feed.temperature != null) {
                                        listData.add(Entry(feed.entryId.toFloat(), feed.temperature.toFloat()))
                                    }
                                }
                                2 -> {
                                    if (feed.waterLeve != null) {
                                        listData.add(Entry(feed.entryId.toFloat(), feed.waterLeve.toFloat()))
                                    }
                                }
                                3 -> {
                                    if (feed.phLevel != null) {
                                        listData.add(Entry(feed.entryId.toFloat(), feed.phLevel.toFloat()))
                                    }
                                }
                            }
                        }
                        loadGraphic(listData, type)
                    } else {
                        for (feed in listFields.reversed()) {
                            adapter.add(HistoryAdapter(feed, type))
                        }
                    }
                    for (feed in listFields.reversed()) {
                        adapter.add(HistoryAdapter(feed, type))
                    }
                }
            })
        adapter.setOnItemClickListener { item, _ ->
            val fieldItem = item as HistoryAdapter
            val fieldDetail = fieldItem.feed
            view.goToDetail(type, fieldDetail)
        }
    }

    override fun loadRecyclerView() {
        view.getRecyclerView().apply {
            layoutManager = LinearLayoutManager(context)
        }
        view.getRecyclerView().adapter = adapter
    }

    fun loadGraphic(list: ArrayList<Entry>, type: Int) {
        lineChart = view.getLineChart()
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)
        lineChart.setPinchZoom(true)

        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        val set1: LineDataSet = LineDataSet(list, Constants.EMPTY_SPACE)
        when (type) {
            1 -> set1.label = "Temperatura"
            2 -> set1.label = "Nivel de agua"
            3 -> set1.label = "pH"
        }
        set1.fillAlpha = 110
        set1.color = Color.RED
        set1.lineWidth = 2f
        set1.valueTextSize = 10f
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)
        val data: LineData = LineData(dataSets)
        lineChart.data = data
        lineChart.visibility = View.VISIBLE
    }
}
