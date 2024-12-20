package com.example.horoscoapp.domain.model

data class PredictionModel(
    val date: String = "", // 2020-01-01
    val horoscope: String = "", // Hoy será un día asqueroso para ti.
    val icon: String? = "", // https://newastro.vercel.app/static/assets/zodiac-1.png
    val id: Int? = 0, // 10
    val sign: String = "" // aries
)