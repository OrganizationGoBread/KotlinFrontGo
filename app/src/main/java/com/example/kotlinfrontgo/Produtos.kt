package com.example.kotlinfrontgo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.kotlinfrontgo.api.RetrofitService
import com.example.kotlinfrontgo.dto.request.ItemPedidoRequest
import com.example.kotlinfrontgo.dto.request.PedidoRequest
import com.example.kotlinfrontgo.dto.request.ProdutoRequest
import com.example.kotlinfrontgo.dto.response.ClienteResponse
import com.example.kotlinfrontgo.dto.response.ItemComercioPedidoResponse
import com.example.kotlinfrontgo.dto.response.ProdutoResponse
import com.example.kotlinfrontgo.ui.theme.KotlinFrontGoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Produtos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idCliente = intent.extras!!.getInt("idCliente")
        val idComercio = intent.extras!!.getInt("idComercio")
        val dia = intent.extras!!.getString("dia").toString()
        val hora = intent.extras!!.getString("hora").toString()

        setContent {
            KotlinFrontGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaProdutos("Android",idCliente,idComercio,dia,hora)
                }
            }
        }
    }
}

@Composable
fun TelaProdutos(name: String, idCliente: Int, idComercio: Int, dia: String, hora: String) {
    val context = LocalContext.current
//    val produtos = remember { mutableStateListOf<ItemComercioPedidoResponse>()}
    val produtos = MutableLiveData<List<MutableLiveData<ItemComercioPedidoResponse>>>()
    val ids = remember { mutableStateListOf<Int>() }
    val nomes = remember { mutableStateListOf<String>() }
    val quantidades = remember { mutableStateListOf<Int>() }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            IconArrowBack {
                val telaPadaria = Intent(context, Padaria::class.java)
                context.startActivity(telaPadaria)
            }
            Spacer(modifier = Modifier.width(60.dp))
            Text("Selecionar Produtos", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        LaunchedEffect(Unit) { // Executar a chamada de API quando o composable é carregado
            val api = RetrofitService.getApiItemComercio()
            api.buscarProdutos(idComercio).enqueue(object :   Callback<List<ItemComercioPedidoResponse>> {
                override fun onResponse(call: Call<List<ItemComercioPedidoResponse>>, response: Response<List<ItemComercioPedidoResponse>>) {
                    Log.i("api", "chamou api!!!!!!")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            produtos.postValue(it.map { item -> MutableLiveData(item) })
                            ids.addAll(it.map { o-> o.produto.id })
                            nomes.addAll(it.map { o -> o.produto.nome })
                            quantidades.addAll(it.map { 0 })
                        }
                    } else {
                        Toast.makeText(context, "Erro ao buscar as padarias", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ItemComercioPedidoResponse>>, t: Throwable) {
                    Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        val lista = produtos.observeAsState().value

        if (lista != null) {
            LazyColumn {
                items(count = lista.size) {index ->
//                    val p = it.observeAsState().value
//                CardProduto(nomeProduto = produto.produto.nome, quantidades[index])
//                    val (produtoGet, produtoSet) = remember { mutableStateOf(it.produto) }
                    CardProduto(lista, quantidades, index)
//                    CardProduto2(produtoGet, produtoSet, it.produto)
//                    Button(onClick = { quantidades[0] = quantidades[0]+1 }) {
//                        Text(text = "mais")
//                    }
//                    Button(onClick = { quantidades[1] = quantidades[1]-1 }) {
//                        Text(text = "menos")
//                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        Button(modifier = Modifier.width(150.dp).height(40.dp),onClick = {
            val listItemPedido = ids.mapIndexed { index, id ->
                ItemPedidoRequest(quantidades[index], ProdutoRequest(id))
            }.toMutableList()
            val pedidoRequest = PedidoRequest(
                diaEntrega = dia,
                horarioEntrega = hora,
                itensPedido = listItemPedido,
                idCliente = idCliente,
                idComercio = idComercio,
                status = "confirmado",
            )
            val api = RetrofitService.getApiPedido()
            api.cadastrarPedido(pedidoRequest).enqueue(object : Callback<List<String>> {
                override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                    if (response.isSuccessful) {
                        Log.i("API","Pingou na API!!!!!!!!")
                        salvarPedidos(context, idCliente)
                    } else {
                        Toast.makeText(context, "Erro no cadastro", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    // Tratar erros de rede
                    Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text(text = "Finalizar Pedido")
        }

    }
}

fun salvarPedidos(context: Context, idCliente: Int){
    val api = RetrofitService.getApiPedido()
    api.salvarPedidos().enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                Log.i("API","Pingou na API PT2!!!!!!!!")
                val tela = Intent(context, PortalCliente::class.java)
                tela.putExtra("idCliente", idCliente)
                context.startActivity(tela)
            } else {
                Toast.makeText(context, "Erro no cadastro", Toast.LENGTH_SHORT).show()
            }
        }
        override fun onFailure(call: Call<Void>, t: Throwable) {
            // Tratar erros de rede
            Toast.makeText(context, "Erro de rede ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
}

@Composable
fun CardProduto(
    produtos: List<MutableLiveData<ItemComercioPedidoResponse>>,
    quantidades: SnapshotStateList<Int>,
    index: Int
) {
    val produto = produtos[index].value!!.produto

    // Card que contém os elementos do produto
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nome do produto
            Text(
                text = produto.nome,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Linha para organizar os botões e a quantidade
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Botão de diminuir a quantidade
                Button(
                    onClick = { if (quantidades[index] > 0) quantidades[index]-- },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(text = "-")
                }

                // Quantidade atual do produto
                Text(
                    text = quantidades[index].toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Botão de aumentar a quantidade
                Button(
                    onClick = { quantidades[index]++ },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = "+")
                }
            }
        }
    }
}
@Composable
fun CardProduto2(
    produtoGet: ProdutoResponse,
    produtoSet: (ProdutoResponse) -> Unit,
    produto: ProdutoResponse
) {

    //val (produtoGet, produtoSet) = remember { mutableStateOf(produto) }

    // Card que contém os elementos do produto
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nome do produto
            Text(
                text = produtoGet.nome,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Linha para organizar os botões e a quantidade
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Botão de diminuir a quantidade
                Button(
                    onClick = { produtoGet.quantidade--; produto.quantidade-=2; if (produtoGet.quantidade > 0) produtoSet(produtoGet.copy(quantidade = produtoGet.quantidade.minus(
                        1
                    ))) },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(text = "-")
                }

                // Quantidade atual do produto
                Text(
                    text = produtoGet.quantidade.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Botão de aumentar a quantidade
                Button(
                    onClick = { produtoSet(produtoGet.copy(quantidade = produtoGet.quantidade.plus(
                        1
                    ))) },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = "+")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    KotlinFrontGoTheme {
        TelaProdutos("Android",1,1,"Segunda-feira","07H30")
    }
}