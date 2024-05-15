package com.example.kotlinfrontgo.dto.request

import java.io.Serializable

data class ClienteRequest(
    val nome: String? = null,
    val cpf: String? = null,
    val telefone: String? = null,
    val email: String? = null,
    var senha: String? = null,
    val tipo: String? = "cliente",
    val endereco: EnderecoRequest? = null
) : Serializable
