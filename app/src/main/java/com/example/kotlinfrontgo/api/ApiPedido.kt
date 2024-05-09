package com.example.kotlinfrontgo.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiPedido {

    @PUT("pedidos/att-status-confirmado/1")
    @FormUrlEncoded
    fun verifyCode(@Field("codigoVerificacao") codigoVerificacao: Int): Call<Void>
}