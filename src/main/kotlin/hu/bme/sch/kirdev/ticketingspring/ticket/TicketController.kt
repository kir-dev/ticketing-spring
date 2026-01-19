package hu.bme.sch.kirdev.ticketingspring.ticket

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Ticket created",
                content = [Content(schema = Schema(implementation = DetailedTicketDto::class))]
            ),
            ApiResponse(responseCode = "400", description = "Board or label not found"),
        ]
    )
    @PostMapping
    fun createTicket(@RequestBody ticket: CreateTicketDto): ResponseEntity<DetailedTicketDto> {
        val created = ticketService.createTicket(ticket)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Tickets found",
                content = [Content(schema = Schema(implementation = DetailedTicketDto::class))]
            )
        ]
    )
    @GetMapping
    fun getAllTickets(): ResponseEntity<List<DetailedTicketDto>> {
        val tickets = ticketService.getAllTickets()
        return ResponseEntity.status(HttpStatus.OK).body(tickets)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Ticket found",
                content = [Content(schema = Schema(implementation = DetailedTicketDto::class))]
            ),
            ApiResponse(responseCode = "404", description = "Ticket not found"),
        ]
    )
    @GetMapping("/{id}")
    fun getTicket(@PathVariable id: Int): ResponseEntity<DetailedTicketDto> {
        val ticket = ticketService.getTicket(id)
        return ResponseEntity.status(HttpStatus.OK).body(ticket)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Ticket updated",
                content = [Content(schema = Schema(implementation = DetailedTicketDto::class))]
            ),
            ApiResponse(responseCode = "400", description = "Board or label not found"),
            ApiResponse(responseCode = "404", description = "Ticket not found"),
        ]
    )
    @PatchMapping("/{id}")
    fun updateTicket(@PathVariable id: Int, @RequestBody ticket: UpdateTicketDto): ResponseEntity<DetailedTicketDto> {
        val updated = ticketService.updateTicket(id, ticket)
        return ResponseEntity.status(HttpStatus.OK).body(updated)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Ticket deleted",
            ),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteTicket(@PathVariable id: Int): ResponseEntity<Void> {
        ticketService.deleteTicket(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}
