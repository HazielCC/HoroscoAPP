package com.example.horoscoapp.utilities.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.horoscoapp.utilities.dialogs.MessageDialog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object API {
    // Crear una instancia de com.example.horoscoapp.utilities.dialogs.MessageDialog
    private val messageDialog = MessageDialog()


    fun Context.isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    // Retrofit
    fun Context.getRetrofit(): Retrofit {

        if (!isNetworkAvailable()) {
            // Handle the case when there is no network available
            // For example, you can throw an exception or return null
            messageDialog.showInternetErrorMessage(this)
        }

        // Logging interceptor
        val logging = HttpLoggingInterceptor() // logging interceptor
        logging.setLevel(HttpLoggingInterceptor.Level.BODY) // logging level

        val client = OkHttpClient.Builder()
            .addInterceptor(logging) // petition logging
            .connectTimeout(30, TimeUnit.SECONDS) // connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // read timeout
            .writeTimeout(30, TimeUnit.SECONDS) // write timeout
            .build()

        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}