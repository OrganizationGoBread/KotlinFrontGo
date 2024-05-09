package com.example.kotlinfrontgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme

class CadastroEnderecoCliente : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CadastroEnderecoClienteTela("Android")
                }
            }
        }
    }
}

@Composable
fun CadastroEnderecoClienteTela(name: String, modifier: Modifier = Modifier) {
    val entradaCep = remember { mutableStateOf("") }
    val entradaNumero = remember { mutableStateOf("") }
    val entradaComplemento = remember { mutableStateOf("") }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 30.dp)) {
        Row {
            Text("Cadastre seu Endereço", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

    }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 80.dp)){
        OutlinedTextField(
            value = entradaCep.value,
            onValueChange = { entradaCep.value = it },
            label = { Text("CEP") },
            placeholder = { Text("") },
            modifier = Modifier
                .padding(PaddingValues(top = 10.dp)),
        )
        OutlinedTextField(
            value = entradaNumero.value,
            onValueChange = { entradaNumero.value = it },
            label = { Text("Número") },
            placeholder = { Text("") },
            modifier = Modifier
                .padding(PaddingValues(top = 10.dp)),
        )
        OutlinedTextField(
            value = entradaComplemento.value,
            onValueChange = { entradaComplemento.value = it },
            label = { Text("Complemento (opcional)") },
            placeholder = { Text("") },
            modifier = Modifier
                .padding(PaddingValues(top = 10.dp)),
        )
        Row (){
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(PaddingValues(top = 10.dp))
                    .fillMaxWidth(0.72f),
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)),
                shape = RoundedCornerShape(10)
            ) { Text("Próximo") }
        }
    }
}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun CadastroEnderecoClientePreview() {
    KotlinFrontGoTheme {
        CadastroEnderecoClienteTela("Android")
    }
}