package com.jccd.monitoringsystem.ui.historylist.history.day_history


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jccd.monitoringsystem.R

class DayHistoryFragment : Fragment() {

    private var type: Int = 0
    private val TYPE_KEY = "type"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_history, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        Log.d("TYPExDDayOnCreate", type.toString())
    }
}
