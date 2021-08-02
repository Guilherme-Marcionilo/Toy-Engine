package br.com.zup.core.port

import br.com.zup.database.ToyEntity
import br.com.zup.entrypoint.dto.ToyDto
import java.util.*

interface ToyServicePort {
    fun findAll(): List<ToyDto>
    fun findById(id: UUID): ToyEntity?
}
