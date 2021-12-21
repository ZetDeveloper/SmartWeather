package com.example.smartweather.rest

import com.example.smartweather.models.RootService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("weather")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("APPID") appId: String
    ): Response<RootService>
}