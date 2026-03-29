package com.weatherapp.station.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weatherapp.station.model.DailyForecast
import com.weatherapp.station.model.HourlyForecast

@Composable
fun HourlyForecastRow(forecasts: List<HourlyForecast>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(forecasts) { forecast ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = forecast.time, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Weather Icon")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${forecast.temperature.toInt()}°C", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun DailyForecastList(forecasts: List<DailyForecast>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        forecasts.forEach { forecast ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = forecast.date, style = MaterialTheme.typography.titleMedium)
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Máx: ${forecast.maxTemperature}°C", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Mín: ${forecast.minTemperature}°C", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Lluvia: ${forecast.rainProbability}%", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}
