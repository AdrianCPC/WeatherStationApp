package com.weatherapp.station.repository

import com.weatherapp.station.model.WeatherResponse
import com.weatherapp.station.network.WeatherApiService
import retrofit2.Response

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeather(): Response<WeatherResponse> {
        return apiService.getWeatherData()
    }
}
