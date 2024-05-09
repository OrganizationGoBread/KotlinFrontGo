package com.example.kotlinfrontgo.dto.response

data class LoginClienteResponse(
    val token: String,
    val cliente: ClienteResponse
)
