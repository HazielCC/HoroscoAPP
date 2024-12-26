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
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    /*androidTestImplementation("androidx.test.espresso:espresso-contrib:3.6.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    androidTestImplementation("androidx.fragment:fragment-testing:1.8.5")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.54") */
}
