package br.com.zup.entrypoint.controller

import br.com.zup.core.port.ToyServicePort
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller("/toy")
class ToyController(private val toyServicePortPort: ToyServicePort) {

    @Get
    fun findAll() = HttpResponse.ok(toyServicePortPort.findAll())

    @Get("/{id}")
    fun findToyById(@PathVariable id: UUID) = HttpResponse.ok(toyServicePortPort.findById(id))

}