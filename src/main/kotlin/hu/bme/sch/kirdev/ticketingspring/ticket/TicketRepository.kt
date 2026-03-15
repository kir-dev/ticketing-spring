package hu.bme.sch.kirdev.ticketingspring.ticket

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: CrudRepository<TicketEntity, Int> {

}