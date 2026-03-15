package hu.bme.sch.kirdev.ticketingspring.ticket

import hu.bme.sch.kirdev.ticketingspring.board.BoardRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val boardRepository: BoardRepository
) {

    fun createTicket(ticket: CreateTicketDto): DetailedTicketDto {
        val board = boardRepository.findById(ticket.boardId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
        return ticketRepository.save(TicketEntity(
            name = ticket.name,
            description = ticket.description?:"",
            board = board,
        )).let { DetailedTicketDto(it) }
    }

    fun getTicket(id: Int): DetailedTicketDto {
        return ticketRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
            .let { DetailedTicketDto(it) }
    }

    fun getAllTickets(): List<DetailedTicketDto> {
        return ticketRepository.findAll().map { DetailedTicketDto(it) }
    }

    fun updateTicket(id: Int, ticket: UpdateTicketDto): DetailedTicketDto {
        val board = boardRepository.findById(ticket.boardId)
            .orElseThrow{ ResponseStatusException(HttpStatus.BAD_REQUEST, "Board not found") }

        return ticketRepository.findById(id).map{
            it.name = ticket.name
            it.description = ticket.description?:""
            it.status = ticket.status
            it.board = board
            ticketRepository.save(it)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
            .let { DetailedTicketDto(it) }
    }

    fun deleteTicket(id: Int) {
        ticketRepository.deleteById(id)
    }

}