package com.example.kotlinfrontgo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

//    val BASE_URL_GO_BREAD = "https://gobread.zapto.org/api/"
    val BASE_URL_GO_BREAD = "http://192.168.0.10:8080/api/"

    fun getApiCliente(): ApiCliente {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_GO_BREAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCliente::class.java)

        return cliente
    }
    fun getApiComercio(): ApiComercio {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_GO_BREAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiComercio::class.java)

        return cliente
    }
    fun getApiItemComercio(): ApiItemComercio {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_GO_BREAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiItemComercio::class.java)

        return cliente
    }
    fun getApiPedido(): ApiPedido {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_GO_BREAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiPedido::class.java)
        return cliente
    }
    fun getApiProduto(): ApiProduto {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_GO_BREAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiProduto::class.java)

        return cliente
    }

}