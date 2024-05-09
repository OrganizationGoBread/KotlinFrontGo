package com.example.kotlinfrontgo.dto.response

data class PedidoClienteResponse(
    val id: Int,
    val diaEntrega: String,
    val horarioEntrega: String,
    val status: String,
    val codigoVerificacao: Int,
    val itensPedido: List<ItemPedidoClienteResponse>,
    val comercio: ComercioClienteResponse
)
