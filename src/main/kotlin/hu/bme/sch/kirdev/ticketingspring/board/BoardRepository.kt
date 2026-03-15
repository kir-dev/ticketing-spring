package hu.bme.sch.kirdev.ticketingspring.board

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: CrudRepository<BoardEntity, Int> {
}