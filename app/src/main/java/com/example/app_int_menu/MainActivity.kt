package com.example.app_int_menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_int_menu.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.app_int_menu.model.LoginRequest
import kotlin.math.log


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                iniciarSesion(email, password)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniciarSesion(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val loginRequest = LoginRequest(email, password)
            val response = RetrofitClient.webService.loginUsuario(loginRequest)

            runOnUiThread {
                // Log the response to see what's being returned by the server
                Log.d("LoginResponse", response.toString())

                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    Toast.makeText(this@MainActivity, "Bienvenido ${loginResponse.usuarioId}", Toast.LENGTH_SHORT).show()

                    // Redirigir a MenuActivity después de un inicio de sesión exitoso
                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Log the error message for debugging
                    Log.e("LoginError", "Error en el inicio de sesión: ${response.errorBody()?.string()}")
                    Toast.makeText(this@MainActivity, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}