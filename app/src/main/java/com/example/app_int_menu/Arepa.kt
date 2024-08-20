package com.example.app_int_menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.adapter.Listaradapter
import com.example.app_int_menu.databinding.ArepaBinding

class Arepa : AppCompatActivity() {

    private lateinit var binding: ArepaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArepaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        setContentView(R.layout.arepa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        initRecyclerView()
    }
    fun initRecyclerView(){

        val recyclerViewcafe= findViewById<RecyclerView>(R.id.recycler_arepa)
        recyclerViewcafe.layoutManager = LinearLayoutManager(this)
        recyclerViewcafe.adapter= Listaradapter(ListarecyclerviewProvider.lista)
    }
}