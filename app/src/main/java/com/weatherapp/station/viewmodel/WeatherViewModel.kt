package com.weatherapp.station.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.station.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    init {
        // Initial fetch call
        fetchWeather()
    }

    fun fetchWeather() {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getWeather()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _uiState.value = WeatherUiState.Success(body)
                    } else {
                        _uiState.value = WeatherUiState.Error("Empty response body")
                    }
                } else {
                    _uiState.value = WeatherUiState.Error("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.localizedMessage ?: "Unknown network error")
            }
        }
    }
}
