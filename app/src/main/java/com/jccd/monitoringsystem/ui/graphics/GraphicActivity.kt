package com.jccd.monitoringsystem.ui.graphics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.jccd.monitoringsystem.R
import kotlinx.android.synthetic.main.activity_graphic.*

class GraphicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphic)

        brGraphics.setDrawBarShadow(false)
        brGraphics.setDrawValueAboveBar(false)
        brGraphics.setPinchZoom(true)
        brGraphics.setDrawGridBackground(true)

        var test:ArrayList<BarEntry> = ArrayList()

        test.add( BarEntry(1f,40f))
        test.add( BarEntry(2f,40f))
        test.add( BarEntry(3f,40f))
        test.add( BarEntry(4f,40f))

        var dataSet: BarDataSet = BarDataSet(test,"Data set 1")
        dataSet.color = resources.getColor(R.color.unibague_blue)

        var data: BarData = BarData(dataSet)
        data.barWidth = 0.9f
        brGraphics.data = data
    }
}
