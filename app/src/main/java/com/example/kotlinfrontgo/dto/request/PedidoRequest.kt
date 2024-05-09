package com.example.kotlinfrontgo.dto.request

import com.example.kotlinfrontgo.CodigoVerificacao

data class PedidoRequest(
    val diaEntrega: String,
    val horarioEntrega: String,
    val itensPedido: List<ItemPedidoRequest>,
    val idCliente: Int,
    val idComercio: Int,
    val status: String = "confirmado",
    val codigoVerificacao: Int = 0
)
