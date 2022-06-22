package com.jjvcorporation.listaexchanges.data

import com.jjvcorporation.listaexchanges.data.remote.dto.Exchange
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApi {

    @GET("/v1/exchanges")
    suspend fun getExchange(): List<Exchange>

    @GET("/v1/exchanges/{exchangeId}")
    suspend fun getExchanges(@Path("exchangeId") exchangeId: String): Exchange
}