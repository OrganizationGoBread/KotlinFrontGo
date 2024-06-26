package com.example.kotlinfrontgo

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun PedidoComponente(
    diaEntrega: String,
    horarioEntrega: String,
    status: String,
    codigoVerificacao: Int,
    idPedido: Int,
    idCliente: Int
){
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
                    .background(Color.Red)
                    .fillMaxWidth(0.03f)
                    .fillMaxHeight(.8f))
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text("Pedido: ${idPedido}")
                Text(diaEntrega)
                Text(horarioEntrega)
                Text("Codigo: ${codigoVerificacao}")
                Button( colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C))
                    , onClick = {
                        val tela = Intent(context, PedidoDetalhadoCliente::class.java)
                        tela.putExtra("idPedido", idPedido)
                        tela.putExtra("idCliente", idCliente)
                        context.startActivity(tela)
                    },
                    modifier = Modifier
                        .padding(PaddingValues(top = 2.dp))
                        .fillMaxWidth(0.6f)) {
                    Text("Ver detalhes",color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(160.dp))
        }
    }
}