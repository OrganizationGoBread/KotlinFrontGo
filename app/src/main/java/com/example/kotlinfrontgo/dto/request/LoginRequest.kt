package com.example.kotlinfrontgo.dto.request

data class LoginRequest(
    var email: String? = null,
    var senha: String? = null
) {
}