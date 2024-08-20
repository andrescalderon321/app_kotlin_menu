package com.example.app_int_menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.adapter.Listaradapter

class Cafe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cafe)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        initRecyclerView()
    }
    fun initRecyclerView(){

        val recyclerViewcafe= findViewById<RecyclerView>(R.id.recycler_cafe)
        recyclerViewcafe.layoutManager = LinearLayoutManager(this)
        recyclerViewcafe.adapter= Listaradapter(ListarecyclerviewProvider.listacafe)
    }
}