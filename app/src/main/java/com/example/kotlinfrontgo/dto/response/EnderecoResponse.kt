package com.example.kotlinfrontgo.dto.response

data class EnderecoResponse (
    val id: Int,
    val cep: String,
    val rua: String,
    val numero: Int,
    val complemento: String?,
    val bairro: String,
    val cidade: String,
    val estado: String
){
    constructor() : this(0, "", "", 0, null, "", "", "")
}
