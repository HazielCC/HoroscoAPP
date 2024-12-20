package com.example.horoscoapp.data.services.response


import com.example.horoscoapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("date") val date: String = "", // 2020-01-01
    @SerializedName("horoscope") val horoscope: String = "", // Hoy será un día asqueroso para ti.
    @SerializedName("icon") val icon: String = "", // https://newastro.vercel.app/static/assets/zodiac-1.png
    @SerializedName("id") val id: Int = 0, // 10
    @SerializedName("sign") val sign: String = "" // aries
) {
    fun toDomain(): PredictionModel {
        return PredictionModel(
            date = date,
            horoscope = horoscope,
            icon = icon,
            id = id,
            sign = sign
        )
    }
}