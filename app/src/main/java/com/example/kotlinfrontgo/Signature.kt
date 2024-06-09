package com.example.kotlinfrontgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme

class Signature : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idCliente = intent.extras!!.getInt("idCliente")
        val idComercio = intent.extras!!.getInt("idComercio")


        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Assinatura(idComercio, idCliente)
                }
            }
        }
    }
}

@Composable
fun Assinatura(idComercio: Int, idCliente: Int, modifier: Modifier = Modifier) {
    Surface (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Voltar",
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(3f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Assinatura idCliente = ${idCliente} and idComercio ${idComercio}"
            )

            Icon(painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Voltar",
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(3f),
                tint = Color.Transparent
            )
        }
    }

    val options = listOf("R$ 249,99", "R$ 299,99")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center
    ) {
        options.forEach { option ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = selectedOption == option,
                        onClick = { selectedOption = option }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = null
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun GreetingPreview4() {
    KotlinFrontGoTheme {
        Assinatura(1, 1)
    }
}