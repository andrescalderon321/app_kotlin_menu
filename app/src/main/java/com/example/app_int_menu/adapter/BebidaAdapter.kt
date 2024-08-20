package com.example.app_int_menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.Bebida
import com.example.app_int_menu.R

class BebidaAdapter (

    var context: Context,
    var listabebidas: ArrayList<Bebida>
): RecyclerView.Adapter<BebidaAdapter.BebidaViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BebidaAdapter.BebidaViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_bebida, parent, false)
        return BebidaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: BebidaViewHolder, position: Int) {
        val bebida = listabebidas.get(position)

        holder.tvidbebida.text = bebida.id.toString()
        holder.tvNombrebebida.text = bebida.name
        holder.tvdescripcionbebida.text = bebida.descripcion
        holder.tvpreciobebida.text = bebida.precio




        holder.btnEditarbebida.setOnClickListener {
            onClick?.editarBebida(bebida)
        }

        holder.btnBorrarbebida.setOnClickListener {
            onClick?.borrarBebida(bebida.id)
        }
    }

    override fun getItemCount(): Int {
        return listabebidas.size
    }

    inner class BebidaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvidbebida = itemView.findViewById(R.id.tvidbebida) as TextView
        val tvNombrebebida = itemView.findViewById(R.id.tvNombrebebida) as TextView
        val tvdescripcionbebida = itemView.findViewById(R.id.tvdescripcionbebida) as TextView
        val tvpreciobebida = itemView.findViewById(R.id.tvpreciobebida) as TextView

        val btnEditarbebida = itemView.findViewById(R.id.btnEditarbebida) as Button
        val btnBorrarbebida = itemView.findViewById(R.id.btnBorrarbebida) as Button
    }

    interface OnItemClicked {
        fun editarBebida(bebida: Bebida)
        fun borrarBebida(id: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}
