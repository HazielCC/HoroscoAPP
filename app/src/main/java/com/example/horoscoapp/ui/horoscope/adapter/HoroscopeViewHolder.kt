package com.example.horoscoapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoapp.databinding.ItemHoroscopeBinding
import com.example.horoscoapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        //  val context = binding.tvHoroscopes.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscopes.setText(horoscopeInfo.name)
        // binding.tvHoroscopes.text = context.getString((horoscopeInfo.name))

        binding.root.setOnClickListener {
            startAnimation(
                binding.ivHoroscope,
                waitAnimation = { onItemSelected(horoscopeInfo) })
            //onItemSelected(horoscopeInfo)
        }
    }

    private fun startAnimation(view: View, waitAnimation: () -> Unit = {}) {
        view.animate().apply {
            duration = 250 // 0.5 seconds
            interpolator = LinearInterpolator() // Accelerate
            rotationBy(360f)

            //rotationXBy(360f) // Rotate 360 degrees on the X axis
            //rotationYBy(360f) // Rotate 360 degrees on the Y axis
            //scaleXBy(0.5f) // Scale by 50%
            //scaleYBy(0.5f) // Scale by 50%
        }.withEndAction { waitAnimation() }.start()
    }
}
