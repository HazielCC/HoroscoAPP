package com.example.horoscoapp.ui.detail

sealed class HoroscopeDetailState {
    data object Loading : HoroscopeDetailState()
    data class Success(val prediction: String) : HoroscopeDetailState()
    data class Error(val error: String) : HoroscopeDetailState()
}