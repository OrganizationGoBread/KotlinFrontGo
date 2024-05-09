package com.example.kotlinfrontgo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun PedidoComponente(){
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
            Box(modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth(0.03f)
                .fillMaxHeight(.8f))
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text("Pedido: 1")
                Text("Segunda-feira")
                Text("07H30")
                Text("Codigo: 5687")
            }
            Spacer(modifier = Modifier.width(160.dp))
            Text(">", fontSize = 30.sp)
        }
    }
}