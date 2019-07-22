package com.jccd.monitoringsystem.utils

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.formatter.ValueFormatter

class DayAxisValueFormatter(private val chart: LineChart) : ValueFormatter() {



    override fun getFormattedValue(value: Float): String {
        return "12"
    }


}
