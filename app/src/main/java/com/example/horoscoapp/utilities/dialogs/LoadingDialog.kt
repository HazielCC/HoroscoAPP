package com.example.horoscoapp.utilities.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.horoscoapp.databinding.DialogLoadingBinding

class LoadingDialog {
    private var dialog: AlertDialog? = null
    private lateinit var binding: DialogLoadingBinding

    fun show(context: Context, message: String) {
        // Verificar si ya hay un diálogo visible
        if (dialog?.isShowing == true) {
            return
        }

        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        binding = DialogLoadingBinding.inflate(inflater)
        val view = binding.root
        builder.setView(view)
        dialog = builder.create()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent) // Hacer el fondo del diálogo transparente
        dialog?.setCancelable(false) // No permitir que el usuario cierre el diálogo
        dialog?.setCanceledOnTouchOutside(false) // No permitir que el usuario cierre el diálogo al tocar fuera de él

        binding.tvLoading.text = message
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}
