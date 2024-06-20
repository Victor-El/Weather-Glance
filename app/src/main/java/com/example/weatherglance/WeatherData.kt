package com.example.weatherglance


import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
) {
    data class Current(
        @SerializedName("cloud")
        val cloud: Int?,
        @SerializedName("condition")
        val condition: Condition?,
        @SerializedName("dewpoint_c")
        val dewpointC: Double?,
        @SerializedName("dewpoint_f")
        val dewpointF: Double?,
        @SerializedName("feelslike_c")
        val feelslikeC: Double?,
        @SerializedName("feelslike_f")
        val feelslikeF: Double?,
        @SerializedName("gust_kph")
        val gustKph: Double?,
        @SerializedName("gust_mph")
        val gustMph: Double?,
        @SerializedName("heatindex_c")
        val heatindexC: Double?,
        @SerializedName("heatindex_f")
        val heatindexF: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("is_day")
        val isDay: Int?,
        @SerializedName("last_updated")
        val lastUpdated: String?,
        @SerializedName("last_updated_epoch")
        val lastUpdatedEpoch: Int?,
        @SerializedName("precip_in")
        val precipIn: Double?,
        @SerializedName("precip_mm")
        val precipMm: Double?,
        @SerializedName("pressure_in")
        val pressureIn: Double?,
        @SerializedName("pressure_mb")
        val pressureMb: Double?,
        @SerializedName("temp_c")
        val tempC: Double?,
        @SerializedName("temp_f")
        val tempF: Double?,
        @SerializedName("uv")
        val uv: Double?,
        @SerializedName("vis_km")
        val visKm: Double?,
        @SerializedName("vis_miles")
        val visMiles: Double?,
        @SerializedName("wind_degree")
        val windDegree: Int?,
        @SerializedName("wind_dir")
        val windDir: String?,
        @SerializedName("wind_kph")
        val windKph: Double?,
        @SerializedName("wind_mph")
        val windMph: Double?,
        @SerializedName("windchill_c")
        val windchillC: Double?,
        @SerializedName("windchill_f")
        val windchillF: Double?
    ) {
        data class Condition(
            @SerializedName("code")
            val code: Int?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("text")
            val text: String?
        )
    }

    data class Location(
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("localtime")
        val localtime: String?,
        @SerializedName("localtime_epoch")
        val localtimeEpoch: Int?,
        @SerializedName("lon")
        val lon: Double?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("tz_id")
        val tzId: String?
    )
}