package com.example.kotlinfrontgo

import android.content.Intent
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.ItemPedidoClienteResponse

@Composable
fun PedidoComercioComponente(idComercio: Int, idPedido: Int, diaEntrega: String, horarioEntrega: String, itensPedido: List<ItemPedidoClienteResponse>, cliente: ClienteResponse, status: String){
    val context = LocalContext.current
    Box(modifier = Modifier
        .background(Color.White)
        .width(340.dp)
        .height(130.dp)
        .border(0.1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))){
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,) {
            if(status == "confirmado"){
                Box(modifier = Modifier
                    .background(Color.Green)
                    .fillMaxWidth(0.03f)
                    .fillMaxHeight(.8f))
            } else{
                Box(modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth(0.03f)
                    .fillMaxHeight(.8f))
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text("${cliente.endereco.rua}, ${cliente.endereco.numero} - ${cliente.endereco.complemento}")
                Text("Cliente: ${cliente.nome}")
                Text(diaEntrega)
                Text(horarioEntrega)
                Button(onClick = {
                    val telaPedidoDetalhado = Intent(context, PedidoDetalhado::class.java)
                    telaPedidoDetalhado.putExtra("idComercio", idComercio)
                    telaPedidoDetalhado.putExtra("idPedido", idPedido)
                    context.startActivity(telaPedidoDetalhado)
                },
                    modifier = Modifier
                        .padding(PaddingValues(top = 2.dp))
                        .fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)),
                    shape = RoundedCornerShape(10)
                ) { Text("Ver detalhes") }
            }
            Spacer(modifier = Modifier.width(160.dp))

        }
    }
}