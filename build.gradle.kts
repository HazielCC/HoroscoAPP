// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Apply Hilts plugin
    alias(libs.plugins.hilt.android) apply false
    // Library for safe navigation
    alias(libs.plugins.safeargs.android) apply false
}
