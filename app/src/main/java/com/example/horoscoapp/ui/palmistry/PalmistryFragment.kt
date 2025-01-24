package com.example.horoscoapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.FragmentPalmistryBinding
import com.example.horoscoapp.utilities.dialogs.MessageDialog
import com.example.horoscoapp.utilities.permissions.PermissionsService
import com.example.horoscoapp.utilities.permissions.PermissionsService.Companion.CAMERA_PERMISSION
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

    @Inject
    lateinit var messageDialog: MessageDialog

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            messageDialog.showCustomMessageWithAction(
                context = requireContext(),
                title = tag,
                message = getString(R.string.permiso_camara),
                type = MessageDialog.MessageType.WARNING,
                textActionClose = getString(R.string.volver),
                action = { onDestroyView() }
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPalmistryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (permissionsService.checkCameraPermission()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(binding.pvFindHand.surfaceProvider) }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                Log.d(tag, "algo anda mal $e")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
