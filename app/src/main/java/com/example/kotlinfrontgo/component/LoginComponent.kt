package com.example.kotlinfrontgo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay



@Composable
fun LoginFailComponent(){
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(10000)
        isVisible = false;
    }

    if(isVisible){
        Box(modifier = Modifier
            .background(Color.White)
            .width(300.dp)
            .height(120.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Aqui você pode adicionar seu ícone
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Erro no login",
                    modifier = Modifier.size(48.dp)
                )

                // Adicionando um espaço entre o ícone e o texto
                Spacer(modifier = Modifier.height(8.dp))

                // Aqui está o texto abaixo do ícone
                Text(
                    text = "Erro no login",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun LoginSucessComponent(){
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(10000)
        isVisible = false;
    }

    if(isVisible){
        Box(modifier = Modifier
            .background(Color.White)
            .width(300.dp)
            .height(120.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Aqui você pode adicionar seu ícone
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Sucesso no Login",
                    modifier = Modifier.size(48.dp)
                )

                // Adicionando um espaço entre o ícone e o texto
                Spacer(modifier = Modifier.height(8.dp))

                // Aqui está o texto abaixo do ícone
                Text(
                    text = "Sucesso no Login",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}
