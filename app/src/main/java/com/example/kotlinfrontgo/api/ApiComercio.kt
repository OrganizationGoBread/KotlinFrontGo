package com.example.kotlinfrontgo.api

import com.example.kotlinfrontgo.dto.request.LoginRequest
import com.example.kotlinfrontgo.dto.response.ComercioResponse
import com.example.kotlinfrontgo.dto.response.ComercioSemPedidoResponse
import com.example.kotlinfrontgo.dto.response.LoginClienteResponse
import com.example.kotlinfrontgo.dto.response.LoginComercioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiComercio {


    @POST("comercios/login")
    fun loginComercio(@Body loginRequest: LoginRequest): Call<LoginComercioResponse>

    @GET("comercios/bairro")
    fun buscarPeloBairro(@Query("bairro") bairro: String): Call<List<ComercioSemPedidoResponse>>

    @GET("comercios/{id}")
    fun getComercioPorId(@Path("id") id:Int): Call<ComercioResponse>



}