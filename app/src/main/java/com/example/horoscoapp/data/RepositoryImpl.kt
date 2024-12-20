package com.example.horoscoapp.data

import com.example.horoscoapp.data.services.HoroscopeApiServices
import com.example.horoscoapp.domain.Repository
import com.example.horoscoapp.domain.model.PredictionModel
import com.orhanobut.logger.Logger
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiServices: HoroscopeApiServices
) : Repository {

    override suspend fun getHoroscope(sign: String): PredictionModel? {
        runCatching { apiServices.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Logger.e("error ${it.message}") }

        return null
    }
}