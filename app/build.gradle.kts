plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.safeargs.android)
    id("kotlin-kapt") // Kotlin annotation processing
}

android {
    namespace = "com.example.horoscoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.horoscoapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"

        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.horoscoapp.CustomTestRunner"
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            resValue("string", "coconame", "coco")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        getByName("debug") {
            resValue("string", "coconame", "[DEBUG] coco")

            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
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
        buildConfig = true
    }
}

dependencies {
    // DaggerHilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.logger)

    // navigation
    implementation(libs.androidx.navigation.ui)  // Depende de navigation-fragment
    implementation(libs.androidx.navigation.fragment) // Depende de navigation-ui

    //Camera X
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)

    // Librerias Predeterminadas
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //UnitTesting
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.kotlintest.runner.junit5)
    testImplementation(libs.mockk)

    //UITesting
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.fragment.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
}
