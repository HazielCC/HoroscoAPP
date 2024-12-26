package com.example.horoscoapp.data.services.motherobject

import com.example.horoscoapp.data.services.response.PredictionResponse

object HoroscopeMotherObject {
    val anyResponse = PredictionResponse(
        date = "2020-01-01",
        horoscope = "Hoy será un día asqueroso para ti.",
        icon = "https://newastro.vercel.app/static/assets/zodiac-1.png",
        id = 10,
        sign = "aries"
    )
}
