package com.example.horoscoapp.ui.horoscope

import com.example.horoscoapp.data.provider.HoroscopeProvider
import com.example.horoscoapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    // Prepare the Test class
    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var horoscopeViewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When horoscopeViewModel is created, then it should not be null`() {
        // Given
        every { horoscopeProvider.getHoroscope() } returns horoscopeInfoList
        horoscopeViewModel = HoroscopeViewModel(horoscopeProvider)

        // When
        val horoscopes = horoscopeViewModel.horoscope.value

        // Then
        assertTrue(horoscopes.isNotEmpty())
    }


}