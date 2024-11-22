package com.example.horoscoapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horoscoapp.R
import com.example.horoscoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Anotación que indica que esta actividad usará Hilt para la inyección de dependencias
class MainActivity : AppCompatActivity() {
    // Declaración de variables para el enlace de vista (binding) y el controlador de navegación (navController)
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo de pantalla completa sin bordes
        binding = ActivityMainBinding.inflate(layoutInflater) // Infla el diseño de la actividad
        setContentView(binding.root) // Establece el contenido de la vista con el diseño inflado

        // Ajusta los márgenes de la vista principal para acomodar las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI() // Inicializa la interfaz de usuario
    }

    // Inicializa la interfaz de usuario
    private fun initUI() {
        initNavigation() // Inicializa la navegación
    }

    // Configura la navegación
    private fun initNavigation() {
        // Configura el controlador de navegación
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Configura la vista de navegación inferior con el controlador de navegación
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
