package com.example.kotlinfrontgo.dto.response

import java.io.Serializable

data class ComercioSemPedidoResponse(
    val id: Int,
    val razaoSocial: String,
    val responsavel: String,
    val email: String,
    val tipo: String,
    val telefone: String,
    val endereco: EnderecoResponse
)