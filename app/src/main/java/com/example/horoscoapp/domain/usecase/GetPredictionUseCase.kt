package com.example.horoscoapp.domain.usecase

import com.example.horoscoapp.domain.Repository
import com.example.horoscoapp.domain.model.PredictionModel
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(sing: String): PredictionModel? = repository.getHoroscope(sing)
}