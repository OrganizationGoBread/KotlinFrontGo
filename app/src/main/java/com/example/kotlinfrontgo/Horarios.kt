package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme

class Horarios : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idCliente = intent.extras!!.getInt("idCliente")
        val idComercio = intent.extras!!.getInt("idComercio")
        val bairro = intent.extras!!.getString("bairro").toString()

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaHorarios(idCliente, idComercio, bairro)
                }
            }
        }
    }
}


@Composable
fun TelaHorarios(idCliente: Int, idComercio: Int, bairro: String){
    var selectedOptionHorario by remember { mutableStateOf("07H30") }
    val optionsHorarios = listOf("07H30", "08H00", "08H30", "09H00", "09H30")
    var selectedOptionDia by remember { mutableStateOf("Segunda-feira") }
    val optionsDias = listOf("Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira")
    val context = LocalContext.current

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
                val telaPadaria = Intent(context, Padaria::class.java)
                telaPadaria.putExtra("bairro", bairro)
                telaPadaria.putExtra("idComercio", idComercio)
                context.startActivity(telaPadaria)
            }
            Spacer(modifier = Modifier.width(70.dp))
            Text("Agendar Horário", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(40.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            optionsDias.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOptionDia),
                        onClick = { selectedOptionDia = text },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(text = text,// Estilo de texto
                        modifier = Modifier.padding(start = 8.dp) // Padding à esquerda do texto
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            optionsHorarios.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOptionHorario),
                        onClick = { selectedOptionHorario = text },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(text = text,// Estilo de texto
                        modifier = Modifier.padding(start = 8.dp) // Padding à esquerda do texto
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier.height(30.dp).width(200.dp),onClick = {
            val telaProdutos = Intent(context, Produtos::class.java)
            telaProdutos.putExtra("idCliente", idCliente)
            telaProdutos.putExtra("idComercio", idComercio)
            telaProdutos.putExtra("dia", selectedOptionDia)
            telaProdutos.putExtra("hora", selectedOptionHorario)
            context.startActivity(telaProdutos)
        }) {
            Text("Próximo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    KotlinFrontGoTheme {
        TelaHorarios(1,1, "não sei")
    }
}