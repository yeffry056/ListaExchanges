package com.jjvcorporation.listaexchanges.ui.exchange

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.jjvcorporation.listaexchanges.data.remote.dto.Exchange


@Composable
fun ExchangeListScreen(
    viewModel: exchangeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lista de exchanges")
                })
        }

    ) {

        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.exchanges){ exchang ->
                    ExchangeItem(exchange = exchang, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()

        }


    }




}

@Composable
fun ExchangeItem(
    exchange: Exchange,
    onClick : (Exchange) -> Unit
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick(exchange) }


    ) {
        Text(
            text = "${exchange.name}  ",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(

             text = "${exchange.description} ",
             style = MaterialTheme.typography.body1,
             overflow = TextOverflow.Ellipsis
         )
        Text(
            text = "${exchange.last_updated} ",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = if (exchange.active) "Activa" else "Inactiva",
            style = MaterialTheme.typography.body2,
            color = if (exchange.active) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}
