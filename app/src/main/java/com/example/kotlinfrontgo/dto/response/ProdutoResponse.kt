package com.example.kotlinfrontgo.dto.response

data class ProdutoResponse(
    val id: Int,
    val nome: String,
    val valorPorcao: Int,
    val tipoPorcao: String
)
