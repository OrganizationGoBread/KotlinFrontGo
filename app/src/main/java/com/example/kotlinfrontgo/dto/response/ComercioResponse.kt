package com.example.kotlinfrontgo.dto.response

data class ComercioResponse(
    val id: Int,
    val razaoSocial: String?,
    val responsavel: String?,
    val email: String?,
    val tipo: String?,
    val cnpj: String?,
    val telefone: String?,
    val endereco: EnderecoResponse?,
    val pedidos: List<PedidoComercioResponse>
)