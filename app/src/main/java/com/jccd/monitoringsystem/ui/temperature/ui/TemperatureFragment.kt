package com.jccd.monitoringsystem.ui.temperature.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Temperature
import com.jccd.monitoringsystem.ui.historylist.HistoryActivity
import com.jccd.monitoringsystem.ui.temperature.ITemperatureMVP
import com.jccd.monitoringsystem.ui.temperature.TemperaturePresenter
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ConvertDate
import kotlinx.android.synthetic.main.fragment_temperature.*

class TemperatureFragment : Fragment(), ITemperatureMVP.view {

    private lateinit var presenter: ITemperatureMVP.presenter
    private val TYPE_KEY = "type"
    private val KEY_GRAPHIC = "isGraphic"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = TemperaturePresenter(this)
        customToolbar()
        return inflater.inflate(R.layout.fragment_temperature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadLastTemperatureField()

        bTemperatureHistoy.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            intent.putExtra(TYPE_KEY,Constants.KEY_TYPE_TEMPERATURE)
            intent.putExtra(KEY_GRAPHIC,false)
            startActivity(intent)
        }

        bGraphics.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            intent.putExtra(TYPE_KEY,Constants.KEY_TYPE_TEMPERATURE)
            intent.putExtra(KEY_GRAPHIC,true)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setDataTemperature(temperature: Temperature) {
        tvTemperatura.text = temperature.temperature + Constants.EMPTY_SPACE +
                activity!!.applicationContext.getString(R.string.temperature_symbol)
        tvDate.text = ConvertDate.converToDateColombian(temperature.createdAt)
    }

    fun customToolbar(){
        (activity as AppCompatActivity).supportActionBar!!.title =
            activity!!.applicationContext.getString(R.string.menu_temperature)
    }
}
