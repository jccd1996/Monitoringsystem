package com.jccd.monitoringsystem.ui.temperature.history.month_history


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.temperature.history.day_history.DayHistoryTemperatureFragmentArgs

class MonthHistoryTemperatureFragment : Fragment() {
    val args by navArgs<DayHistoryTemperatureFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val type = arguments!!.getInt("type")
        Log.d("TYPExDMonth", args.type.toString())
        return inflater.inflate(R.layout.fragment_month_history_temperature, container, false)
    }


}
