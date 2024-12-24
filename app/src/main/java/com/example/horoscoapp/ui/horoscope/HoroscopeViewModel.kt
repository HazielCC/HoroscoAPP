package com.example.horoscoapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscoapp.data.provider.HoroscopeProvider
import com.example.horoscoapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    horoscopeProvider: HoroscopeProvider
) :
    ViewModel() {
    // MutableStateFlow is a state-holder observable flow that emits the current and new state
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    // Initialize the horoscope list
    init {
        _horoscope.value = horoscopeProvider.getHoroscope()
    }
}
