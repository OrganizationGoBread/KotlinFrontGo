package com.example.kotlinfrontgo.dto.request

data class ItemPedidoRequest(
    val id: Int?,
    val quantidade: Int,
    val pedido: PedidoRequest?,
    val produto: ProdutoRequest?
)
