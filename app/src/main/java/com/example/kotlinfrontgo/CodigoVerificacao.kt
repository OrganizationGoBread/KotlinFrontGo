package com.example.kotlinfrontgo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import kotlin.math.absoluteValue


class CodigoVerificacao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idPedido = intent.extras!!.getInt("idPedido")
        val idComercio = intent.extras!!.getInt("idComercio")

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CodigoVerificacaoTela(idPedido, idComercio)
                }
            }
        }
    }
}

@Composable
fun CodigoVerificacaoTela(idPedido: Int, idComercio: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var entradaNum1 = remember { mutableStateOf("") }
    val entradaNum2 = remember { mutableStateOf("") }
    val entradaNum3 = remember { mutableStateOf("") }
    val entradaNum4 = remember { mutableStateOf("") }
    val maxChar = 1
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    var isFocused = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        focusRequester1.requestFocus()
        onDispose { }
    }

    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier

        .fillMaxWidth(1f)) {
        Row (modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 30.dp)){
            IconArrowBack {
                val telaPortalComercio = Intent(context, PortalComercio::class.java)
                telaPortalComercio.putExtra("idComercio", idComercio)
                context.startActivity(telaPortalComercio)
            }

            Text("Verificação", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 110.dp)
                    .padding(bottom = 50.dp))
        }

        Row (){
            Text("Digite o código de\nverificação", fontSize = 40.sp, fontWeight = FontWeight.Bold, lineHeight = 45.sp,
                modifier = Modifier.fillMaxWidth(0.92f).padding(bottom = 15.dp))
        }

        Text("Solicite o código ao cliente:", fontSize = 18.sp, color = Color.Gray,
            modifier = Modifier.fillMaxWidth(0.92f).padding(bottom = 20.dp))

        Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth(1f)){

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = entradaNum1.value,
                onValueChange = {
                    if (it.length <= maxChar) {
                        entradaNum1.value = it
                    }
                },

                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .focusRequester(focusRequester1)
                    .onGloballyPositioned {
                        if (entradaNum1.value.length == maxChar) {
                            focusRequester2.requestFocus()
                        }
                    },

                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 40.sp),
                )

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = entradaNum2.value,
                onValueChange = {
                    if (it.length <= maxChar) entradaNum2.value = it
                },

                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .focusRequester(focusRequester2)
                    .onGloballyPositioned {
                        if (entradaNum2.value.length == maxChar) {
                            focusRequester3.requestFocus()
                        }
                    },

                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 40.sp),
                )

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = entradaNum3.value,
                onValueChange = {
                    if (it.length <= maxChar) entradaNum3.value = it
                },

                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .focusRequester(focusRequester3)
                    .onGloballyPositioned {
                        if (entradaNum3.value.length == maxChar) {
                            focusRequester4.requestFocus()
                        }
                    },

                shape = RoundedCornerShape(15.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 40.sp),
                )

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = entradaNum4.value,
                onValueChange = {
                    if (it.length <= maxChar) entradaNum4.value = it
                },

                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .focusRequester(focusRequester4),
                shape = RoundedCornerShape(15.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 40.sp),
                )
        }

        Button(
            onClick = {
                val apiPedido = RetrofitService.getApiPedido()
                val codigoVerificacaoString = entradaNum1.value + entradaNum2.value + entradaNum3.value + entradaNum4.value
                val codigoVerificacao = codigoVerificacaoString.toInt()

                apiPedido.verifyCode(codigoVerificacao, idPedido).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            // Status do pedido atualizado com sucesso
                            Toast.makeText(context, "Status do pedido atualizado com sucesso", Toast.LENGTH_SHORT).show()
                            val tela = Intent(context, PortalComercio::class.java)
                            tela.putExtra("idComercio", idComercio)
                            context.startActivity(tela)
                        } else {
                            // Tratar erros
                            Toast.makeText(context, " ${response.code()} Erro ao atualizar status do pedido", Toast.LENGTH_SHORT).show()
                        }
                    }



                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Tratar erros de rede
                        Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            },

            modifier = Modifier
                .padding(PaddingValues(top = 20.dp))
                .fillMaxWidth(0.93f),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(12)
        ) {
            Text("Verificar", fontSize = 20.sp, modifier = Modifier.height(35.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun CodigoVerificacaoPreview() {
    KotlinFrontGoTheme {
        CodigoVerificacaoTela(1,1)
    }
}