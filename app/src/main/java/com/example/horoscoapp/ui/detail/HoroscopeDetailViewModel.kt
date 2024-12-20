package com.example.horoscoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscoapp.domain.usecase.GetPredictionUseCase
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    fun example(sing: String) {
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val result =
                withContext(Dispatchers.IO) { getPredictionUseCase(sing = sing) }
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(result)
                Logger.d("DSADASD")
            }
        }
    }
}
