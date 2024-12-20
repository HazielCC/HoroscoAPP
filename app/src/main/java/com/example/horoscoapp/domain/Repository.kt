package com.example.horoscoapp.domain

import com.example.horoscoapp.domain.model.PredictionModel


interface Repository {
    suspend fun getHoroscope(sign: String): PredictionModel?
}