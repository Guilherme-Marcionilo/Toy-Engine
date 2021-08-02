package br.com.zup.core.model

import java.math.BigDecimal
import java.util.*

data class Toy(
    var id: UUID? = null,
    val name: String,
    val price: BigDecimal,
    val description: String
)