package br.com.zup.core.port

import br.com.zup.database.ToyEntity
import java.util.*
import javax.inject.Singleton

@Singleton
interface ToyDatabasePort {
    fun findAll(): List<ToyEntity>
    fun findById(id: UUID): ToyEntity?

}
