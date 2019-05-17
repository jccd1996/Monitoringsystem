package com.jccd.monitoringsystem.ui.water_level.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.WaterLevel
import com.jccd.monitoringsystem.ui.water_level.IWaterLevelMVP
import com.jccd.monitoringsystem.ui.water_level.WaterLevelPresenter
import kotlinx.android.synthetic.main.fragment_water_level.*

class WaterLevelFragment : Fragment(), IWaterLevelMVP.view {

    private lateinit var presenter: WaterLevelPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = WaterLevelPresenter(this)
        customToolbar()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadLastWaterLevelField()

    }

    override fun setDataWaterLevel(waterLevel: WaterLevel) {
        tvLevelWater.text = waterLevel.waterLevel
        tvDate.text = waterLevel.createdAt
    }

    fun customToolbar(){
        (activity as AppCompatActivity).supportActionBar!!.title =
            activity!!.applicationContext.getString(R.string.menu_level_water)
    }
}
