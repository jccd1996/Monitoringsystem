package com.jccd.monitoringsystem.ui.historylist.history.day_history.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.ui.historylist.history.day_history.DayHistoryPresenter
import com.jccd.monitoringsystem.ui.historylist.history.day_history.IDayHistoryMVP
import com.jccd.monitoringsystem.ui.importants.detail.ui.ImportantDetailActivity
import kotlinx.android.synthetic.main.fragment_day_history.*

class DayHistoryFragment : Fragment(), IDayHistoryMVP.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"
    private val KEY_GRAPHIC = "isGraphic"
    private var isGraphic = false
    private val FEED_DETAIL = "feed"
    private lateinit var presenter: IDayHistoryMVP.presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DayHistoryPresenter(this)
        presenter.loadRecyclerView()

        if(isGraphic){
            rvDayHistory.visibility = View.GONE
            clDayHistory.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            presenter.loadDayFields(type,isGraphic)

        }else{
            presenter.loadDayFields(type,false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        isGraphic = arguments!!.getBoolean(KEY_GRAPHIC)

        Log.d("TYPExDDayOnCreate", type.toString())
        Log.d("TYPExDDayOnCreateGrap", isGraphic.toString())
    }

    override fun getRecyclerView(): RecyclerView = rvDayHistory
    override fun getLineChart(): LineChart = lineChart

    override fun goToDetail(type: Int, feed: Feed) {
        val intent = Intent(activity, ImportantDetailActivity::class.java)
        intent.putExtra(TYPE_KEY, type)
        intent.putExtra(FEED_DETAIL, feed)
        startActivity(intent)
    }
}
