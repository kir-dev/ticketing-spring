package hu.bme.sch.kirdev.ticketingspring.ticket

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ticket")
class TicketController(
    private val ticketService: TicketService
) {

    @PostMapping
    fun createTicket(@RequestBody ticket: CreateTicketDto): ResponseEntity<DetailedTicketDto> {
        val created = ticketService.createTicket(ticket)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @GetMapping
    fun getAllTickets(): ResponseEntity<List<DetailedTicketDto>> {
        val tickets = ticketService.getAllTickets()
        return ResponseEntity.status(HttpStatus.OK).body(tickets)
    }


    @GetMapping("/{id}")
    fun getTicket(@PathVariable id: Int): ResponseEntity<DetailedTicketDto> {
        val ticket = ticketService.getTicket(id)
        return ResponseEntity.status(HttpStatus.OK).body(ticket)
    }


    @PatchMapping("/{id}")
    fun updateTicket(@PathVariable id: Int, @RequestBody ticket: UpdateTicketDto): ResponseEntity<DetailedTicketDto> {
        val updated = ticketService.updateTicket(id, ticket)
        return ResponseEntity.status(HttpStatus.OK).body(updated)
    }


    @DeleteMapping("/{id}")
    fun deleteTicket(@PathVariable id: Int): ResponseEntity<Void> {
        ticketService.deleteTicket(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}
