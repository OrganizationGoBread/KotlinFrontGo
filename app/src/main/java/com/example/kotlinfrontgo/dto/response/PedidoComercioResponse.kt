package com.example.kotlinfrontgo.dto.response

data class PedidoComercioResponse(
    val id: Int,
    val diaEntrega: String,
    val horarioEntrega: String,
    val status: String,
    val itensPedido: List<ItemPedidoClienteResponse>,
    val cliente: ClienteResponse
)