package com.example.horoscoapp.utilities.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.DialogMessageBinding
import com.example.horoscoapp.utilities.services.API.isNetworkAvailable

class MessageDialog {
    private lateinit var binding: DialogMessageBinding
    private var dialog: AlertDialog? = null

    companion object {
        const val TYPE_ERROR = "error"
        const val TYPE_SUCCESS = "success"
        const val TYPE_INFO = "info"
        const val TYPE_WARNING = "warning"
        const val TYPE_ERROR_INTERNET = "error_internet"
    }

    private fun show(context: Context, message: String, type: String) {
        if (dialog?.isShowing == true) return

        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        binding = DialogMessageBinding.inflate(inflater)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()

        binding.tvText.text = message
        configureDialog(context, type)
        binding.btnClose.setOnClickListener { dialog?.dismiss() }
    }

    private fun configureDialog(context: Context, type: String) {
        when (type) {
            TYPE_ERROR -> {
                dialog?.setCancelable(false)
                binding.tvTitle.text = context.getString(R.string.error)
                binding.tvTitle.setTextColor(context.getColor(R.color.red))
                binding.ivIcon.setImageResource(R.drawable.ic_error)
            }

            TYPE_SUCCESS -> {
                binding.tvTitle.text = context.getString(R.string.exito)
                binding.tvTitle.setTextColor(context.getColor(R.color.green))
                binding.ivIcon.setImageResource(R.drawable.ic_success)
            }

            TYPE_INFO -> {
                binding.tvTitle.text = context.getString(R.string.informacion)
                binding.tvTitle.setTextColor(context.getColor(R.color.blue))
                binding.ivIcon.setImageResource(R.drawable.ic_information)
            }

            TYPE_WARNING -> {
                binding.tvTitle.text = context.getString(R.string.advertencia)
                binding.tvTitle.setTextColor(context.getColor(R.color.yellow))
                binding.ivIcon.setImageResource(R.drawable.ic_warning)
            }

            TYPE_ERROR_INTERNET -> {
                dialog?.setCancelable(false)
                binding.tvTitle.text = context.getString(R.string.error_servicio)
                binding.tvTitle.setTextColor(context.getColor(R.color.red))
                binding.ivIcon.setImageResource(R.drawable.ic_error)
                binding.btnClose.visibility = View.GONE
                binding.btnAction.text = context.getString(R.string.reintentar)
                binding.btnAction.setOnClickListener {
                    if (context.isNetworkAvailable()) {
                        dialog?.dismiss()
                    } else {
                        dialog?.dismiss()
                        show(
                            context,
                            context.getString(R.string.error_internet),
                            TYPE_ERROR_INTERNET
                        )
                    }
                }
            }
        }
    }

    fun showErrorMessage(context: Context, message: String) {
        show(context, message, TYPE_ERROR)
    }

    fun showSuccessMessage(context: Context, message: String) {
        show(context, message, TYPE_SUCCESS)
    }

    fun showInfoMessage(context: Context, message: String) {
        show(context, message, TYPE_INFO)
    }

    fun showWarningMessage(context: Context, message: String) {
        show(context, message, TYPE_WARNING)
    }

    fun showCustomMessageWithAction(
        context: Context,
        title: String,
        message: String,
        type: Int = 1,
        twoButtons: Boolean = false,
        textBtnClose: String? = null,
        action: () -> Unit
    ) {
        val typeString = when (type) {
            1 -> TYPE_ERROR
            2 -> TYPE_SUCCESS
            3 -> TYPE_WARNING
            4 -> TYPE_INFO
            else -> TYPE_INFO
        }
        show(context, message, typeString)
        binding.tvTitle.text = title

        if (twoButtons) {
            binding.btnClose.visibility = View.VISIBLE
            binding.btnClose.text = textBtnClose ?: context.getString(R.string.volver)
        } else {
            binding.btnClose.visibility = View.GONE
        }

        binding.btnAction.setOnClickListener {
            action()
            dialog?.dismiss()
        }
    }

    fun showInternetErrorMessage(context: Context) {
        show(context, context.getString(R.string.error_internet), TYPE_ERROR_INTERNET)
    }
}