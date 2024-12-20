package com.example.horoscoapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscoapp.databinding.FragmentHoroscopeBinding
import com.example.horoscoapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscoapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscoapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscoapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscoapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscoapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscoapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscoapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscoapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscoapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscoapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscoapp.domain.model.HoroscopeInfo.Virgo
import com.example.horoscoapp.domain.model.HoroscopeModel
import com.example.horoscoapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    // Conecta el ViewModel con el fragmento
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Variable para el enlace de vista (binding)
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    // Metodo para crear la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño para este fragmento
        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Metodo llamado cuando la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI() // Inicializa la interfaz de usuario
    }

    // Inicializa la interfaz de usuario
    private fun initUI() {
        initUIState() // Inicializa el estado de la interfaz de usuario
        initRecycleView()
    }

    // Inicializa el estado de la interfaz de usuario
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Recoge los datos del ViewModel y los registra en el log
                horoscopeViewModel.horoscope.collect {
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    // Inicializa el RecycleView
    private fun initRecycleView() {
        // Inicializa el adaptador de la lista
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            val type = when (it) {
                Aquarius -> HoroscopeModel.AQUARIUS
                Aries -> HoroscopeModel.ARIES
                Cancer -> HoroscopeModel.CANCER
                Capricorn -> HoroscopeModel.CAPRICORN
                Gemini -> HoroscopeModel.GEMINI
                Leo -> HoroscopeModel.LEO
                Libra -> HoroscopeModel.LIBRA
                Pisces -> HoroscopeModel.PISCES
                Sagittarius -> HoroscopeModel.SAGITTARIUS
                Scorpio -> HoroscopeModel.SCORPIO
                Taurus -> HoroscopeModel.TAURUS
                Virgo -> HoroscopeModel.VIRGO

            }
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        // Establece el adaptador de la lista
        binding.rvHoroscope.apply {
            // layoutManager = LinearLayoutManager(context) // Establece el diseño de la lista
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    // Metodo llamado cuando la vista es destruida
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libera el enlace de vista
    }
}
