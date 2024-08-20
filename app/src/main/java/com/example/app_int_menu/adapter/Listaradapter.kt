package com.example.app_int_menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.Listar
import com.example.app_int_menu.R

class Listaradapter(private val lista:List<Listar>) : RecyclerView.Adapter<ListarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListarViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ListarViewHolder(layoutInflater.inflate(R.layout.item_listar, parent, false))
    }

    override fun getItemCount(): Int = lista.size





    override fun onBindViewHolder(holder: ListarViewHolder, position: Int) {
        val item = lista[position]
        holder.render(item)



    }
}