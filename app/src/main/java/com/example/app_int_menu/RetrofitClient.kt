package com.example.app_int_menu

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.app_int_menu.model.LoginRequest  // Asegúrate de que la ruta del import sea correcta

object RetrofitClient {
    private const val BASE_URL = "http://192.168.100.251:3000"  // Asegúrate de que esta URL es correcta

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}