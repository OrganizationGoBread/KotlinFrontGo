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
import com.example.kotlinfrontgo.dto.request.ClienteRequest
import com.example.kotlinfrontgo.dto.request.EnderecoRequest
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.LoginClienteResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroEnderecoCliente : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cpf = intent.extras!!.getString("cpf").toString()
        val nome = intent.extras!!.getString("nome").toString()
        val email = intent.extras!!.getString("email").toString()
        val telefone = intent.extras!!.getString("telefone").toString()
        val senha = intent.extras!!.getString("senha").toString()

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CadastroEnderecoClienteTela(cpf, nome, telefone, email, senha)
                }
            }
        }
    }
}

@Composable
fun CadastroEnderecoClienteTela(cpf: String, nome: String, telefone:String, email:String, senha: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val entradaCep = remember { mutableStateOf("") }
    val entradaNumero = remember { mutableStateOf("") }
    val entradaComplemento = remember { mutableStateOf("") }
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth(1f)
        .padding(top = 30.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBackCadastro3 {
                val tela = Intent(context, CadastroSenhaCliente::class.java)
                context.startActivity(tela)
            }
            Spacer(modifier = Modifier.width(125.dp))
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
                onClick = {
                    val endereco = EnderecoRequest(entradaCep.value, entradaNumero.value.toInt(), entradaComplemento.value)
                    val cliente = ClienteRequest(nome, cpf, telefone, email, senha, "cliente", endereco)

                    val api = RetrofitService.getApiCliente()
                    api.cadastrarCliente(cliente).enqueue(object : Callback<ClienteResponse> {
                        override fun onResponse(call: Call<ClienteResponse>, response: Response<ClienteResponse>) {
                            if (response.isSuccessful) {
                                val telaLoginCliente = Intent(context, LoginCliente::class.java)
                                context.startActivity(telaLoginCliente)
                            } else {
                                Toast.makeText(context, "Erro no cadastro", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<ClienteResponse>, t: Throwable) {
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
            ) { Text("Próximo") }
        }
    }
}

@Composable
fun IconArrowBackCadastro3(onClick: () -> Unit){
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
fun CadastroEnderecoClientePreview() {
    KotlinFrontGoTheme {
        CadastroEnderecoClienteTela("", "", "", "", "")
    }
}