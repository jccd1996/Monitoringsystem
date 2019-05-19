package com.jccd.monitoringsystem.db.network.services

import com.jccd.monitoringsystem.db.model.PhLevel
import com.jccd.monitoringsystem.db.model.Temperature
import com.jccd.monitoringsystem.db.model.WaterLevel
import com.jccd.monitoringsystem.db.network.response.ThingSpeakResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface GetData {

    @GET("field/1/last.json")
    fun getTemperatureLastField(
        @Query("api_key") apiKey: String,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<Temperature>

    @GET("field/1.json")
    fun getTemperatureAllField(@Query("api_key") apiKey: String): Call<ThingSpeakResponse>

    @GET("field/1.json")
    fun getDataWithDateFieldTemperature(
        @Query("api_key") apiKey: String,
        @Query("days") days: Int,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<ThingSpeakResponse>

    @GET("field/2/last.json")
    fun getWaterLevelLastField(
        @Query("api_key") apiKey: String,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<WaterLevel>

    @GET("field/1.json")
    fun getWaterLevelAllField(@Query("api_key") apiKey: String): Call<ThingSpeakResponse>

    @GET("field/2.json")
    fun getDataWithDateFieldWater(
        @Query("api_key") apiKey: String,
        @Query("days") days: Int,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<ThingSpeakResponse>

    @GET("field/3/last.json")
    fun getPhLevelLastField(
        @Query("api_key") apiKey: String,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<PhLevel>

    @GET("field/1.json")
    fun getPhAllField(@Query("api_key") apiKey: String): Call<ThingSpeakResponse>

    @GET("field/3.json")
    fun getDataWithDateFieldPhLevel(
        @Query("api_key") apiKey: String,
        @Query("days") days: Int,
        @Query("timezone") timeZone: String = "America/Bogota"
    ): Call<ThingSpeakResponse>
}
