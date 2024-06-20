package com.example.kotlinfrontgo.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.Horarios
import com.example.kotlinfrontgo.Signature
import com.example.kotlinfrontgo.TelaPedido
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme

@Composable
fun CardPadaria(
    modifier: Modifier = Modifier,
    razaoSocial: String,
    rua: String,
    numero: Int,
    bairro: String,
    estado: String,
    telefone: String,
    idComercio: Int,
    idCliente: Int,
    context: Context
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        content = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = razaoSocial,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${rua}, ${numero}" +
                            "\n${bairro}, ${estado}" +
                            "\n${telefone}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Button(modifier = Modifier
                .height(40.dp)
                .width(140.dp)
                .padding(start = 8.dp, bottom = 8.dp)
                ,onClick = {
                val tela = Intent(context, Horarios::class.java)
                tela.putExtra("idCliente", idCliente)
                tela.putExtra("idComercio", idComercio)
                tela.putExtra("bairro", bairro)
                context.startActivity(tela)
            }) {
                Text(text = "SELECIONAR")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun CardPadariaPreview() {
    val dummyContext = LocalContext.current
    KotlinFrontGoTheme {
        CardPadaria(
            razaoSocial = "Padaria do Pão",
            rua = "Rua das Flores",
            numero = 123,
            bairro = "Centro",
            estado = "SP",
            telefone = "(11) 1234-5678",
            idComercio = 1,
            idCliente = 1,
            context = dummyContext
        )
    }
}

