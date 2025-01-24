package com.example.horoscoapp.utilities.permissions

import android.content.Context
import android.util.Log
import androidx.core.content.PermissionChecker
import com.example.horoscoapp.R
import javax.inject.Inject

class PermissionsService @Inject constructor(
    private val context: Context
) {

    private val tag = "PermissionsService"

    companion object {
        const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    fun checkCameraPermission(): Boolean {
        val permissionGranted = PermissionChecker.checkSelfPermission(
            context, CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED

        if (permissionGranted) {
            Log.d(tag, context.getString(R.string.permiso_camara_si))
        } else {
            Log.d(tag, context.getString(R.string.permiso_camara_no))
        }

        return permissionGranted
    }

}