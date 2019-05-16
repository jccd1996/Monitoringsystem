package com.jccd.monitoringsystem.db.network.services

import retrofit2.http.GET
import retrofit2.http.Streaming
import okhttp3.ResponseBody
import retrofit2.Call


interface GetData {

    @GET("files/feeds.csv")
    @Streaming
    fun downloadFile(): Call<ResponseBody>
}