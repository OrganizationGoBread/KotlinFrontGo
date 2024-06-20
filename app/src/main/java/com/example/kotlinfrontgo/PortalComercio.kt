package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.component.CardPadaria
import com.example.kotlinfrontgo.dto.request.LoginRequest
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.ComercioResponse
import com.example.kotlinfrontgo.dto.response.ComercioSemPedidoResponse
import com.example.kotlinfrontgo.dto.response.ItemPedidoClienteResponse
import com.example.kotlinfrontgo.dto.response.LoginComercioResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class PortalComercio : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idComercio = intent.extras!!.getInt("idComercio")
        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortalComercio(idComercio)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PortalComercio(idComercio: Int) {
    val context = LocalContext.current
    val comercio = remember { mutableStateOf<ComercioResponse?>(null) }
    val pedidosIds = remember { mutableStateListOf<Int>() }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBackPortalComercio {
                val tela = Intent(context, LoginComercio::class.java)
                context.startActivity(tela)
            }
            Spacer(modifier = Modifier.width(125.dp))
            Text("Pedidos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(1f)
            .padding(bottom = 20.dp)
            .padding(top = 20.dp)) {
            Button(
                onClick = {
                    val api = RetrofitService.getApiPedido()
                    api.alterarPedidoParaPendente(pedidosIds).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                Toast.makeText(context, "Pedido alterado para pendente", Toast.LENGTH_SHORT).show();
                                val telaPortalComercio = Intent(context, PortalComercio::class.java)
                                telaPortalComercio.putExtra("idComercio", idComercio)
                                context.startActivity(telaPortalComercio)
                            } else {
                                Toast.makeText(context, "Erro ao buscar os pedidos", Toast.LENGTH_SHORT).show();
                                val telaPortalComercio = Intent(context, PortalComercio::class.java)
                                telaPortalComercio.putExtra("idComercio", idComercio)
                                context.startActivity(telaPortalComercio)
                            }
                        }
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show();
                        }
                    })
                },
                modifier = Modifier
                    .padding(PaddingValues(top = 5.dp))
                    .fillMaxWidth(0.72f),
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)),
                shape = RoundedCornerShape(10)
            ) { Text("Começar Entregas") }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            LaunchedEffect(Unit) { // Executar a chamada de API quando o composable é carregado
                val api = RetrofitService.getApiComercio()
                api.getComercioPorId(idComercio).enqueue(object :   Callback<ComercioResponse> {
                    override fun onResponse(call: Call<ComercioResponse>, response: Response<ComercioResponse>) {
                        if (response.isSuccessful) {
                            comercio.value = response.body()
                        } else {
                            Toast.makeText(context, "Erro ao buscar os pedidos", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ComercioResponse>, t: Throwable) {
                        Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            comercio.value?.let { comercio ->
                comercio.pedidos.forEach { pedido ->
                    if (pedido.diaEntrega.equals(LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale("pt", "BR")), ignoreCase = true)) {
                        PedidoComercioComponente(
                            comercio.id,
                            pedido.id,
                            pedido.diaEntrega,
                            pedido.horarioEntrega,
                            pedido.itensPedido,
                            pedido.cliente,
                            pedido.status
                        )
                        pedidosIds.add(pedido.id)
                    }
                }
            }
        }
    }
}


@Composable
fun IconArrowBackPortalComercio(onClick: () -> Unit){
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Voltar",
        modifier = Modifier
            .size(25.dp)
            .clickable { onClick() }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi=true)
@Composable
fun PortalComercioPreview() {
    KotlinFrontGoTheme {
        val list = listOf<ItemPedidoClienteResponse>()
        PortalComercio(1)
    }
}
