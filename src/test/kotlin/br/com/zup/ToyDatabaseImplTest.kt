package br.com.zup

import br.com.zup.database.ToyDatabaseImpl
import br.com.zup.database.ToyEntity
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import java.math.BigDecimal
import java.util.*

class ToyDatabaseImplTest : AnnotationSpec() {

    private val cqlSession = mockk<CqlSession>(relaxed = true)
    private val toyDatabaseImpl = ToyDatabaseImpl(cqlSession)
    private lateinit var toyEntity: ToyEntity

    @BeforeEach
    fun setUp() {
        toyEntity = ToyEntity(UUID.randomUUID(), "Test", BigDecimal.ONE, "Desc")
    }

    @Test
    fun `should return list of toy`() {
        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM toy.Toy LIMIT 100"
                )
        )
        val result = toyDatabaseImpl.findAll()

        verify {toyDatabaseImpl.findAll()}

        result shouldBe selectResult
            .map { toy ->
                ToyEntity(
                    toy.getUuid("id")!!, toy.getString("name")!!,
                    toy.getBigDecimal("price")!!, toy.getString("description")!!
                )
            }.toList()
    }

    @Test
    fun `should return toy by ID`() {

        val id = UUID.randomUUID()

        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM toy.Toy WHERE id = ? LIMIT 100",
                    id
                )
        )
        val result = toyDatabaseImpl.findById(id)

        verify { toyDatabaseImpl.findById(id)}

        result shouldBe selectResult
            .map { toy ->
                ToyEntity(
                    toy.getUuid("id")!!, toy.getString("name")!!,
                    toy.getBigDecimal("price")!!, toy.getString("description")!!
                )
            }.firstOrNull()

    }


}