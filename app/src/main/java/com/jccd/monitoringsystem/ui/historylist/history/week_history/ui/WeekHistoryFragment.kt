package com.jccd.monitoringsystem.ui.historylist.history.week_history.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.ui.historylist.history.week_history.IWeekHistory
import com.jccd.monitoringsystem.ui.historylist.history.week_history.WeekHistoryPresenter
import com.jccd.monitoringsystem.ui.importants.ui.ImportantDetailActivity
import com.jccd.monitoringsystem.utils.Constants
import kotlinx.android.synthetic.main.fragment_week_history.*


class WeekHistoryFragment : Fragment(), IWeekHistory.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"
    private lateinit var presenter: IWeekHistory.presenter
    private val FEED_DETAIL = "feed"

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
        presenter.loadWeekFields(type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        Log.d("TYPExD", type.toString())
    }

    override fun getRecyclerView(): RecyclerView = rvWeekHistory

    override fun goToDetail(type: Int, feed: Feed) {
        val intent = Intent(activity, ImportantDetailActivity::class.java)
        intent.putExtra(TYPE_KEY, type)
        intent.putExtra(FEED_DETAIL, feed)
        startActivity(intent)
    }
}
