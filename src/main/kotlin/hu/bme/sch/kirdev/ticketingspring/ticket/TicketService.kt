package hu.bme.sch.kirdev.ticketingspring.ticket

import hu.bme.sch.kirdev.ticketingspring.board.BoardRepository
import hu.bme.sch.kirdev.ticketingspring.label.LabelRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val labelRepository: LabelRepository,
    private val boardRepository: BoardRepository
) {

    fun createTicket(ticket: CreateTicketDto): TicketEntity {
        val board = boardRepository.findById(ticket.boardId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
        val labels = ticket.labelIds?.map {
            labelRepository.findById(it)
                .orElseThrow{ ResponseStatusException(HttpStatus.BAD_REQUEST, "Label not found") }
        }?.toMutableList() ?: mutableListOf()
        return ticketRepository.save(TicketEntity(
            name = ticket.name,
            description = ticket.description?:"",
            board = board,
            labels = labels
        ))
    }

    fun getTicket(id: Int): TicketEntity {
        return ticketRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
    }

    fun getAllTickets(): List<TicketEntity> {
        return ticketRepository.findAll().toList()
    }

    fun updateTicket(id: Int, ticket: UpdateTicketDto): TicketEntity {
        val board = boardRepository.findById(ticket.boardId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
        val labels = ticket.labelIds?.map {
            labelRepository.findById(it)
                .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Label not found") }
        }?.toMutableList() ?: mutableListOf()

        return ticketRepository.findById(id).map{
            val toUpdate = it
            toUpdate.name = ticket.name
            toUpdate.description = ticket.description?:""
            toUpdate.status = ticket.status
            toUpdate.board = board
            toUpdate.labels = labels
            ticketRepository.save(toUpdate)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
    }

    fun deleteTicket(id: Int) {
        ticketRepository.deleteById(id)
    }

}