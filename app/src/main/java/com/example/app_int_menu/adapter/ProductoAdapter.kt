package com.example.app_int_menu.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_int_menu.Producto
import com.example.app_int_menu.R

class ProductoAdapter(
    var context: Context,
    var listaproductos: ArrayList<Producto>
): RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return ProductoViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaproductos.get(position)

        holder.tvid.text = producto.id.toString()
        holder.tvNombre.text = producto.name
        holder.tvprecio.text = producto.precio
        holder.tvdescripcion.text = producto.descripcion
        holder.tvdisponible.text = producto.disponibilidad


        holder.btnEditar.setOnClickListener {
            onClick?.editarUsuario(producto)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarUsuario(producto.id)
        }
    }

    override fun getItemCount(): Int {
        return listaproductos.size
    }

    inner class ProductoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvid = itemView.findViewById(R.id.tvid) as TextView
        val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        val tvprecio = itemView.findViewById(R.id.tvprecio) as TextView
        val tvdescripcion = itemView.findViewById(R.id.tvdescripcion) as TextView
        val tvdisponible = itemView.findViewById(R.id.tvdisponible) as TextView
        val btnEditar = itemView.findViewById(R.id.btnEditar) as Button
        val btnBorrar = itemView.findViewById(R.id.btnBorrar) as Button
    }

    interface OnItemClicked {
        fun editarUsuario(producto: Producto)
        fun borrarUsuario(id: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}