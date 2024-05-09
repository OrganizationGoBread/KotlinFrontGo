package com.example.kotlinfrontgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme

class PortalCliente2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPedido("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPedido(name: String, modifier: Modifier = Modifier){
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
            Text("<", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(90.dp))
            Text("Meus Pedidos", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Lista de pedidos", color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            PedidoComponente()
            Spacer(modifier = Modifier.height(15.dp))
            PedidoComponente()
            Spacer(modifier = Modifier.height(15.dp))
            PedidoComponente()
        }
    }

}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun PortalClientePreview() {
    KotlinFrontGoTheme {
        TelaPedido("Android")
    }
}