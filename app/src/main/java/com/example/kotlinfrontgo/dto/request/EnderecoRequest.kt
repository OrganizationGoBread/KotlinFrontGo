package com.example.kotlinfrontgo.dto.request

data class EnderecoRequest(
    val cep: String,
    val numero: Int,
    val complemento: String?
)
