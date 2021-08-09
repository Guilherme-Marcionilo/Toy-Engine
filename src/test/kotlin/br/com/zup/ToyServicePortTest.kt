package br.com.zup

import br.com.zup.core.port.ToyServicePort
import br.com.zup.database.ToyEntity
import br.com.zup.entrypoint.dto.ToyDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

class ToyServicePortTest : AnnotationSpec() {

    private val toyServicePort = mockk<ToyServicePort>()

    private lateinit var toyDto : ToyDto
    private lateinit var toyEntity : ToyEntity

    @BeforeEach
    fun setUp() {
        toyDto = ToyDto(UUID.randomUUID(), "Nome", BigDecimal.ONE, "DESC")
        toyEntity = ToyEntity(UUID.randomUUID(), "Nome", BigDecimal.ONE, "DESC")
    }

    @Test
    fun `should List of ToyDto- findAll` (){
        every { toyServicePort.findAll() } answers { mutableListOf(toyDto) }
        val result = toyServicePort.findAll()
        result shouldBe mutableListOf(toyDto)
    }

    @Test
    fun `should ToyEntity - findById` (){
        val id = UUID.randomUUID()
        every { toyServicePort.findById(id) } answers { toyEntity }
        val result = toyServicePort.findById(id)
        result shouldBe toyEntity
    }
}