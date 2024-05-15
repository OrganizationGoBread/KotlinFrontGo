package com.example.kotlinfrontgo.api

import com.example.kotlinfrontgo.dto.request.ClienteRequest
import com.example.kotlinfrontgo.dto.request.ClienteUpdateRequest
import com.example.kotlinfrontgo.dto.request.LoginRequest
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.LoginClienteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Call

interface ApiCliente {

    @POST("clientes/cadastrar")
    fun cadastrarCliente(@Body novoCliente:ClienteRequest): Call<ClienteResponse>

    @GET("clientes/{id}")
    fun getClientePorId(@Path("id") id:Int): Call<ClienteResponse>

    @POST("clientes/login")
    fun loginCliente(@Body loginRequest:LoginRequest): Call<LoginClienteResponse>

    @PUT("clientes/{id}")
    fun put(@Path("id") id:Int, @Body clienteEditado:ClienteUpdateRequest): Call<ClienteResponse>

    @DELETE("clientes/{id}")
    fun delete(@Path("id") id:Int): Call<Void>
}