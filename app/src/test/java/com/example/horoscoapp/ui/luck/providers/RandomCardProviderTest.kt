package com.example.horoscoapp.ui.luck.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class RandomCardProviderTest {

    @Test
    fun `getRandomCard Should Return a Random Card`() {
        // Given
        val randomCardProvider = RandomCardProvider()

        // When
        val randomCard = randomCardProvider.getLuck()

        // Then
        assertNotNull(randomCard)
    }
}