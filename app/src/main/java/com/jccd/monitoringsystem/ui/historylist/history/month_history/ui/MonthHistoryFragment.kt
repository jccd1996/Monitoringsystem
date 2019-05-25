package com.jccd.monitoringsystem.ui.historylist.history.month_history.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.ui.historylist.history.month_history.IMonthHistoryMVP
import com.jccd.monitoringsystem.ui.historylist.history.month_history.MonthHistoryPresenter
import com.jccd.monitoringsystem.ui.importants.detail.ui.ImportantDetailActivity
import kotlinx.android.synthetic.main.fragment_month_history.*


class MonthHistoryFragment : Fragment(),IMonthHistoryMVP.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"
    private val FEED_DETAIL = "feed"
    private val KEY_GRAPHIC = "isGraphic"
    private var isGraphic = false

    private lateinit var presenter: IMonthHistoryMVP.presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = MonthHistoryPresenter(this)
        return inflater.inflate(R.layout.fragment_month_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadRecyclerView()
        //presenter.loadMonthFields(type)
        if(isGraphic){
            rvMonthHistory.visibility = View.GONE
            clMonth.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            presenter.loadMonthFields(type,isGraphic)

        }else{
            presenter.loadMonthFields(type,false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        isGraphic = arguments!!.getBoolean(KEY_GRAPHIC)
    }
    override fun getRecyclerView(): RecyclerView = rvMonthHistory
    override fun getLineChart(): LineChart = lineChart

    override fun goToDetail(type: Int, feed: Feed) {
        val intent = Intent(activity, ImportantDetailActivity::class.java)
        intent.putExtra(TYPE_KEY, type)
        intent.putExtra(FEED_DETAIL, feed)
        startActivity(intent)
    }
}
