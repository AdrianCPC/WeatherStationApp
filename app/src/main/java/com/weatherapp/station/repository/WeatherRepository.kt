package com.weatherapp.station.repository

import com.weatherapp.station.BuildConfig
import com.weatherapp.station.model.WeatherResponse
import com.weatherapp.station.network.WeatherApiService
import retrofit2.Response

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponse> {
        return apiService.getWeatherData(lat, lon, BuildConfig.API_KEY)
    }
}
