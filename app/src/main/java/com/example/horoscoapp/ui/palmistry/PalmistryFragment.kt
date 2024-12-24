package com.example.horoscoapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horoscoapp.databinding.FragmentPalmistryBinding
import com.example.horoscoapp.utilities.permissions.PermissionsService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PalmistryFragment : Fragment() {
    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    // Utilities
    private val tag = "PalmistryFragment"

    @Inject
    lateinit var permissionsService: PermissionsService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPalmistryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(tag, permissionsService.checkCameraPermission().toString())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
