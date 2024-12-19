package com.example.horoscoapp.data.services

import com.example.horoscoapp.data.services.response.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiServices {
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): Response<HoroscopeResponse>
}