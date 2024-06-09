package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.component.LoginFailComponent
import com.example.kotlinfrontgo.component.LoginSucessComponent
import com.example.kotlinfrontgo.dto.request.LoginRequest
import com.example.kotlinfrontgo.dto.response.LoginClienteResponse
import com.example.kotlinfrontgo.dto.response.LoginComercioResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginComercio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginComercioTela("Android")
                }
            }
        }
    }
}

@Composable
    fun LoginComercioTela(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val entradaLogin = remember { mutableStateOf("") }
    val entradaSenha = remember { mutableStateOf("") }
    val texto = remember { mutableStateOf("") }
    var sucesso by remember { mutableStateOf(0) }
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 30.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBackLoginComercio {
                val telaUsersActivity = Intent(context, UsersActivity::class.java)
                context.startActivity(telaUsersActivity)
            }
            Spacer(modifier = Modifier.width(125.dp))
            Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

    }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 80.dp)){
        OutlinedTextField(
            value = entradaLogin.value,
            onValueChange = { entradaLogin.value = it },
            label = { Text("Email") },
            placeholder = { Text("") }
        )
        OutlinedTextField(
            value = entradaSenha.value,
            onValueChange = { entradaSenha.value = it },
            label = { Text("Senha") },
            placeholder = { Text("") },
            modifier = Modifier
                .padding(PaddingValues(top = 10.dp)),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Row (){
            Button(
                onClick = {
                    val api = RetrofitService.getApiComercio()
                    val login = LoginRequest(entradaLogin.value, entradaSenha.value)
                    api.loginComercio(login).enqueue(object : Callback<LoginComercioResponse> {
                        override fun onResponse(call: Call<LoginComercioResponse>, response: Response<LoginComercioResponse>) {
                            if (response.isSuccessful) {
                                sucesso = 2;
                            } else {
                                sucesso = 1;
                            }
                        }
                        override fun onFailure(call: Call<LoginComercioResponse>, t: Throwable) {
                            // Tratar erros de rede
                            Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                },
                modifier = Modifier
                    .padding(PaddingValues(top = 10.dp))
                    .fillMaxWidth(0.72f),
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1D2C)),
                shape = RoundedCornerShape(10)
            ) { Text("Entrar") }
        }
        Text(text = texto.value)
        if(sucesso == 1){
            LoginFailComponent();
        } else if(sucesso == 2){
            LoginSucessComponent();
            val telaPortalComercio = Intent(context, CodigoVerificacao::class.java)
            context.startActivity(telaPortalComercio)
        }
    }
}

@Composable
fun IconArrowBackLoginComercio(onClick: () -> Unit){
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Voltar",
        modifier = Modifier
            .size(25.dp)
            .clickable { onClick() }
    )
}


@Preview(showBackground = true, showSystemUi=true)
@Composable
fun LoginComercioPreview() {
    KotlinFrontGoTheme {
        LoginComercioTela("Android")
    }
}