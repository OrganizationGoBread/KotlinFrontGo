package com.example.kotlinfrontgo.dto.request

data class ItemPedidoRequest(
    val quantidade: Int,
    val produto: ProdutoRequest?
)
