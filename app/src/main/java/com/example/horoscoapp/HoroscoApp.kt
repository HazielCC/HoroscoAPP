package com.example.horoscoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Anotación que indica que esta aplicación usará Hilt para la inyección de dependencias
class HoroscoApp : Application() {
    // Clase principal de la aplicación que extiende de Application
    // Aquí puedes inicializar configuraciones globales para tu aplicación
}
