package com.weatherapp.station.network

import com.weatherapp.station.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    @GET("/weather/data")
    suspend fun getWeatherData(): Response<WeatherResponse>
}
