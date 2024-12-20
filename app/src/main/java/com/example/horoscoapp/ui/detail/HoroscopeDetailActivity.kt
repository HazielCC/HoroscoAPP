package com.example.horoscoapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscoapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    // Variables
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    // Argumentos de navegación
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater) // ViewBinding
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()
    }

    private fun initUI() {
        horoscopeDetailViewModel.getHorosope(args.type)
        initListeners()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }

                }
            }
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadingState() {
        binding.pbLoading.isVisible = true
    }

    private fun errorState() {
        binding.pbLoading.isVisible = false

    }

    private fun successState(horoscopeDetailState: HoroscopeDetailState.Success) {
        binding.pbLoading.isVisible = false

        binding.tvTitle.text = horoscopeDetailState.sing
        binding.tvDescription.text = horoscopeDetailState.prediction

        val image = when (horoscopeDetailState.horoscopeModel) {
            HoroscopeModel.ARIES -> R.drawable.detail_aries
            HoroscopeModel.TAURUS -> R.drawable.detail_taurus
            HoroscopeModel.GEMINI -> R.drawable.detail_gemini
            HoroscopeModel.CANCER -> R.drawable.detail_cancer
            HoroscopeModel.LEO -> R.drawable.detail_leo
            HoroscopeModel.VIRGO -> R.drawable.detail_virgo
            HoroscopeModel.LIBRA -> R.drawable.detail_libra
            HoroscopeModel.SCORPIO -> R.drawable.detail_scorpio
            HoroscopeModel.SAGITTARIUS -> R.drawable.detail_sagittarius
            HoroscopeModel.CAPRICORN -> R.drawable.detail_capricorn
            HoroscopeModel.AQUARIUS -> R.drawable.detail_aquarius
            HoroscopeModel.PISCES -> R.drawable.detail_pisces
        }

        binding.ivSing.setImageResource(image)
    }
}
