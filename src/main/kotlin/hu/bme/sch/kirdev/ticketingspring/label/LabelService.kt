package hu.bme.sch.kirdev.ticketingspring.label

import io.micrometer.observation.transport.ResponseContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class LabelService(
    private val labelRepository: LabelRepository
) {

    fun createLabel(label: CreateLabelDto): LabelEntity {
        return labelRepository.save(
            LabelEntity(
                name = label.name,
                color = label.color
            )
        )
    }

    fun getLabel(id: Int): LabelEntity {
        return labelRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Label not found") }
    }

    fun getAllLabels(): List<LabelEntity> {
        return labelRepository.findAll().toList()
    }

    fun updateLabel(id: Int, label: UpdateLabelDto): LabelEntity {
         return labelRepository.findById(id).map{
            val toUpdate: LabelEntity = it
            toUpdate.name = label.name
            toUpdate.color = label.color
            labelRepository.save(toUpdate)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Label not found") }
    }

    fun deleteLabel(id: Int) {
        labelRepository.deleteById(id)
    }

}