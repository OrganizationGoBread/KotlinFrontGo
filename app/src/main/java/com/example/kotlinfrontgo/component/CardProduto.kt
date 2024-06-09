package com.example.kotlinfrontgo.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.R

@Composable
fun CardProduto(
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = Modifier.fillMaxWidth()
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(painter = painterResource(id = R.mipmap.menos),
                contentDescription = "Voltar",
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(3f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Produto",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Icon(painter = painterResource(id = R.mipmap.menos),
                contentDescription = "Voltar",
                modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(3f),
                tint = Color.Transparent
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ){
        Text(text = "Ã‰ hora de escolher seus produtos",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray)
    }

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
                    modifier = Modifier.fillMaxWidth() ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "PÃ£o FrancÃªs",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    BtnQuantidade(quantity = 1, onQuantityChange = {})
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "R$5 / PorÃ§Ã£o",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    )

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
                    modifier = Modifier.fillMaxWidth() ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "PÃ£o FrancÃªs",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    BtnQuantidade(quantity = 1, onQuantityChange = {})
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "R$5 / PorÃ§Ã£o",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    )

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun CardProdutoPreview() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        CardProduto()
    }
}