package com.example.horoscoapp.ui.deteail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscoapp.utilities.dialogs.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    // Variables
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDeteailViewModel: HoroscopeDetailViewModel by viewModels()

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
        initUIState()
    }

    private fun initUIState() {
        args.type

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDeteailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> TODO()
                        is HoroscopeDetailState.Success -> TODO()
                    }

                }
            }
        }
    }

    private fun loadingState() {
        LoadingDialog().show(
            this@HoroscopeDetailActivity,
            "Cargando..."
        )
    }
}
