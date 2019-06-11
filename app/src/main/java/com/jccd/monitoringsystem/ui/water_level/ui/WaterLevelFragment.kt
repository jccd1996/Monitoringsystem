package com.jccd.monitoringsystem.ui.water_level.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.WaterLevel
import com.jccd.monitoringsystem.ui.historylist.HistoryActivity
import com.jccd.monitoringsystem.ui.water_level.IWaterLevelMVP
import com.jccd.monitoringsystem.ui.water_level.WaterLevelPresenter
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ConvertDate
import kotlinx.android.synthetic.main.fragment_water_level.*

class WaterLevelFragment : Fragment(), IWaterLevelMVP.view {

    private lateinit var presenter: WaterLevelPresenter
    private val TYPE_KEY = "type"
    private val KEY_GRAPHIC = "isGraphic"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = WaterLevelPresenter(this)
        customToolbar()
        return inflater.inflate(R.layout.fragment_water_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadLastWaterLevelField()

        bWaterLevel.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            intent.putExtra(TYPE_KEY, Constants.KEY_TYPE_WATER_LEVEL)
            startActivity(intent)
        }

        bGraphics.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            intent.putExtra(TYPE_KEY,Constants.KEY_TYPE_WATER_LEVEL)
            intent.putExtra(KEY_GRAPHIC,true)
            startActivity(intent)
        }
    }

    override fun setDataWaterLevel(waterLevel: WaterLevel) {
        tvLevelWater.text = waterLevel.waterLevel + Constants.EMPTY_SPACE +
                activity!!.applicationContext.getString(R.string.unit_water_level)
        tvDate.text = ConvertDate.converToDateColombian(waterLevel.createdAt)
    }

    fun customToolbar() {
        (activity as AppCompatActivity).supportActionBar!!.title =
            activity!!.applicationContext.getString(R.string.menu_level_water)
    }
}
