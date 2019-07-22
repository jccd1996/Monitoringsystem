package com.jccd.monitoringsystem.ui.historylist.history.week_history.ui


import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.ui.historylist.history.week_history.IWeekHistory
import com.jccd.monitoringsystem.ui.historylist.history.week_history.WeekHistoryPresenter
import com.jccd.monitoringsystem.ui.importants.detail.ui.ImportantDetailActivity
import kotlinx.android.synthetic.main.fragment_week_history.*


class WeekHistoryFragment : Fragment(), IWeekHistory.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"
    private lateinit var presenter: IWeekHistory.presenter
    private val FEED_DETAIL = "feed"
    private val KEY_GRAPHIC = "isGraphic"
    private var isGraphic = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_week_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = WeekHistoryPresenter(this)
        presenter.loadRecyclerView()
//        presenter.loadWeekFields(type)

        if(isGraphic){
            rvWeekHistory.visibility = View.GONE
            clWeek.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            presenter.loadWeekFields(type,isGraphic)

        }else{
            presenter.loadWeekFields(type,false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        isGraphic = arguments!!.getBoolean(KEY_GRAPHIC)
        Log.d("TYPExD", type.toString())
        Log.d("TYPExDDayOnCreateGrap", isGraphic.toString())
    }

    override fun getRecyclerView(): RecyclerView = rvWeekHistory
    override fun getLineChart(): LineChart = lineChart


    override fun goToDetail(type: Int, feed: Feed) {
        val intent = Intent(activity, ImportantDetailActivity::class.java)
        intent.putExtra(TYPE_KEY, type)
        intent.putExtra(FEED_DETAIL, feed)
        startActivity(intent)
    }

    fun loadTest(){
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)

        var test:ArrayList<Entry> = ArrayList()

        test.add( Entry(1f,40f))
        test.add( Entry(2f,80f))
        test.add( Entry(3f,20f))
        test.add( Entry(4f,30f))

        val set1: LineDataSet = LineDataSet(test,"Temperatura")
        set1.fillAlpha = 110
        set1.color = Color.RED
        set1.lineWidth = 2f
        set1.valueTextSize = 10f

        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)

        val data: LineData  = LineData(dataSets)

        lineChart.data = data
        clWeek.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        lineChart.visibility = View.VISIBLE
    }
}
