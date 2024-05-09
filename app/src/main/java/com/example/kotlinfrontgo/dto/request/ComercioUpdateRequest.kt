package com.example.kotlinfrontgo.dto.request

data class ComercioUpdateRequest(
    var razaoSocial: String,
    var responsavel: String,
    var cnpj: String,
    var telefone: String,
    var email: String,
    var tipo: String,
    var endereco: EnderecoRequest
)
