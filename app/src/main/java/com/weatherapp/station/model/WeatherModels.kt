package com.weatherapp.station.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current") val current: CurrentWeather,
    @SerializedName("daily") val daily: List<DailyForecast>,
    @SerializedName("hourly") val hourly: List<HourlyForecast>
)

data class CurrentWeather(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("precip") val accumulatedRain: Double
)

data class DailyForecast(
    @SerializedName("date") val date: String,
    @SerializedName("temp_max") val maxTemperature: Double,
    @SerializedName("temp_min") val minTemperature: Double,
    @SerializedName("pop") val rainProbability: Int
)

data class HourlyForecast(
    @SerializedName("time") val time: String,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("pop") val rainProbability: Int
)
