package com.jjvcorporation.listaexchanges.data.repository

import com.jjvcorporation.listaexchanges.data.ExchangeApi
import com.jjvcorporation.listaexchanges.data.remote.dto.Exchange
import com.jjvcorporation.listaexchanges.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class exchangeRepository @Inject constructor(
    private val api : ExchangeApi
) {

    fun getExchanges(): Flow<Resource<List<Exchange>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val exchang = api.getExchange() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(exchang)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}