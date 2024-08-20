package com.example.app_int_menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.Postre

import com.example.app_int_menu.R

class PostreAdapter (

    var context: Context,
    var listapostres: ArrayList<Postre>
): RecyclerView.Adapter<PostreAdapter.PostreViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostreAdapter.PostreViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_postres, parent, false)
        return PostreViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PostreViewHolder, position: Int) {
        val postre = listapostres.get(position)

        holder.tvidpostre.text = postre.id.toString()
        holder.tvNombrepostre.text =  postre.name
        holder.tvpreciopostre.text = postre.precio
        holder.tvdescripcionpostre.text = postre.descripcion



        holder.btnEditarpostre.setOnClickListener {
            onClick?.editarPostre(postre)
        }

        holder.btnBorrarpostre.setOnClickListener {
            onClick?.borrarPostre(postre.id)
        }
    }

    override fun getItemCount(): Int {
        return listapostres.size
    }

    inner class PostreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvidpostre = itemView.findViewById(R.id.tvidpostre) as TextView
        val tvNombrepostre = itemView.findViewById(R.id.tvNombrepostre) as TextView
        val tvpreciopostre = itemView.findViewById(R.id.tvpreciopostre) as TextView
        val tvdescripcionpostre = itemView.findViewById(R.id.tvdescripcionpostre) as TextView
        val btnEditarpostre = itemView.findViewById(R.id.btnEditarpostre) as Button
        val btnBorrarpostre = itemView.findViewById(R.id.btnBorrarpostre) as Button
    }

    interface OnItemClicked {
        fun editarPostre(postre: Postre)
        fun borrarPostre(id: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}
