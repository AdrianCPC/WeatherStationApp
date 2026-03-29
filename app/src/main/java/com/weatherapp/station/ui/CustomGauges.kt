package com.weatherapp.station.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WindGauge(windSpeed: Float, windDirectionDegrees: Float, modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    
    Box(
        modifier = modifier.aspectRatio(1f).padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2

            // Draw outer circle
            drawCircle(
                color = onSurfaceColor.copy(alpha = 0.2f),
                radius = radius,
                center = center,
                style = Stroke(width = 4.dp.toPx())
            )

            // Draw compass ticks (N, S, E, W)
            val tickLength = 10.dp.toPx()
            for (i in 0 until 4) {
                val angle = Math.toRadians((i * 90 - 90).toDouble())
                val startX = center.x + (radius - tickLength) * cos(angle).toFloat()
                val startY = center.y + (radius - tickLength) * sin(angle).toFloat()
                val endX = center.x + radius * cos(angle).toFloat()
                val endY = center.y + radius * sin(angle).toFloat()
                
                drawLine(
                    color = onSurfaceColor,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            // Draw Arrow (needle)
            rotate(degrees = windDirectionDegrees, pivot = center) {
                val path = Path().apply {
                    moveTo(center.x, center.y - radius * 0.7f) // Top tip
                    lineTo(center.x - 8.dp.toPx(), center.y + 12.dp.toPx()) // Bottom left
                    lineTo(center.x, center.y) // Inner notch
                    lineTo(center.x + 8.dp.toPx(), center.y + 12.dp.toPx()) // Bottom right
                    close()
                }
                drawPath(path = path, color = primaryColor)
            }
        }
        
        Text(
            text = "${windSpeed.toInt()}\nkm/h",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}
