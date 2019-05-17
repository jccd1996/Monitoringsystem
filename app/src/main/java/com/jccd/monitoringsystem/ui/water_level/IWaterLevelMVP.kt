package com.jccd.monitoringsystem.ui.water_level

import com.jccd.monitoringsystem.db.model.WaterLevel

interface IWaterLevelMVP {
    interface view{
        fun setDataWaterLevel(waterLevel: WaterLevel)
    }
    interface presenter{
        fun loadLastWaterLevelField()
    }
}