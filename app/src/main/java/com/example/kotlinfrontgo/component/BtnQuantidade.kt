package com.example.kotlinfrontgo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfrontgo.R

@Composable
fun BtnQuantidade(
    modifier: Modifier = Modifier,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onQuantityChange(quantity - 1) }) {
            Icon(
                painter = painterResource(id = R.mipmap.menos),
                contentDescription = "Decrease"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "1", fontSize = 16.sp) // Aqui você pode colocar a quantidade atual
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Icon(
                painter = painterResource(id = R.mipmap.mais),
                contentDescription = "Increase"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BtnQuantidadePreview() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        BtnQuantidade(quantity = 1, onQuantityChange = {})
    }
}
