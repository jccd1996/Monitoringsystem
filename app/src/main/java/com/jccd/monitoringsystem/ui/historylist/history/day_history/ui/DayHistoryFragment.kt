package com.jccd.monitoringsystem.ui.historylist.history.day_history.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.historylist.history.day_history.DayHistoryPresenter
import com.jccd.monitoringsystem.ui.historylist.history.day_history.IDayHistoryMVP
import kotlinx.android.synthetic.main.fragment_day_history.*

class DayHistoryFragment : Fragment(),IDayHistoryMVP.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"
    private lateinit var presenter:IDayHistoryMVP.presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DayHistoryPresenter (this)
        presenter.loadRecyclerView()
        presenter.loadDayFields()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        Log.d("TYPExDDayOnCreate", type.toString())
    }

    override fun getRecyclerView(): RecyclerView = rvDayHistory
}
