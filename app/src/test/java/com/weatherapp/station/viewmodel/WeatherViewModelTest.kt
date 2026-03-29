package com.weatherapp.station.viewmodel

import com.weatherapp.station.model.*
import com.weatherapp.station.network.WeatherApiService
import com.weatherapp.station.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchWeather_success_updatesUiStateToSuccess() = runTest {
        // Arrange
        val fakeApiService = object : WeatherApiService {
            override suspend fun getWeatherData(lat: Double, lon: Double, apiKey: String): Response<WeatherResponse> {
                val fakeResponse = WeatherResponse(
                    current = CurrentWeather(25.0, 60, 10.0, "N", 1012, 0.0),
                    daily = emptyList(),
                    hourly = emptyList()
                )
                return Response.success(fakeResponse)
            }
        }
        val repository = WeatherRepository(fakeApiService)
        
        // Act
        val viewModel = WeatherViewModel(repository)
        advanceUntilIdle() // Espera a que termine la corrutina en ViewModel init

        // Assert
        val state = viewModel.uiState.value
        assertTrue("El estado debería ser Success pero es $state", state is WeatherUiState.Success)
    }
}
