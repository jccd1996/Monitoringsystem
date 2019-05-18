package com.jccd.monitoringsystem.ui.temperature.history.week_history


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jccd.monitoringsystem.R


class WeekHistoryTemperatureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = arguments!!.getInt("type")
        Log.d("TYPExD", type.toString())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_history_temperature, container, false)
    }

}
