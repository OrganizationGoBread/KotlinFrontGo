package com.example.kotlinfrontgo.dto.request

data class ClienteUpdateRequest(
    var nome: String,
    var cpf: String,
    var telefone: String,
    var email: String,
    var tipo: String,
    var endereco: EnderecoRequest
)
