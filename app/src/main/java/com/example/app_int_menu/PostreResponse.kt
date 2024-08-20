package com.example.app_int_menu

import com.google.gson.annotations.SerializedName

class PostreResponse(
    @SerializedName("listaPostres") var listaPostres: ArrayList<Postre>
)
