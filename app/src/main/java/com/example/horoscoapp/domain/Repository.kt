package com.example.horoscoapp.domain

interface Repository {
    suspend fun getHoroscope(sign: String): String
}