package br.com.zup.core.service

import br.com.zup.core.mapper.ToyConverter
import br.com.zup.core.port.ToyDatabasePort
import br.com.zup.core.port.ToyServicePort
import br.com.zup.database.ToyEntity
import br.com.zup.entrypoint.dto.ToyDto
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class ToyServiceImpl(private val toyDatabasePort: ToyDatabasePort): ToyServicePort {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    override fun findAll(): List<ToyDto> {
        LOG.info("Listando todos os lanches - Core | Service")
        val listToy = toyDatabasePort.findAll()

        val toyDtos = mutableListOf<ToyDto>()

        for (i: ToyEntity in listToy) {
            toyDtos.add(ToyConverter.toyEntitytoToyDto(i))
        }
        return toyDtos
    }

    override fun findById(id: UUID) = toyDatabasePort.findById(id)

}