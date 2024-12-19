plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Kotlin annotation processing
    id("com.google.dagger.hilt.android") // DaggerHilt
    id("androidx.navigation.safeargs.kotlin") // Navigation Safe Args
}

android {
    namespace = "com.example.horoscoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.horoscoapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Kotlin options
    kotlinOptions {
        jvmTarget = "11" // Set the JVM target for Kotlin code
    }

    // Enable view binding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // DaggerHilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    implementation("com.orhanobut:logger:2.2.0")

    // navigation
    implementation(libs.androidx.navigation.ui)  // Depende de navigation-fragment
    implementation(libs.androidx.navigation.fragment) // Depende de navigation-ui

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
