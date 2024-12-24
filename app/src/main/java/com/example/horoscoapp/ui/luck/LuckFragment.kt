package com.example.horoscoapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.FragmentLuckBinding
import com.example.horoscoapp.ui.luck.providers.RandomCardProvider
import com.example.horoscoapp.utilities.input.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject


@AndroidEntryPoint
class LuckFragment : Fragment() {
    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    // Utilities
    private val tag = "LuckFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        initListeners()
        preparePrediction()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        // binding.ivRoulette.setOnClickListener { spinRoulette() }

        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeLeft() {
                spinRoulette()
            }

            override fun onSwipeRight() {
                spinRoulette()
            }
        })
    }

    private fun preparePrediction() {
        val luckPrediction = randomCardProvider.getLuck()
        if (luckPrediction != null) {
            binding.tvPrediction.text = getString(luckPrediction.text)
            binding.ivPrediction.setImageResource(luckPrediction.image)
            binding.tvPredictionShare.setOnClickListener { shareResult(luckPrediction.text) }
        }
    }


    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivcard.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                Log.d(tag, "onAnimationRepeat")
            }
        })

        binding.ivcard.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_up)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                binding.ivcard.isVisible = false
                showPredictionView()
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit
        })

        binding.ivcard.startAnimation(growAnimation)
    }

    private fun showPredictionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit
        })

        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    private fun shareResult(text: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
