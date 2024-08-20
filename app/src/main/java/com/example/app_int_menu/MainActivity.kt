package com.example.app_int_menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.app_int_menu.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imagenbutton1.setOnClickListener {
            val intent = Intent(this, Arepa::class.java)
            startActivity(intent)
        }

        binding.imagenbutton2.setOnClickListener {
            val intent = Intent(this, ProductoMain::class.java)
            startActivity(intent)
        }
        binding.imagenbutton3.setOnClickListener {
            val intent = Intent(this, Perro::class.java)
            startActivity(intent)
        }

        binding.imagenbutton6.setOnClickListener {
            val intent = Intent(this, Cafe::class.java)
            startActivity(intent)
        }
        binding.imagenbutton7.setOnClickListener {
            val intent = Intent(this, BebidaMain::class.java)
            startActivity(intent)
        }

        binding.imagenbutton11.setOnClickListener {
            val intent = Intent(this, Helado::class.java)
            startActivity(intent)
        }

        binding.imagenbutton12.setOnClickListener {
            val intent = Intent(this, PostreMain::class.java)
            startActivity(intent)
        }

    }
    }
