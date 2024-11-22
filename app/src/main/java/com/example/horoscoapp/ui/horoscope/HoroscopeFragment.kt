package com.example.horoscoapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscoapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    // Conecta el ViewModel con el fragmento
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Variable para el enlace de vista (binding)
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    // Método para crear la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño para este fragmento
        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Método llamado cuando la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI() // Inicializa la interfaz de usuario
    }

    // Inicializa la interfaz de usuario
    private fun initUI() {
        initUIState() // Inicializa el estado de la interfaz de usuario
    }

    // Inicializa el estado de la interfaz de usuario
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Recoge los datos del ViewModel y los registra en el log
                horoscopeViewModel.horoscope.collect {
                    Log.d("Hola", it.toString())
                }
            }
        }
    }

    // Método llamado cuando la vista es destruida
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libera el enlace de vista
    }
}
