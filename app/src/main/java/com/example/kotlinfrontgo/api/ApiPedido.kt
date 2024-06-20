package com.example.kotlinfrontgo.api

import com.example.kotlinfrontgo.dto.request.PedidoRequest
import com.example.kotlinfrontgo.dto.response.PedidoComercioResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPedido {

    @PUT("pedidos/att-status-confirmado/{idPedido}")
    @FormUrlEncoded
    fun verifyCode(@Field("codigoVerificacao") codigoVerificacao: Int, @Path("idPedido") id: Int): Call<Void>

    @PUT("pedidos/att-status-pendente/lista")
    fun alterarPedidoParaPendente(@Body listIdsPedidos: List<Int>): Call<Void>

    @POST("pedidos")
    fun cadastrarPedido(@Body pedidoRequest: PedidoRequest): Call<List<String>>

    @POST("pedidos/salvar-pedidos")
    fun salvarPedidos(): Call<Void>

    @GET("pedidos/{id}")
    fun getPedidoPorId(@Path("id") id:Int): Call<PedidoComercioResponse>

    @DELETE("pedidos/{id}")
    fun deletarPedido(@Path("id") id: Int): Call<Void>

}