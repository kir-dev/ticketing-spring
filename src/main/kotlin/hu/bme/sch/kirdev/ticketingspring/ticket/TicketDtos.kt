package hu.bme.sch.kirdev.ticketingspring.ticket

import hu.bme.sch.kirdev.ticketingspring.board.BoardDto
import hu.bme.sch.kirdev.ticketingspring.label.LabelDto
import java.util.Date

data class CreateTicketDto(
    val name: String,
    val description: String?,
    var boardId: Int,
    var labelIds: List<Int>?
)

data class UpdateTicketDto(
    val name: String,
    val description: String?,
    var status: TicketStatus,
    var boardId: Int,
    var labelIds: List<Int>?
)

data class TicketDto(
    val id: Int,
    val name: String,
    val description: String,
    val status: TicketStatus,
    val createdAt: Date,
    val updatedAt: Date,
){
    constructor(ticket: TicketEntity): this(
        id = ticket.id,
        name = ticket.name,
        description = ticket.description,
        status = ticket.status,
        createdAt = ticket.createdAt,
        updatedAt = ticket.updatedAt,
    )
}

data class DetailedTicketDto(
    val id: Int,
    val name: String,
    val description: String,
    val status: TicketStatus,
    val board: BoardDto,
    val labels: List<LabelDto>,
    val createdAt: Date,
    val updatedAt: Date,
){
    constructor(ticket: TicketEntity): this(
        id = ticket.id,
        name = ticket.name,
        description = ticket.description,
        status = ticket.status,
        board = BoardDto(ticket.board),
        labels = ticket.labels.map { LabelDto(it) },
        createdAt = ticket.createdAt,
        updatedAt = ticket.updatedAt,
    )
}