package com.example.horoscoapp.data.provider

import com.example.horoscoapp.domain.model.HoroscopeInfo
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    // get all the horoscope
    fun getHoroscope(): List<HoroscopeInfo> {
        return listOf(
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
}
