package com.example.horoscoapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.horoscoapp.R
import com.example.horoscoapp.ui.detail.HoroscopeDetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    // Esta regla de Hilt se encarga de inicializar Hilt en las pruebas
    @get : Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    // Esta regla de ActivityScenarioRule se encarga de lanzar la actividad
    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject() // Inicializa Hilt
        Intents.init() // Inicializa Intents
    }

    @After
    fun tearDown() {
        Intents.release() // Libera Intents
    }

    // Verifica que el bottomNavigationView sea visible
    @Test
    fun when_activityIsLaunched_bottomNavigationViewIsDisplayed() {
        // Verifica que el bottomNavigationView sea visible
        onView(withId(R.id.luckFragment)).perform(click())
    }

    @Test
    fun when_horoscope_is_selected_then_open_horoscope_detail() {
        // Verifica que el bottomNavigationView sea visible
        onView(withId(R.id.horoscopeFragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        intended(hasComponent(HoroscopeDetailActivity::class.java.name))
    }
}