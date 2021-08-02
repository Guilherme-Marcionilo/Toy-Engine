package br.com.zup.entrypoint.dto

import java.math.BigDecimal
import java.util.*

data class ToyDto(
    var id: UUID? = null,
    val name: String,
    val price: BigDecimal,
    val description: String
)

