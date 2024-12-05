// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Apply Hilts plugin
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    // Library for safe navigation
    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply false
}
