package br.com.zup

import br.com.zup.core.port.ToyDatabasePort
import br.com.zup.database.ToyEntity
import br.com.zup.entrypoint.dto.ToyDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

class ToyDatabasePortTest : AnnotationSpec() {

    private val toyDatabasePort = mockk<ToyDatabasePort>()

    private lateinit var toyEntity : ToyEntity

    @BeforeEach
    fun setUp() {
        toyEntity = ToyEntity(UUID.randomUUID(), "Nome", BigDecimal.ONE, "DESC")
    }

    @Test
    fun `should List of ToyEntity- findAll` (){
        every { toyDatabasePort.findAll() } answers { mutableListOf(toyEntity) }
        val result = toyDatabasePort.findAll()
        result shouldBe mutableListOf(toyEntity)
    }

    @Test
    fun `should ToyEntity - findById` (){
        val id = UUID.randomUUID()
        every { toyDatabasePort.findById(id) } answers { toyEntity }
        val result = toyDatabasePort.findById(id)
        result shouldBe toyEntity
    }
}