package com.jccd.monitoringsystem.ui.historylist.history.month_history.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.historylist.history.month_history.IMonthHistoryMVP
import com.jccd.monitoringsystem.ui.historylist.history.month_history.MonthHistoryPresenter
import kotlinx.android.synthetic.main.fragment_month_history.*


class MonthHistoryFragment : Fragment(),IMonthHistoryMVP.view {

    private var type: Int = 0
    private val TYPE_KEY = "type"

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
        presenter.loadMonthFields()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
    }
    override fun getRecyclerView(): RecyclerView = rvMonthHistory
}
