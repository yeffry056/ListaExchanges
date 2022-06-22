package com.jjvcorporation.listaexchanges.data.remote.dto

data class Exchange(
    val id: String = "",
    val name: String = "",
    val description: String? = null,
    val active: Boolean = false,
    val last_updated: String = ""
)
