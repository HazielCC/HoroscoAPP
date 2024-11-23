package com.example.horoscoapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscoapp.databinding.ItemHoroscopeBinding
import com.example.horoscoapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo) {
        val context = binding.tvHoroscopes.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscopes.setText(horoscopeInfo.name)
        // binding.tvHoroscopes.text = context.getString((horoscopeInfo.name))
    }
}
