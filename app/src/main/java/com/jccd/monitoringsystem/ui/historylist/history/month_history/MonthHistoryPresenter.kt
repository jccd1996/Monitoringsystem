package com.jccd.monitoringsystem.ui.historylist.history.month_history

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
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.db.network.response.ThingSpeakResponse
import com.jccd.monitoringsystem.ui.adapters.HistoryAdapter
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.DayAxisValueFormatter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.jccd.monitoringsystem.utils.MyMarkerView


class MonthHistoryPresenter(private val view: IMonthHistoryMVP.view) : IMonthHistoryMVP.presenter {

    private val adapter = GroupAdapter<ViewHolder>()
    val listFields: ArrayList<Feed> = ArrayList()
    val listData: ArrayList<Entry> = ArrayList()
    private lateinit var lineChart: LineChart
    private lateinit var mv : MyMarkerView
    val context = MonitoringSystem.getInstance().getContext()!!

    override fun loadMonthFields(type: Int, isGraphic: Boolean) {

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

        lineChart.setTouchEnabled(true)
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        val xAxisFormatter = DayAxisValueFormatter(lineChart)
//        xAxis.granularity = 1f // only intervals of 1 day
//        xAxis.labelCount = 7
//        xAxis.valueFormatter = xAxisFormatter


        val set1: LineDataSet = LineDataSet(list, Constants.EMPTY_SPACE)
        when (type) {
            1 -> {
                set1.label = context.getString(R.string.menu_temperature)
                mv = MyMarkerView(context, R.layout.custom_marker_view,context.getString(R.string.temperature_symbol))
            }
            2 -> {
                set1.label = context.getString(R.string.menu_level_water)
                mv = MyMarkerView(context, R.layout.custom_marker_view,context.getString(R.string.unit_water_level))
            }
            3 -> {
                set1.label = context.getString(R.string.menu_ph)
                mv = MyMarkerView(context, R.layout.custom_marker_view,Constants.EMPTY)
            }
        }

        mv.chartView = lineChart
        lineChart.marker = mv

        set1.fillAlpha = 110
        set1.color = Color.RED
        set1.lineWidth = 2f
        set1.valueTextSize = 10f
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)
        val data: LineData = LineData(dataSets)
        lineChart.visibility = View.VISIBLE
        lineChart.data = data
        lineChart.animateX(1000)
    }
}
