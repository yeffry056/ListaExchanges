package com.jjvcorporation.listaexchanges.ui.exchange

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjvcorporation.listaexchanges.data.repository.exchangeRepository
import com.jjvcorporation.listaexchanges.exchangeListState
import com.jjvcorporation.listaexchanges.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class exchangeViewModel @Inject constructor(
    private val exchangeRepository: exchangeRepository
):ViewModel(){

    private var _state = mutableStateOf(exchangeListState())
    val state: State<exchangeListState> = _state

    init {
        exchangeRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = exchangeListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = exchangeListState(exchanges = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = exchangeListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}