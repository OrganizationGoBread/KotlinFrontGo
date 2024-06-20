package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.dto.response.ComercioResponse
import com.example.kotlinfrontgo.dto.response.ComercioSemPedidoResponse
import com.example.kotlinfrontgo.dto.response.ItemComercioPedidoResponse
import com.example.kotlinfrontgo.dto.response.ItemPedidoClienteResponse
import com.example.kotlinfrontgo.dto.response.PedidoComercioResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoDetalhadoCliente : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idCliente = intent.extras!!.getInt("idCliente")
        val idPedido = intent.extras!!.getInt("idPedido")

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PedidoDetalhadoCliente(idCliente, idPedido)
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PedidoDetalhadoCliente(idCliente: Int, idPedido: Int){
    val context = LocalContext.current
    val pedidoSelecionado = remember { mutableStateOf<PedidoComercioResponse?>(null) }
    val itensPedido = remember { mutableStateListOf<ItemPedidoClienteResponse>() } // Usar mutableStateListOf para observabilidade


    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()) {
        LaunchedEffect(Unit) {
            val api = RetrofitService.getApiPedido()
            api.getPedidoPorId(idPedido).enqueue(object :   Callback<PedidoComercioResponse> {
                override fun onResponse(call: Call<PedidoComercioResponse>, response: Response<PedidoComercioResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            itensPedido.clear()
                            itensPedido.addAll(it.itensPedido) // Adicionar todos os itens à lista de forma reativa
                        }
                    } else {
                        Toast.makeText(context, "Erro ao buscar o comércio", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PedidoComercioResponse>, t: Throwable) {
                    Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        LazyColumn{
            items(itensPedido.size) {index ->
                val itemPedido = itensPedido[index]
                Text("Produto: ${itemPedido.produto.nome}")
                Text("Quantidade: ${itemPedido.quantidade}")
                Text("________________________________________________")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,Arrangement.SpaceAround
        ){
            Button(
                onClick = {
                    val tela = Intent(context, PortalCliente::class.java)
                    tela.putExtra("idCliente", idCliente)
                    context.startActivity(tela)
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
                , // Para arredondar os cantos
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)) // Definindo a cor do botão
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White // Definindo a cor do texto
                )
            }
            Button(
                onClick = {
                    val api = RetrofitService.getApiPedido()
                    api.deletarPedido(idPedido).enqueue(object :   Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Log.i("api", "chamou api!!!!!!")
                            if (response.isSuccessful) {
                               val tela = Intent(context, PortalCliente::class.java)
                                tela.putExtra("idCliente", idCliente)
                                context.startActivity(tela)
                            } else {
                                Toast.makeText(context, "Erro ao deletar o pedido", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(50.dp)
                , // Para arredondar os cantos
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)) // Definindo a cor do botão
            ) {
                Text(
                    text = "Deletar pedido",
                    color = Color.White // Definindo a cor do texto
                )
            }
        }
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PedidoDetalhadoClientePreview() {
    KotlinFrontGoTheme {
        PedidoDetalhadoCliente(1, 1)
    }
}