package com.example.app_int_menu

import com.google.gson.annotations.SerializedName



class BebidasResponse(
    @SerializedName("listaBebidas") var listaBebidas: ArrayList<Bebida>
)