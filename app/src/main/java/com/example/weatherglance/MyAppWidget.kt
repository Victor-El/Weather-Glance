package com.example.weatherglance

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import kotlinx.coroutines.launch

class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            GlanceTheme(GlanceTheme.colors) {
                Scaffold(titleBar = {
                    TitleBar(startIcon = ImageProvider(R.drawable.ic_android_black_24dp), title = "Weather Glance")
                }, backgroundColor = GlanceTheme.colors.secondaryContainer) {
                    MyCounter(context, id)
                }
            }
        }
    }
}

@Composable
fun MyCounter(context: Context, glanceId: GlanceId) {
    val scope = rememberCoroutineScope()
    val repo = WeatherRepository.getRepo(context, scope)

    val weatherDataModel by repo.weatherDataFlow.collectAsState()

    LaunchedEffect(key1 = weatherDataModel) {
        repo.update(glanceId)
    }

    Column(
        modifier = GlanceModifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(weatherDataModel.data?.location?.name ?: "Nowhere", style = TextStyle(fontSize = 16.sp, color = GlanceTheme.colors.onSurface))
        Spacer(modifier = GlanceModifier.height(8.dp))
        Text("${weatherDataModel.data?.current?.tempC ?: 0} \u00B0 C", style = TextStyle(fontSize = 36.sp, color = GlanceTheme.colors.onSurface))
        Spacer(modifier = GlanceModifier.height(16.dp))
        Button(text = "Abuja", onClick = {
            scope.launch {
                Log.d("GLANCE", weatherDataModel.toString())
                repo.getWeather("Abuja")
            }
        })
        Spacer(modifier = GlanceModifier.height(8.dp))
        Button(text = "Lagos", onClick = {
            scope.launch {
                Log.d("GLANCE", weatherDataModel.toString())
                repo.getWeather("Lagos")
            }
        })
    }
}