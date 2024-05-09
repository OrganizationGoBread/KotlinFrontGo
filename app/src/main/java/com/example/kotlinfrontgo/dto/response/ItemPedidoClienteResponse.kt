package com.example.kotlinfrontgo.dto.response

data class ItemPedidoClienteResponse(
    val id: Int,
    val quantidade: Int,
    val produto: ProdutoResponse
)