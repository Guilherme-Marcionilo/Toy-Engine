package br.com.zup.database

import br.com.zup.core.port.ToyDatabasePort
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.Row
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*
import javax.inject.Singleton

@Singleton
class ToyDatabaseImpl(private val cqlSession: CqlSession) : ToyDatabasePort {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    override fun findAll(): List<ToyEntity> {
        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM toy.Toy LIMIT 100"
                )
        )
        LOG.info("Listando Toys: $selectResult")
        return selectResult
            .map { toy ->
                ToyEntity(toy.getUuid("id")!!, toy.getString("name")!!,
                    toy.getBigDecimal("price")!!, toy.getString("description")!!)
            }.toList()
    }

    override fun findById(id: UUID): ToyEntity? {
        try {
            val selectResult = cqlSession.execute(
                SimpleStatement
                    .newInstance(
                        "SELECT * FROM toy.Toy WHERE id = ? LIMIT 100",
                        id
                    )
            )
            LOG.info("Possível Toy Selecionado: $selectResult")
            return selectResult
                .map { toy ->
                    ToyEntity(toy.getUuid("id")!!, toy.getString("name")!!,
                        toy.getBigDecimal("price")!!, toy.getString("description")!!) }.firstOrNull()

        } catch (error: IllegalStateException) {
            throw IllegalStateException()
        }
    }


}