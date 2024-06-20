package com.example.kotlinfrontgo.api

import com.example.kotlinfrontgo.dto.response.ComercioSemPedidoResponse
import com.example.kotlinfrontgo.dto.response.ItemComercioPedidoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiItemComercio {
    @GET("itens-comercio/{id}")
    fun buscarProdutos(@Path("id") id: Int): Call<List<ItemComercioPedidoResponse>>
}