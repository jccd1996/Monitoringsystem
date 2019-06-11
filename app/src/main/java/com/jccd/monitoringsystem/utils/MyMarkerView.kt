package com.jccd.monitoringsystem.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.jccd.monitoringsystem.R

class MyMarkerView(context: Context, layoutResource: Int, type: String) : MarkerView(context, layoutResource) {

    private val tvContent: TextView = findViewById(R.id.tvContent)

    private val unitValue = type

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @SuppressLint("SetTextI18n")
    override fun refreshContent(e: Entry?, highlight: Highlight?) {

        if (e is CandleEntry) {

            val ce = e as CandleEntry?

            tvContent.text = Utils.formatNumber(ce!!.high, 0, true) + Constants.EMPTY_SPACE + unitValue
        } else {
            tvContent.text = Utils.formatNumber(e!!.y, 0, true) + Constants.EMPTY_SPACE + unitValue
        }

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}