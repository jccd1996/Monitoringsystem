package com.jccd.monitoringsystem.ui.historylist.history.week_history


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jccd.monitoringsystem.R


class WeekHistoryFragment : Fragment() {

    private var type: Int = 0
    private val TYPE_KEY = "type"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_week_history, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(TYPE_KEY)
        Log.d("TYPExD", type.toString())
    }

}
