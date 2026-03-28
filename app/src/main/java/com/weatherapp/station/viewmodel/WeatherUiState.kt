package com.weatherapp.station.viewmodel

import com.weatherapp.station.model.WeatherResponse

sealed interface WeatherUiState {
    data object Loading : WeatherUiState
    data class Success(val data: WeatherResponse) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}
