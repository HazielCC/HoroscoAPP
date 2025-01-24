package com.example.horoscoapp.motherobject

import com.example.horoscoapp.data.services.response.PredictionResponse
import com.example.horoscoapp.domain.model.HoroscopeInfo

object HoroscopeMotherObject {
    val anyResponse = PredictionResponse(
        date = "2020-01-01",
        horoscope = "Hoy será un día asqueroso para ti.",
        icon = "https://newastro.vercel.app/static/assets/zodiac-1.png",
        id = 10,
        sign = "aries"
    )

    val horoscopeInfoList = listOf(
        HoroscopeInfo.Aries,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Pisces
    )
}
