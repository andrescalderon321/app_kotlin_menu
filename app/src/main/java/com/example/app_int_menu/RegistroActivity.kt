package com.example.app_int_menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_int_menu.databinding.ActivityRegistroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registrarUsuario(nombre, email, password)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registrarUsuario(nombre: String, email: String, password: String) {
        val usuario = Usuario(nombre, email, password)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.webService.registrarUsuario(usuario)
            runOnUiThread {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    finish() // Finaliza la actividad de registro
                } else {
                    Toast.makeText(this@RegistroActivity, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}