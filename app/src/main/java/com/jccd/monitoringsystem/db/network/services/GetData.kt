package com.jccd.monitoringsystem.db.network.services

import com.jccd.monitoringsystem.db.model.PhLevel
import com.jccd.monitoringsystem.db.model.Temperature
import com.jccd.monitoringsystem.db.model.WaterLevel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface GetData {

    @GET( "field/1/last.json")
    fun getTemperatureLastField(@Query("api_key") apiKey: String): Call<Temperature>

    @GET( "field/2/last.json")
    fun getWaterLevelLastField(@Query("api_key") apiKey: String): Call<WaterLevel>

    @GET( "field/3/last.json")
    fun getPhLevelLastField(@Query("api_key") apiKey: String): Call<PhLevel>
}