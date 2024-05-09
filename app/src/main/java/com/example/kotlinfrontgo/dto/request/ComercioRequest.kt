package com.example.kotlinfrontgo.dto.request

data class ComercioRequest(
    val razaoSocial: String,
    val responsavel: String,
    val cnpj: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val tipo: String = "comercio",
    val endereco: EnderecoRequest
)
