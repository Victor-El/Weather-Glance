package com.example.weatherglance

import android.content.Context
import androidx.glance.GlanceId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository private constructor(
    val context: Context,
    val scope: CoroutineScope,
) {

    private var weatherService: WeatherService

    val weatherDataFlow = MutableStateFlow(WeatherModel())


    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
        val retro = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").client(client).addConverterFactory(GsonConverterFactory.create()).build()
        weatherService = retro.create(WeatherService::class.java)

    }

    suspend fun getWeather(city: String) {
        val data = try {
            val result = weatherService.getCityWeather(city)
            WeatherModel(
                loading = false,
                data = result,
                failure = false,
                success = true
            )
        } catch (e: Exception) {
            WeatherModel(
                loading = false,
                data = null,
                failure = true,
                message = e.localizedMessage,
                success = false
            )
        }

        weatherDataFlow.emit(data)
    }

    suspend fun update(glanceId: GlanceId) {
        MyAppWidget().update(context, glanceId)
    }

    companion object {
        private var instance: WeatherRepository? = null

        fun getRepo(context: Context, coroutineScope: CoroutineScope): WeatherRepository {
            if (instance == null) {
                instance = WeatherRepository(context, coroutineScope)
            }

            return instance!!
        }
    }

    data class WeatherModel(
        val loading: Boolean = false,
        val data: WeatherData? = null,
        val failure: Boolean = false,
        val message: String? = null,
        val success: Boolean = false
    )

}