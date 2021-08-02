package br.com.zup.core.mapper

import br.com.zup.database.ToyEntity
import br.com.zup.entrypoint.dto.ToyDto

class ToyConverter {
    companion object {
        fun toyEntitytoToyDto(toyEntity: ToyEntity) = with(toyEntity) {
            ToyDto(id, name, price, description)
        }
    }
}