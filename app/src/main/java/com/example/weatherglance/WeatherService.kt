package com.example.weatherglance

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json?key=bc0fd7612f01488696275745222303")
    suspend fun getCityWeather(
        @Query("q") city: String,
    ): WeatherData

}