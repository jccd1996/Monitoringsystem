package com.jccd.monitoringsystem.ui.historylist.history.week_history.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.historylist.history.week_history.IWeekHistory
import com.jccd.monitoringsystem.ui.historylist.history.week_history.WeekHistoryPresenter
import kotlinx.android.synthetic.main.fragment_week_history.*


class WeekHistoryFragment : Fragment(),IWeekHistory.view {


    private var type: Int = 0
    private val TYPE_KEY = "type"
    private lateinit var presenter: IWeekHistory.presenter

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
        presenter.loadWeekFields()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        Log.d("TYPExD", type.toString())
    }
    override fun getRecyclerView(): RecyclerView = rvWeekHistory
}
