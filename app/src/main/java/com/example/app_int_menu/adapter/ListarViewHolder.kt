package com.example.app_int_menu.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_int_menu.Listar
import com.example.app_int_menu.ListarecyclerviewProvider
import com.example.app_int_menu.R
import com.example.app_int_menu.databinding.CafeBinding
import com.example.app_int_menu.databinding.ItemListarBinding

class ListarViewHolder (view: View):RecyclerView.ViewHolder(view) {

    val binding = ItemListarBinding.bind(view)

    fun render(listarmodel:Listar){
        binding.textView.text = listarmodel.nombre
        binding.textView2.text = listarmodel.contenido
        Glide.with(binding.imageView3.context).load(listarmodel.foto).into(binding.imageView3)

        }
    }
