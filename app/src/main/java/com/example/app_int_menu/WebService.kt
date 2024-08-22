package com.example.app_int_menu

import com.example.app_int_menu.model.LoginRequest
import retrofit2.Response
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object  AppConstantes {

    const val  BASE_URL = "http://192.168.100.251:3000"

}


interface WebService {
    @GET("/productos")
    suspend fun obtenerProductos(): Response<ProductosResponse>

    @GET("/producto/{id}")
    suspend fun obtenerProducto(
        @Path("id") id: Int

    ): Response<Producto>

    @POST("/producto/agregar")
    suspend fun agregarProducto(
        @Body producto: Producto
    ): Response<String>

    @PUT("/producto/editar/{id}")
    suspend fun actualizarProducto(
        @Path("id") id: Int,
        @Body producto: Producto
    ): Response<String>

    @DELETE("/producto/delete/{id}")
    suspend fun borrarProducto(
        @Path("id") id: Int
    ): Response<String>

    /*
 * crud bebidas
 */

    @GET("/bebidas")
    suspend fun obtenerBebidas(): Response<BebidasResponse>

    @GET("/bebida/{id}")
    suspend fun obtenerBebidas(
        @Path("id") id: Int

    ): Response<Producto>

    @POST("/bebida/agregar")
    suspend fun agregarBebida(
        @Body bebida: Bebida
    ): Response<String>

    @PUT("/bebida/editar/{id}")
    suspend fun actualizarBebida(
        @Path("id") id: Int,
        @Body bebida: Bebida
    ): Response<String>

    @DELETE("/bebida/delete/{id}")
    suspend fun borrarBebida(
        @Path("id") id: Int
    ): Response<String>

    /*
* crud postres
*/

    @GET("/postres")
    suspend fun obtenerPostres(): Response<PostreResponse>

    @GET("/postre/{id}")
    suspend fun obtenerPostres(
        @Path("id") id: Int

    ): Response<Postre>

    @POST("/postres/agregar")
    suspend fun agregarPostres(
        @Body postre: Postre
    ): Response<String>

    @PUT("/postre/editar/{id}")
    suspend fun actualizarPostre(
        @Path("id") id: Int,
        @Body postre: Postre
    ): Response<String>

    @DELETE("/postre/delete/{id}")
    suspend fun borrarPostre(
        @Path("id") id: Int
    ): Response<String>

    /*
* crud login
*/


    // ... (las rutas ya existentes)

    @POST("/usuario/registro")
    suspend fun registrarUsuario(
        @Body usuario: Usuario
    ): Response<String>

    @POST("/usuario/login")
    suspend fun loginUsuario(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}


