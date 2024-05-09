package com.example.kotlinfrontgo.dto.request

data class ClienteRequest(
    val nome: String? = null,
    val cpf: String? = null,
    val telefone: String? = null,
    val email: String? = null,
    val senha: String? = null,
    val tipo: String? = "cliente",
    val endereco: EnderecoRequest? = null
)
