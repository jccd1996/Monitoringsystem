package com.jccd.monitoringsystem.ui.temperature.detail

import com.jccd.monitoringsystem.db.model.Temperature

interface ITemperatureMVP {
    interface view{
        fun setDataTemperature(temperature: Temperature)
    }
    interface presenter{
        fun loadLastTemperatureField()
    }
}