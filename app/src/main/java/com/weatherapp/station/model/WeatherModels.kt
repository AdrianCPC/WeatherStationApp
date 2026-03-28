package com.weatherapp.station.model

data class WeatherResponse(
    val current: CurrentWeather,
    val daily: List<DailyForecast>,
    val hourly: List<HourlyForecast>
)

data class CurrentWeather(
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: String,
    val pressure: Int,
    val accumulatedRain: Double
)

data class DailyForecast(
    val date: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val rainProbability: Int
)

data class HourlyForecast(
    val time: String,
    val temperature: Double,
    val rainProbability: Int
)
