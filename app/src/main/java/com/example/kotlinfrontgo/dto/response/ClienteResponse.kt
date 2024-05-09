package com.example.kotlinfrontgo.dto.response

data class ClienteResponse(
    val id: Int,
    val nome: String,
    val telefone: String,
    val email: String,
    val tipo: String,
    val cpf: String,
    val assinatura: String,
    val endereco: EnderecoResponse,
    val pedidos: List<PedidoClienteResponse>
)