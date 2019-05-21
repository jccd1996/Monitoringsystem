package com.jccd.monitoringsystem.ui.historylist.history.month_history.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.ui.historylist.history.month_history.IMonthHistoryMVP
import com.jccd.monitoringsystem.ui.historylist.history.month_history.MonthHistoryPresenter
import com.jccd.monitoringsystem.ui.importants.ui.ImportantDetailActivity
import kotlinx.android.synthetic.main.fragment_month_history.*


class MonthHistoryFragment : Fragment(),IMonthHistoryMVP.view {


    private var type: Int = 0
    private val TYPE_KEY = "type"
    private val FEED_DETAIL = "feed"

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
        presenter.loadMonthFields(type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
    }
    override fun getRecyclerView(): RecyclerView = rvMonthHistory

    override fun goToDetail(type: Int, feed: Feed) {
        val intent = Intent(activity, ImportantDetailActivity::class.java)
        intent.putExtra(TYPE_KEY, type)
        intent.putExtra(FEED_DETAIL, feed)
        startActivity(intent)
    }
}
