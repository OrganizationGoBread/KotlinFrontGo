package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.PedidoClienteResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortalCliente : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idCliente = intent.extras!!.getInt("idCliente")

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPedido(idCliente)
                }
            }
        }
    }
}

@Composable
fun TelaPedido(idCliente: Int, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val clienteState = remember { mutableStateOf<ClienteResponse?>(null) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBack {
                val telaLoginCliente = Intent(context, LoginCliente::class.java)
                context.startActivity(telaLoginCliente)
            }
            Spacer(modifier = Modifier.width(90.dp))
            Text("Meus Pedidos", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            IconBuy {
                val tela = Intent(context, Padaria::class.java)
                tela.putExtra("bairro", clienteState.value!!.endereco.bairro)
                tela.putExtra("idCliente", clienteState.value!!.id)
                context.startActivity(tela)
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Lista de pedidos", color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val api = RetrofitService.getApiCliente()
            api.getClientePorId(idCliente).enqueue(object : Callback<ClienteResponse> {
                override fun onResponse(call: Call<ClienteResponse>, response: Response<ClienteResponse>) {
                    if (response.isSuccessful) {
                        clienteState.value = response.body()
                    } else {
                        Toast.makeText(context, "Erro ao buscar o cliente", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<ClienteResponse>, t: Throwable) {
                    // Tratar erros de rede
                    Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

            clienteState.value?.let { cliente ->
                    cliente.pedidos.forEach { pedido ->
                        PedidoComponente(
                            pedido.diaEntrega,
                            pedido.horarioEntrega,
                            pedido.status,
                            pedido.codigoVerificacao
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
            }
        }
    }

}

@Composable
fun IconArrowBack(onClick: () -> Unit){
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Voltar",
        modifier = Modifier
            .size(25.dp)
            .clickable { onClick() }
    )
}

@Composable
fun IconBuy(onClick: () -> Unit){
    Icon(
        imageVector = Icons.Default.ShoppingCart,
        contentDescription = "comprar",
        modifier = Modifier
            .size(25.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun PortalClientePreview() {
    KotlinFrontGoTheme {
        TelaPedido(1)
    }
}


