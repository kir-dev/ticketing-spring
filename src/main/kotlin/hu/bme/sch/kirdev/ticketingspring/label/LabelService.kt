package hu.bme.sch.kirdev.ticketingspring.label

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class LabelService(
    private val labelRepository: LabelRepository
) {

    fun createLabel(label: CreateLabelDto): DetailedLabelDto {
        return labelRepository.save(
            LabelEntity(
                name = label.name,
                color = label.color
            )
        ).let { DetailedLabelDto(it) }
    }

    fun getLabel(id: Int): DetailedLabelDto {
        return labelRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Label not found") }
            .let { DetailedLabelDto(it) }
    }

    fun getAllLabels(): List<DetailedLabelDto> {
        return labelRepository.findAll().map { DetailedLabelDto(it) }
    }

    fun updateLabel(id: Int, label: UpdateLabelDto): DetailedLabelDto {
         return labelRepository.findById(id).map{
            val toUpdate = it
            toUpdate.name = label.name
            toUpdate.color = label.color
            labelRepository.save(toUpdate)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Label not found") }
        .let { DetailedLabelDto(it) }
    }

    fun deleteLabel(id: Int) {
        labelRepository.deleteById(id)
    }

}