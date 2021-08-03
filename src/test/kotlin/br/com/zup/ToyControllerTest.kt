package br.com.zup

import br.com.zup.core.port.ToyServicePort
import br.com.zup.core.service.ToyServiceImpl
import br.com.zup.entrypoint.controller.ToyController
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.clearAllMocks
import io.mockk.mockk
import java.util.*

@MicronautTest
class ToyControllerTest() : StringSpec({

    clearAllMocks()

    val createToy: ToyServicePort = ToyServiceImpl(mockk(relaxed = true))

    val id = UUID.randomUUID()

    val toyController = ToyController(createToy)

    "getAll" {
        toyController.findAll().code() shouldBe 200
    }

    "notGetById" {
        toyController.findToyById(id).code() shouldNotBe 500
    }

    "getById" {
        toyController.findToyById(id).code() shouldBe 200
    }
})