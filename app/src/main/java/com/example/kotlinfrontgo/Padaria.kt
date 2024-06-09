package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.component.CardPadaria
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.ComercioSemPedidoResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.*
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme



class Padaria : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bairro = intent.extras!!.getString("bairro").toString()
        val idCliente = intent.extras!!.getInt("idCliente")
        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Padaria(bairro, idCliente)
                }
            }
        }
    }
}

@Composable
fun Padaria(bairro: String, idCliente: Int) {
    val context = LocalContext.current
    val comercios = remember { mutableStateListOf<ComercioSemPedidoResponse>() } // Usar mutableStateListOf para observabilidade

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBackPadaria {
                val tela = Intent(context, PortalCliente::class.java)
                context.startActivity(tela)
            }
            Spacer(modifier = Modifier.width(125.dp))
            Text("Padarias", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            LaunchedEffect(Unit) { // Executar a chamada de API quando o composable é carregado
                val api = RetrofitService.getApiComercio()
                api.buscarPeloBairro(bairro).enqueue(object :   Callback<List<ComercioSemPedidoResponse>> {
                    override fun onResponse(call: Call<List<ComercioSemPedidoResponse>>, response: Response<List<ComercioSemPedidoResponse>>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                comercios.clear()
                                comercios.addAll(it) // Adicionar todos os itens à lista de forma reativa
                            }
                        } else {
                            Toast.makeText(context, "Erro ao buscar as padarias", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<ComercioSemPedidoResponse>>, t: Throwable) {
                        Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            LazyColumn {
                items(comercios.size) { index ->
                    val comercio = comercios[index]
                    CardPadaria(
                        razaoSocial = comercio.razaoSocial, // Supondo que esses campos existam no seu DTO
                        rua = comercio.endereco.rua,
                        numero = comercio.endereco.numero,
                        bairro = comercio.endereco.bairro,
                        estado = comercio.endereco.estado,
                        telefone = comercio.telefone,
                        idComercio = comercio.id,
                        idCliente = idCliente,
                        context = context
                    )
                }
            }
        }
    }
}


@Composable
fun IconArrowBackPadaria(onClick: () -> Unit){
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Voltar",
        modifier = Modifier
            .size(25.dp)
            .clickable { onClick() }
    )
}
