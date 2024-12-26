package com.example.horoscoapp.data.services.response

import com.example.horoscoapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest {

    @Test
    fun `toDomain Should Return a Correct PredictionModel`() {
        // Given
        val horoscopeResponse = anyResponse
        val horoscopeResponseCopy = anyResponse.copy(sign = "example")

        // When
        val predictionModel = horoscopeResponse.toDomain()

        // Then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.date shouldBe horoscopeResponse.date
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
        predictionModel.icon shouldBe horoscopeResponse.icon
        predictionModel.id shouldBe horoscopeResponse.id

    }
}