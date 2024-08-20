package com.example.app_int_menu

import com.google.gson.annotations.SerializedName

class ProductosResponse(
    @SerializedName("listaProductos") var listaProductos: ArrayList<Producto>
)
