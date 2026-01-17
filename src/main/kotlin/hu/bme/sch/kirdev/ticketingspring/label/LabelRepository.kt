package hu.bme.sch.kirdev.ticketingspring.label

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository: CrudRepository<LabelEntity, Int> {
}