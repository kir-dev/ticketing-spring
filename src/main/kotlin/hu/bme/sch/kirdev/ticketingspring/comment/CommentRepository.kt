package hu.bme.sch.kirdev.ticketingspring.comment

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
interface CommentRepository: CrudRepository<CommentEntity, Int> {
}