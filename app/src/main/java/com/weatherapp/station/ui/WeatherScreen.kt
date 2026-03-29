package com.weatherapp.station.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weatherapp.station.viewmodel.WeatherUiState
import com.weatherapp.station.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val state = uiState) {
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            is WeatherUiState.Success -> {
                val current = state.data.current
                val hourly = state.data.hourly
                val daily = state.data.daily
                
                // Conversión placeholder de la dirección a grados
                val windDirectionDegrees = when(current.windDirection.uppercase()) {
                    "N" -> 0f
                    "NE" -> 45f
                    "E" -> 90f
                    "SE" -> 135f
                    "S" -> 180f
                    "SW" -> 225f
                    "W" -> 270f
                    "NW" -> 315f
                    else -> 0f
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Mocoa, Putumayo",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(bottom = 24.dp, top = 32.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WeatherMetricCard(
                            title = "Temperatura",
                            value = "${current.temperature}°C",
                            modifier = Modifier.weight(1f)
                        )
                        Box(modifier = Modifier.weight(1f).height(120.dp), contentAlignment = Alignment.Center) {
                            WindGauge(
                                windSpeed = current.windSpeed.toFloat(),
                                windDirectionDegrees = windDirectionDegrees
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        WeatherMetricCard(
                            title = "Humedad",
                            value = "${current.humidity}%",
                            modifier = Modifier.weight(1f)
                        )
                        WeatherMetricCard(
                            title = "Presión",
                            value = "${current.pressure} hPa",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Pronóstico por Hora",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Pasar datos reales de pronóstico por hora a UI
                    HourlyForecastRow(forecasts = hourly)

                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Pronóstico Extendido",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Pasar datos reales de pronóstico diario a UI
                    DailyForecastList(forecasts = daily)
                }
            }
        }
    }
}
